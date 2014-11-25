package com.netbanking.util;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.netbanking.database.userinfo;
import com.netbanking.database.usersec;
import com.netbanking.database.util.DBConn;


public class CustomAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {
	 
	 @Override
	 public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
	      String userTargetUrl = "/externalHomePage.html";
	      String adminTargetUrl = "/internalHomePage.html";
	      String deadUserUrl = "/deadUser.html";
	      
	      Session session = null;
			 try{
				 session = DBConn.getSessionFactory().openSession();
					if(session == null) {
						return;
					}
					session.beginTransaction();
					Query query1 = session
							.createQuery("from userinfo where userId= :userid");
					query1.setParameter("userid", request.getParameter("j_username"));
					userinfo user = (userinfo) query1.uniqueResult();
					if(user == null) {
						System.out.println("inside first");
						
							response.sendRedirect(request.getContextPath()
									+ "/homePageError");
							
							request.getSession().invalidate();
							return;
					}
					if(user.getUserStatus().toString().equalsIgnoreCase("DEAD"))
					{  
						
						request.getSession().invalidate();
						getRedirectStrategy().sendRedirect(request, response, deadUserUrl);
						
						
						return;
					}
					Query query = session
							.createQuery("update usersec set noOfFailedLogin = 0 where userID = :userid");
					query.setParameter("userid", request.getParameter("j_username"));
					int result = query.executeUpdate();
					if (result == 1) {
						session.getTransaction().commit();
						session.close();
					}
			 }catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					return;
			 }
	      
	     Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
	      if (roles.contains("CUSTOMER") || roles.contains("MERCHANT")) {
	         getRedirectStrategy().sendRedirect(request, response, userTargetUrl);
	      } 
	      else if (roles.contains("EMPLOYEE") || roles.contains("MANAGER")) {
		         getRedirectStrategy().sendRedirect(request, response, adminTargetUrl);
	      }
	      else {
	         super.onAuthenticationSuccess(request, response, authentication);
	         return;
	      }
			
	   }
	}