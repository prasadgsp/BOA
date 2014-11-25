package com.netbanking.database.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.netbanking.database.*;
import com.netbanking.database.account;
import com.netbanking.database.account.AccountStatus;
import com.netbanking.database.paymentrequest;
import com.netbanking.database.trandetails;
import com.netbanking.database.trandetails.TransStatus;
import com.netbanking.database.userinfo;
import com.netbanking.exception.DBResult;

public class AccountDBHandler {

	public static List<account> getAccounts() {

		List<account> acctlist = new ArrayList<account>();
		Session session = null;
		int i = 0;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session.createQuery("from account");

			List templist = query.list();

			for (i = 0; i < templist.size(); i++) {
				account tempacct = (account) templist.get(i);
				acctlist.add(tempacct);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.flush();
			session.close();
		}
		return acctlist;
	}

	public static double getBalance(int accountId) {
		double Balance = 0;
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return -999.00;
			}
			Query query = session
					.createQuery("from account where accNum= :accNum");
			query.setParameter("accNum", accountId);
			account acct = (account) query.uniqueResult();
			if (acct != null && acct.getAccNum() == (accountId)) {
				Balance = acct.getBalance();
			} else
				Balance = -999.0;
		} catch (Exception e) {
			System.out
					.println("Error occured while searching for Account Balance: "
							+ e.getMessage());
		} finally {
			session.flush();
			session.close();
		}
		return Balance;
	}

	public static List<account> getAccounts(String userId) {

		List<account> acctlist = new ArrayList<account>();
		Session session = null;
		int i = 0;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from account where userId= :userId");
			query.setParameter("userId", userId);

			List templist = query.list();

			for (i = 0; i < templist.size(); i++) {
				account tempacct = (account) templist.get(i);
				acctlist.add(tempacct);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.flush();
			session.close();
		}
		return acctlist;
	}

	public static List<account> getLiveAccounts(String userId) {

		List<account> acctlist = new ArrayList<account>();
		Session session = null;
		int i = 0;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from account where userId= :userId and accStatus=0");
			query.setParameter("userId", userId);

			List templist = query.list();

			for (i = 0; i < templist.size(); i++) {
				account tempacct = (account) templist.get(i);
				acctlist.add(tempacct);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.flush();
			session.close();
		}
		return acctlist;
	}

	public static DBResult checkAccount(String userId, int accNum) {
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false, "Database session not created");
			}
			Query query = session
					.createQuery("from account where userId= :userId and accNum= :accNum");
			query.setParameter("userId", userId);
			query.setParameter("accNum", accNum);
			List templist = query.list();
			account acct = (account) query.uniqueResult();
			if (acct != null && acct.getAccNum() == (accNum)) {
				return new DBResult(true, "User has the account");
			} else
				return new DBResult(false, "Account not associated with user");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		} finally {
			session.flush();
			session.close();
		}
	}

	public static DBResult checkAuthCombo(String userId, int accNum) {
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false, "Database session not created");
			}
			Query query = session
					.createQuery("from authsender where  authAccont= :accNum and userId= :userId");
			query.setParameter("accNum", accNum);
			query.setParameter("userId", userId);
			authsender acct = (authsender) query.uniqueResult();
			if (acct != null && acct.getAuthAccont() == (accNum)) {
				return new DBResult(true, "User already authorized");
			} else {
				return new DBResult(false, "Account not authorized for user");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		} finally {
			session.flush();
			session.close();
		}
	}

	public static DBResult updateBalance(int accountId, double balance) {
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false, "Database session not created");
			}
			Transaction trans = session.beginTransaction();
			Query query = session
					.createQuery("update account set balance = :balance where accNum= :accNum");
			query.setParameter("balance", balance);
			query.setParameter("accNum", accountId);

			int r = query.executeUpdate();
			trans.commit();
			if (r == 1)
				return new DBResult(true, "Account balance updated");
			else {
				trans.rollback();
				return new DBResult(false, "Error in account balance updation");
			}
		} catch (Exception e) {
			System.out
					.println("Error occured while searching for Account Balance: "
							+ e.getMessage());
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		} finally {
			session.flush();
			session.close();
		}
	}

	public static List<trandetails> getLastTenTrans(int acctId) {

		List<trandetails> translist = new ArrayList<trandetails>();
		Session session = null;
		int i = 0;
		int start = 0;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from trandetails where toAcct= :acctId or fromAcct= :acctIdd and TransStatus='COMPLETED'");
			query.setParameter("acctId", acctId);
			query.setParameter("acctIdd", acctId);

			List templist = query.list();
			if (templist.size() < 10)
				start = 0;
			else
				start = templist.size() - 9;

			for (i = start; i < templist.size(); i++) {
				trandetails temptrans = (trandetails) templist.get(i);
				translist.add(temptrans);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.flush();
			session.close();
		}
		return translist;
	}

	public static List<trandetails> getFirstTenPending() {

		List<trandetails> translist = new ArrayList<trandetails>();
		Session session = null;
		int i = 0;
		int end = 0;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from trandetails where TransStatus='PENDING'");

			List templist = query.list();
			if (templist.size() < 4)
				end = templist.size();
			else
				end = 4;

			for (i = 0; i < end; i++) {
				trandetails temptrans = (trandetails) templist.get(i);
				translist.add(temptrans);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		finally {
			session.flush();
			session.close();
		}
		return translist;
	}

	public static int getLastAcctNum() {
		int acctNum = 501642918;
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return -999;
			}
			Query query = session.createQuery("from account");

			List templist = query.list();
			if (templist.size() == 0) {
				return acctNum;
			}

			account tempacct = (account) templist.get(templist.size() - 1);
			if (tempacct != null) {
				acctNum = tempacct.getAccNum();
			}
		} catch (Exception e) {

			e.printStackTrace();
			return -999;
		}

		finally {
			session.flush();
			session.close();
		}

		return acctNum;

	}

	// Check if account Number exists
	public static DBResult checkAccountNumExists(int accNum) {
		Session session = null;
		DBResult result = new DBResult(false,
				"Error occured while searching for account number.");
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			Query query = session
					.createQuery("from account where accNum= :accNum");
			query.setParameter("accNum", accNum);
			account account = (account) query.uniqueResult();
			if (account != null && account.getAccNum() == accNum) {
				return new DBResult(true, "Account Number Found.");
			} else {
				return new DBResult(false, "Account Number not found.");
			}
		} catch (Exception e) {
			System.out
					.println("Error occured while searching for account number: "
							+ e.getMessage());
			result = new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
		return result;
	}

	// Fetch details of an account
	public static account getAccount(int accId) {
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}
			Query query = session
					.createQuery("from account where accNum= :accNum");
			query.setParameter("accNum", accId);
			account Account = (account) query.uniqueResult();
			if (Account != null) {
				return Account;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out
					.println("Error occured while searching for account number: "
							+ e.getMessage());
			return null;
		}
	}

	// Delete an account
	public static DBResult deleteAccount(int accId) {
		account Account = getAccount(accId);
		if (Account == null) {
			return new DBResult(false, "Error while fetching from database");
		}
		if (Account.getBalance() != 0) {
			return new DBResult(false,
					"Please withdraw all your money before closing");
		}
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false, "Database session not created");
			}
			Transaction trans = session.beginTransaction();
			Query query = session
					.createQuery("update account set accStatus = :status,dateOfClose=:cdate where accNum= :accNum");
			query.setParameter("status", AccountStatus.CLOSED);
			query.setParameter("accNum", accId);
			Date d = new Date();
			query.setParameter("cdate", d);

			int r = query.executeUpdate();
			trans.commit();
			if (r == 1)
				return new DBResult(true, "Account Closed!!!");
			else {
				trans.rollback();
				return new DBResult(false, "Error in account closing");
			}
		} catch (Exception e) {
			System.out.println("Error occured while deleting for Account : "
					+ e.getMessage());
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		} finally {
			session.flush();
			session.close();
		}

	}

	public static int getCustAccountID(String custId) {

		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return -999;
			}
			session.beginTransaction();

			Query query = session
					.createQuery("from account where userId= :custId");
			query.setParameter("custId", custId);

			List templist = query.list();

			account acc = (account) templist.get(0);
			int custAcc = acc.getAccNum();
			return custAcc;

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return -999;
		}

	}

	public static int getMerchantAccountID(int paymentId) {

		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return -999;
			}
			session.beginTransaction();

			Query query = session
					.createQuery("from paymentrequest where paymentId= :paymentId");
			query.setParameter("paymentId", paymentId);

			List<paymentrequest> templist = query.list();

			paymentrequest pr = (paymentrequest) templist.get(0);
			String merchantId = pr.getMerchantId();

			Query query2 = session
					.createQuery("from account where userId= :merchantId");
			query2.setParameter("merchantId", merchantId);

			List templist2 = query2.list();

			account acc = (account) templist2.get(0);
			int merchantAcc = acc.getAccNum();
			return merchantAcc;

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return -999;
		}
	}

	public static double getPaymentAmt(int paymentId) {

		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return -999.00;
			}
			session.beginTransaction();

			Query query = session
					.createQuery("from paymentrequest where paymentId= :paymentId");
			query.setParameter("paymentId", paymentId);

			List<paymentrequest> templist = query.list();

			paymentrequest pr = (paymentrequest) templist.get(0);
			double paymentamt = pr.getPaymentAmount();

			return paymentamt;

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return -999.00;
		}
	}

	public static DBResult updatePaymentStatus(int paymentId,
			TransStatus paymentStatus) {
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false, "Database session not created");
			}
			Transaction trans = session.beginTransaction();
			Query query = session
					.createQuery("update paymentrequest set paymentStatus = :paymentStatus where paymentId= :paymentId");
			query.setParameter("paymentStatus", paymentStatus);
			query.setParameter("paymentId", paymentId);

			int r = query.executeUpdate();
			trans.commit();
			if (r == 1)
				return new DBResult(true, "Payment status updated");
			else {
				trans.rollback();
				return new DBResult(false, "Error in payment status updation");
			}
		} catch (Exception e) {
			System.out.println("Error occured while updating payment stat: "
					+ e.getMessage());
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		} finally {
			session.flush();
			session.close();
		}
	}

	public static DBResult insertPaymentDetails(String payeeID,
			String merchantId, double amount, String desc) {
		Session session = null;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			session.beginTransaction();

			paymentrequest pr = new paymentrequest(merchantId, payeeID, amount,
					desc, TransStatus.PENDING);

			session.save(pr);

			session.getTransaction().commit();
			session.close();
			return new DBResult(true, "Payment details updated!");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
	}

	public static List<paymentrequest> retrievePaymentDetails(String userId) {

		List<paymentrequest> paymentlist = new ArrayList<paymentrequest>();
		Session session = null;
		int i = 0;
		try {
			session = DBConn.getSessionFactory().openSession();
			if (session == null) {
				return null;
			}

			System.out.println("Going to run query");
			Query query = session
					.createQuery("from userinfo where userId= :userId");
			query.setParameter("userId", userId);

			List templist = query.list();

			userinfo uinfo = (userinfo) templist.get(0);

			String emailID = uinfo.geteMail();

			Query query2 = session
					.createQuery("from paymentrequest where payeeId= :payeeId and paymentStatus= 0");
			query2.setParameter("payeeId", emailID);

			List<paymentrequest> temppaylist = query2.list();
			if (temppaylist.size() == 0)
				return null;

			for (i = 0; i < temppaylist.size(); i++) {

				paymentrequest tempPr = (paymentrequest) temppaylist.get(i);
				paymentlist.add(tempPr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.flush();
			session.close();
		}
		return paymentlist;
	}
}
