package com.netbanking.database.util;

import org.hibernate.Query;
import org.hibernate.Session;

import com.netbanking.util.*;
import com.netbanking.database.*;
import com.netbanking.exception.DBResult;

public class LoginDBHandler {

	public static DBResult LoginCheck(String userid, String passwd) {
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			Query query = session
					.createQuery("from usersec where userId= :userid");
			query.setParameter("userid", userid);
			System.out.println(passwd);
			usersec user = (usersec) query.uniqueResult();
			if (user != null && user.getUserId().equals(userid)) {
				Encryptor enc = new Encryptor();
				if (enc.validateUser(passwd, user.getPasswd())) {
					return new DBResult(true, "User authenticated.");
				} else {
					return new DBResult(false, "Invalid Password!");
				}
			} else {
				return new DBResult(false, "Invalid Username!");
			}
		} catch (Exception e) {
			System.out.println("Error occured while searching for username: "
					+ e.getMessage());
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		} finally {
			session.flush();
			session.close();
		}
	}

	public static OTPGenerator sendOTPToUser(String userid) {
		Session session = null;
		OTPGenerator OTP = new OTPGenerator();
		try {
			if (!checkUserIdExists(userid).isResult()) {
				return null;
			}
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from userinfo where userId= :userid");
			query.setParameter("userid", userid);
			userinfo user = (userinfo) query.uniqueResult();
			OTP.sendOTP(user.geteMail());
		} catch (Exception e) {
			return null;
		}
		return OTP;
	}

	public static DBResult updatePassword(String userid, String oldPassword,
			String newPassword, String givenOTP, OTPGenerator OTP) {

		Session session = null;

		try {
			if (!checkUserIdExists(userid).isResult()) {
				return new DBResult(false, "User not found!");
			}
			 Encryptor en=new Encryptor();
				
				boolean flag1 = en.matches(givenOTP,OTP.getOTP());
				if (!flag1) {
					return new DBResult(false, "OTP doesn't match!");
				}
			if (Math.abs(System.currentTimeMillis() - OTP.getTimeStamp()) > 300000) {
				return new DBResult(false, "OTP Expired!");
			}
			DBResult flag = LoginCheck(userid, oldPassword);
			if (!flag.isResult()) {
				return new DBResult(false, flag.getMessage());
			}
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			session.beginTransaction();
			Query query = session
					.createQuery("from usersec where userId= :userid");
			query.setParameter("userid", userid);

			String enewPwd = Encryptor.encodePwd(newPassword);

			query = session
					.createQuery("update usersec set passwd = :newPassword where userID = :userid");
			query.setParameter("newPassword", enewPwd);
			query.setParameter("userid", userid);
			int result = query.executeUpdate();
			if (result == 1) {
				session.getTransaction().commit();
				session.close();
				return new DBResult(true, "User password updated!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
		return new DBResult(false, "Error in updating user credential!");
	}

	// Check if user id exists or not
	public static DBResult checkUserIdExists(String userid) {
		Session session = null;
		DBResult result = new DBResult(false, "Database exception occured.");
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			Query query = session
					.createQuery("from userinfo where userId= :userid");
			query.setParameter("userid", userid);
			userinfo user = (userinfo) query.uniqueResult();
			if (user != null && user.getUserId().equals(userid)) {
				return new DBResult(true, "User Found.");
			} else {
				return new DBResult(false, "User not found.");
			}
		} catch (Exception e) {
			System.out.println("Error occured while searching for username: "
					+ e.getMessage());
			result = new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
		return result;
	}

	public static userinfo checkMailIdExists(String eMail) {
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from userinfo where eMail= :email");
			query.setParameter("email", eMail);
			userinfo user = (userinfo) query.uniqueResult();
			if (user != null)
				return user;
		} catch (Exception e) {
			System.out.println("Error occured while searching for username: "
					+ e.getMessage());
			return null;
		}
		return null;
	}

	// check OTP
	public static DBResult checkOTP(String userid, String givenOTP,
			OTPGenerator OTP) {
		try {
            Encryptor en=new Encryptor();
			
			boolean flag = en.matches(givenOTP,OTP.getOTP());
			if (!checkUserIdExists(userid).isResult()) {
				return new DBResult(false, "User not found!");
			}else if (!flag) {
				return new DBResult(false, "OTP doesn't match!");
			}else if (Math.abs(System.currentTimeMillis() - OTP.getTimeStamp()) > 300000) {
				return new DBResult(false, "OTP Expired!");
			} else {
				return new DBResult(true, "Entered OTP is correct!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
	}

	// Change the original password in DataBase for Forgot Password
	public static DBResult changePassword(String userid, String Password) {
		Session session = null;
		try {

			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			session.beginTransaction();

			Query query = session
					.createQuery("from usersec where userId= :userid");
			query.setParameter("userid", userid);
			String ePwd = Encryptor.encodePwd(Password);

			query = session
					.createQuery("update usersec set passwd = :Password where userID = :userid");
			query.setParameter("Password", ePwd);
			query.setParameter("userid", userid);
			int result = query.executeUpdate();
			System.out.println(result);
			
			if (result == 1) {
				session.getTransaction().commit();
				session.close();
				return new DBResult(true, "Password is successfully changed.");
			} else {
				session.close();
				return new DBResult(false, "Error occured while updating the password!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
	}
}