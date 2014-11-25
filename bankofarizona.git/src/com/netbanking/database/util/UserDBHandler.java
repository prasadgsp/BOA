package com.netbanking.database.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.netbanking.model.*;
import com.netbanking.database.*;
import com.netbanking.database.account.AccountStatus;
import com.netbanking.database.trandetails.TransStatus;
import com.netbanking.database.trandetails.TransType;
import com.netbanking.database.userinfo.UserStatus;
import com.netbanking.database.userinfo.UserType;
import com.netbanking.database.usersec.AuthStatus;
import com.netbanking.exception.DBResult;
import com.netbanking.util.Encryptor;
import com.netbanking.util.GetRole;
import com.netbanking.util.OTPGenerator;

public class UserDBHandler {
	public static DBResult userSignUp(userinfo user, usersec usec, account acc) {
		DBResult result = new DBResult(false, "Database exception occured.");
		if(acc!= null && acc.getBalance() < 0){
			return new DBResult(false, "Negetive balance not allowed as initial deposit!");
		}
		result = LoginDBHandler.checkUserIdExists(user.getUserId());
		if (result.isResult()) {
			return new DBResult(false, "User ID already exists.");
		}
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			String sPwd = usec.getPasswd();
			String sSSN = user.getSsn();
			String ePwd = Encryptor.encodePwd(sPwd);
			String eSSN = Encryptor.codeSSN(sSSN);
			usec.setPasswd(ePwd);
			user.setSsn(eSSN);
			session.beginTransaction();
			session.save(user);
			session.save(usec);

			if (acc != null) {
				int lastAccNum = AccountDBHandler.getLastAcctNum();
				if (lastAccNum == -999) {
					return new DBResult(false, "Database connection error!");
				}
				acc.setAccNum(lastAccNum + 1);
				acc.setAccStatus(AccountStatus.OPEN);
				acc.setDateOfClose(null);
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				acc.setDateOfOpen(date);
				trandetails tns = new trandetails();
				tns.setAmount(acc.getBalance());
				tns.setFromAcct(acc.getAccNum());
				tns.setTransType(TransType.CREDIT);
				tns.setTransStatus(TransStatus.COMPLETED);
				tns.setToAcct(0);
				tns.setTimestamp(date);

				session.save(acc);
				session.save(tns);
			}
			
			OTPGenerator OTP = new OTPGenerator();
			DBResult certResult = new DBResult(true, "");
			if(acc != null){
				certResult = OTP.sendCertificate(user.getUserId(), user.geteMail(), acc.getAccNum());
			} else {
				certResult = OTP.sendCertificate(user.getUserId(), user.geteMail(), 0);
			}
			if(!certResult.isResult()){
				return new DBResult(false, "Error in certificate creation for user: "+user.getFirstName());
			}
			session.getTransaction().commit();
			session.close();
			result = new DBResult(true, "New user Account created!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
		return result;
	}

	// Gets user type
	public static UserType getCustomerType(String uid) {
		if (!LoginDBHandler.checkUserIdExists(uid).isResult()) {
			return null;
		}
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from userinfo where userId= :userid");
			query.setParameter("userid", uid);
			userinfo user = (userinfo) query.uniqueResult();
			return user.getUserType();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// Method to retrieve customer detail without PII
	public static userinfo getCustomerWithoutPII(String userid) {
		userinfo user = null;
		if (!LoginDBHandler.checkUserIdExists(userid).isResult()) {
			return null;
		}
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from userinfo where userId= :userid");
			query.setParameter("userid", userid);
			user = (userinfo) query.uniqueResult();

			// Hiding PII
			user.setSsn("000000000");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return user;
	}

	public static String fetchUserId(String fName, String lName) {
		userinfo user = null;
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from userinfo where firstName = :fname and lastName = :lname");
			query.setParameter("fname", fName);
			query.setParameter("lname", lName);
			user = (userinfo) query.uniqueResult();
			if (user == null) {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return user.getUserId();

	}

	// Method to retrieve customer detail with PII
	public static userinfo getCustomerWithPII(String userid) {
		// TODO check authorization
		userinfo user = null;
		if (!LoginDBHandler.checkUserIdExists(userid).isResult()) {
			return null;
		}
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from userinfo where userId= :userid");
			query.setParameter("userid", userid);
			user = (userinfo) query.uniqueResult();
			if (user == null) {
				return null;
			}
			user.setSsn(Encryptor.codeSSN(user.getSsn()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return user;
	}

	public static DBResult updateUserDetail(String userId, String address,
			String eMail, int phone) {
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			session.beginTransaction();
			Query query = session
					.createQuery("update userinfo set address = :newadrs, eMail = :newMail, phoneNo = :newPhn where userID = :userid");
			query.setParameter("newadrs", address);
			query.setParameter("userid", userId);
			query.setParameter("newMail", eMail);
			query.setParameter("newPhn", phone);
			int result = query.executeUpdate();
			if (result == 1) {
				session.getTransaction().commit();
				session.close();
				return new DBResult(true, "User details updated!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
		return new DBResult(false, "Failed to update user details!");
	}

	// Retrieve Sec qu & Answer for user
	public static usersec getSecurityAnswers(String userId) {
		usersec usec = null;
		if (!LoginDBHandler.checkUserIdExists(userId).isResult()) {
			return null;
		}
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from usersec where userId= :userid");
			query.setParameter("userid", userId);
			usec = (usersec) query.uniqueResult();
			/*String Q1 = usec.getQues1();
			String Q2 = usec.getQues2();
			String Q3 = usec.getQues3();
			query = session
					.createQuery("from quwarehouse where question_ID= :Qid");

			query.setParameter("Qid", Q1);
			usec.setQues1(((quwarehouse) query.uniqueResult()).getQuestion());
			query.setParameter("Qid", Q2);
			usec.setQues2(((quwarehouse) query.uniqueResult()).getQuestion());
			query.setParameter("Qid", Q3);
			usec.setQues3(((quwarehouse) query.uniqueResult()).getQuestion());*/
			usec.setPasswd("");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

		return usec;
	}

	/*public static List<quwarehouse> getAllSecurityQuestions() {
		List<quwarehouse> qwh = new ArrayList<quwarehouse>();

		Session session = null;
		int i = 0;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session.createQuery("from quwarehouse");
			List templist = query.list();

			for (i = 0; i < templist.size(); i++) {
				quwarehouse tempquwh = (quwarehouse) templist.get(i);
				qwh.add(tempquwh);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.flush();
			session.close();
		}
		return qwh;

	}*/


	
	public static DBResult removeUser(String userId, String DeleteInitiatorUser) {

		// User cannot delete himself
		if (userId.equals(DeleteInitiatorUser)) {
			return new DBResult(false, "User cannot delete himself!");
		}
		DBResult result = LoginDBHandler.checkUserIdExists(userId);
		if (!result.isResult()) {
			return result;
		}
		result = LoginDBHandler.checkUserIdExists(DeleteInitiatorUser);
		if (!result.isResult()) {
			return result;
		}

		userinfo user = getCustomerWithoutPII(userId);
		if (user == null) {
			return new DBResult(false,
					"Error occured while viewing profile. Possible cause: Invalid user ID.");
		}
		userinfo authUser = getCustomerWithoutPII(DeleteInitiatorUser);

		if (authUser == null) {
			return new DBResult(false,
					"Error occured while viewing profile. Possible cause: Invalid user ID.");
		}
		// Checking if users are alive
		if (user.getUserStatus() != UserStatus.LIVE) {
			return new DBResult(false, "User already disabled");
		}

		Session session = null;
		Query query = null;
		Transaction trans = null;
		boolean uflag = false;
		List<account> acctlist= new ArrayList<account>();
		try
		{
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false, "Database exception occured.");
			}
			
			query=session.createQuery("from account where userId= :userId and accStatus=0");
			query.setParameter("userId", userId);
			acctlist=(List<account>) query.list();
			if(acctlist.size()>0){
				return new DBResult(false, "There are active accounts associated with the user. Disable them first");
			}
			System.out.println("NO ACTIVE ACCOUNTS!!!!!");
			trans = session.beginTransaction();
			query = session.createQuery("update userinfo set userStatus= :status where userId = :userid");
			query.setParameter("userid", userId);
			query.setParameter("status", UserStatus.DEAD);
			int result1 = query.executeUpdate();
			if(result1==1)
			{
			session.getTransaction().commit();
			}
			else if (result1 != 1) {
				return new DBResult(false, "Database exception occured.");
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}

		session.close();
		return new DBResult(true, "User Successfully Deleted");
	}

	// Change User Auth Status
	public static DBResult modifyUserAuthorizedStatus(String userId,
			AuthStatus authStatus) {
		Session session = null;
		Query query = null;
		Transaction trans = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false, "Database exception occured.");
			}
			trans = session.beginTransaction();
			query = session
					.createQuery("update usersec set authStatus= :status where userId = :userid");
			query.setParameter("userid", userId);
			query.setParameter("status", authStatus);
			int result = query.executeUpdate();
			if (result == 1) {
				session.getTransaction().commit();
				session.close();
				return new DBResult(true,
						"The authorization has been approved.");
			} else {
				return new DBResult(false, "Database exception occured.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
	}

	// Add auth user
	public static String addAuthorizedUser(String eMail, int accNum,
			String userId) {
		if (eMail == null || userId == null) {
			return null;
		}
		if (!LoginDBHandler.checkUserIdExists(userId).isResult()) {
			return null;
		}
		userinfo authUser = LoginDBHandler.checkMailIdExists(eMail);
		if (authUser == null) {
			return null;
		} else {
			if (!authUser.geteMail().equals(eMail)) {
				return null;
			}
			authsender auth = new authsender(userId, authUser.getUserId(),
					accNum);
			Session session = null;
			try {
				session = DBConn.getSessionFactory().openSession();
				if (session == null) {
					return null;
				}
				session.beginTransaction();
				session.save(auth);
				session.getTransaction().commit();
				session.close();

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return (authUser.getFirstName() + " " + authUser.getLastName());
	}

	public static DBResult userUnlock(String userId){
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if(session == null) {
				return new DBResult(false, "failed to create database session");
			}
			session.beginTransaction();
			Query query = session
					.createQuery("update usersec set enabled= 1 where userId = :userid");
			query.setParameter("userid", userId);
			int result = query.executeUpdate();
			if (result == 1) {
				session.getTransaction().commit();
				session.close();
				return new DBResult(true, "User Account: "+userId+" Has been unlocked");
			} else {
				return new DBResult(false, "Failed to unlock user account.");
			}
			}catch(Exception e){
				e.printStackTrace();
				return new DBResult(false, "Error occured while processing the request. Please try again later.");
			}
		}
	
	public static List<userinfo> getUserProfiles() {
		
		
		List<userinfo> userList = new ArrayList<userinfo>();
		Session session=null;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if(session == null){
				return null;
			}
			String role = GetRole.roles();
			if(role.equalsIgnoreCase("MANAGER"))
			{
			Query query=session.createQuery("from userinfo");
			userList=(List<userinfo>) query.list();
			
			if(userList == null){
				return null;
			}
			}
			else if(role.equalsIgnoreCase("EMPLOYEE"))
			{
			Query query=session.createQuery("from userinfo where userType='CUSTOMER' or userType= 'MERCHANT'");
			userList=(List<userinfo>) query.list();
			
			if(userList == null){
				return null;
			}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			session.flush();
			session.close();
		}
		return userList;
	}
	
public static userinfo getUserProfile(String userId) {
		
		
		userinfo user=new userinfo();
		Session session=null;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if(session == null){
				return null;
			}
			Query query=session.createQuery("from userinfo where userId= :userId");
			query.setParameter("userId", userId);

			user=(userinfo)query.uniqueResult();
			if(user == null){
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			session.flush();
			session.close();
		}
		return user;
	}
	
	public static List<authsender> getAllAuthSenders(String userid) {
		List<authsender> authList = new ArrayList<authsender>();
		Session session=null;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if(session == null){
				return null;
			}
			Query query=session.createQuery("from authsender where userId= :UserId");
			query.setParameter("UserId", userid);

			authList=(List<authsender>)query.list();
			if(authList == null){
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			session.flush();
			session.close();
		}
		return authList;
	}
}
