package com.netbanking.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.netbanking.database.usersec;
import com.netbanking.database.util.DBConn;

public class AuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		if (exception.getClass().isAssignableFrom(
				SessionAuthenticationException.class)) {
			response.sendRedirect(request.getContextPath() + "/sessionError");
		} else {
			Session session = null;
			try {
				session = DBConn.getSessionFactory().openSession();
				if (session == null) {
					return;
				}
				session.beginTransaction();
				Query query = session
						.createQuery("from usersec where userId = :uId");
				query.setParameter("uId", request.getParameter("j_username"));
				usersec usec = (usersec) query.uniqueResult();
				if (usec == null) {
					response.sendRedirect(request.getContextPath()
							+ "/homePageError");
					return;
				}
				if (usec.getNoOfFailedLogin() >= 2) {
					query = session
							.createQuery("update usersec set enabled = :status where userID = :userid");
					query.setParameter("userid", usec.getUserId());
					query.setParameter("status", false);
					int result = query.executeUpdate();
					if (result == 1) {
						session.getTransaction().commit();
						session.close();
					}
					response.sendRedirect(request.getContextPath()
							+ "/accountLocked");
					return;
				} else {

					query = session
							.createQuery("update usersec set noOfFailedLogin = noOfFailedLogin + 1 where userID = :userid");
					query.setParameter("userid", usec.getUserId());
					int result = query.executeUpdate();
					if (result == 1) {
						session.getTransaction().commit();
						session.close();
					}
					response.sendRedirect(request.getContextPath()
							+ "/homePageError");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return;
			}
		}

	}
}
