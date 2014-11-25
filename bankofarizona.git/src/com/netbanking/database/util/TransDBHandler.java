package com.netbanking.database.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.netbanking.database.account;
import com.netbanking.database.trandetails;
import com.netbanking.database.trandetails.TransStatus;
import com.netbanking.exception.DBResult;

public class TransDBHandler {	

	public static List<trandetails> getFirstTenTrans()
	{

		List<trandetails> tranlist = new ArrayList<trandetails>();
		Session session=null;
		int i=0;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if(session == null){
				return null;
			}
			Query query=session.createQuery("from trandetails");


			List templist=query.list();
			if(templist == null){
				return null;
			}
			int tempsize;
			for(i=0;i<templist.size();i++)
			{
				trandetails temptran=(trandetails)templist.get(i);
				tranlist.add(temptran);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}


		finally{
			session.flush();
			session.close();
		}


		return tranlist;

	}

	public static List<trandetails> getFirstTenPendingTrans()
	{
		
		List<trandetails> tranlist = new ArrayList<trandetails>();
		Session session=null;
		int i=0;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			Query query=session.createQuery("from trandetails where transStatus= :status");
			query.setParameter("status",TransStatus.PENDING);
			
			List templist=query.list();
			for(i=0;i<templist.size();i++)
			{
				trandetails temptran=(trandetails)templist.get(i);
				tranlist.add(temptran);
		
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
			
		
		return tranlist;
		
	}

	public static List<trandetails> getTrans(int acctId)
	{

		List<trandetails> tranlist = new ArrayList<trandetails>();
		Session session=null;
		int i=0;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if(session == null){
				return null;
			}
			Query query=session.createQuery("from trandetails where fromAcct= :acctId or toAcct= :acctId");
			query.setParameter("acctId", acctId);

			List templist=query.list();
			if(templist == null){
				return null;
			}

			for(i=0;i<templist.size();i++)
			{
				trandetails temptran=(trandetails)templist.get(i);
				tranlist.add(temptran);

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


		return tranlist;

	}



	public static DBResult chkValidAcct(int acctId)
	{
		Session session=null;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			Query query=session.createQuery("from account where accNum= :accNum");
			query.setParameter("accNum",acctId);
			account acct=(account)query.uniqueResult();
			if(acct!= null && acct.getAccNum()==(acctId)) {
				return new DBResult(true, "Account validation succeeded!");
			} else {
				return new DBResult(false, "Account Validation Failed!");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
		finally{
			session.flush();
			session.close();
		}
	}

	public static DBResult chkBalance(int acctId, double TransAmt)
	{
		Session session=null;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			Query query=session.createQuery("from account where accNum= :accNum");
			query.setParameter("accNum",acctId);
			account acct=(account)query.uniqueResult();
			if(acct!= null && acct.getAccNum()==(acctId))
			{
				if (TransAmt<= acct.getBalance()) {
					return new DBResult(true, "Amount validation succeeded!");
				} else {
					return new DBResult(false, "Transfer amount is more than Account balance.");
				}
			} else {
				return new DBResult(false, "Invalid account number!");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}	
		finally{
			session.flush();
			session.close();
		}
	}


	public static DBResult createTrans(trandetails trans)
	{
		if(trans.getAmount() < 0){
			return new DBResult(false,
					"Negetive values are not allowed as amount!");
		}
		DBResult result = new DBResult(false, "Database exception occured.");
		Session session=null;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			Transaction tran=session.beginTransaction();
			Date date = new Date();
			trans.setTimestamp(date);
			session.save(trans);
			tran.commit();
			result = new DBResult(true, "Transaction successfully created.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}
		finally{
			session.flush();
			session.close();
		}
		return result;
	}


	public static DBResult setTransComplete(int transId, String authUser)
	{
		Session session=null;
		DBResult result = new DBResult(false, "Database exception occured.");
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			Transaction trans=session.beginTransaction();
			Query query=session.createQuery("from trandetails where transId= :transId");
			query.setParameter("transId",transId);
			trandetails trans1 = (trandetails)query.uniqueResult();
			
			double balance = AccountDBHandler.getBalance(trans1.getFromAcct());
			if(balance == -999) {
				return new DBResult(false, "Error retrieving balance for the from Account. Possible Cause: Invalid account number.");
			}
			if(balance < trans1.getAmount()) {
				return new DBResult(false, "The account balance is less than the transfer money for transaction " + transId);
			}
			
			query=session.createQuery("update trandetails set transStatus='COMPLETED', authUser = :authUser where transId= :transId and transStatus='PENDING'");
			query.setParameter("transId",transId);
			query.setParameter("authUser",authUser);
			int r=query.executeUpdate();			
			if(r==1) {
				switch(trans1.getTransType()){
				case TRANSFER:
				{
					query=session.createQuery("update account set balance= balance + :amt where accNum= :acc");
					query.setParameter("acc",trans1.getToAcct());
					query.setParameter("amt",trans1.getAmount());
					r=query.executeUpdate();
					if(r == 1){
						query=session.createQuery("update account set balance= balance - :amt where accNum= :acc");
						query.setParameter("acc",trans1.getFromAcct());
						query.setParameter("amt",trans1.getAmount());
						r=query.executeUpdate();
						if(r == 1){
							trans.commit();
							return new DBResult(true, "Transaction status set to COMPLETE.");
						}
					}
					break;
				}
				case DEBIT:
				{
					query=session.createQuery("update account set balance= balance - :amt where accNum= :acc");
					query.setParameter("acc",trans1.getFromAcct());
					query.setParameter("amt",trans1.getAmount());
					r=query.executeUpdate();
					if(r == 1){
						trans.commit();
						return new DBResult(true, "Transaction status set to COMPLETE.");
					}
					break;
				}
				case CREDIT:
				{
					query=session.createQuery("update account set balance= balance + :amt where accNum= :acc");
					query.setParameter("acc",trans1.getFromAcct());
					query.setParameter("amt",trans1.getAmount());
					r=query.executeUpdate();
					if(r == 1){
						trans.commit();
						return new DBResult(true, "Transaction status set to COMPLETE.");
					}
					break;
				}
				}

			}
			else
			{
				trans.rollback();
				return new DBResult(false, "Transaction status could not be set to COMPLETE.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}

		finally{
			session.flush();
			session.close();
		}

		return result;
	}

	//Reject a transaction
	public static DBResult setTransReject(int transId, String authUser)
	{
		Session session=null;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if (session == null) {
				return new DBResult(false,
						"Database session could not be created!");
			}
			Transaction trans=session.beginTransaction();
			Query query=session.createQuery("update trandetails set transStatus='REJECTED', authUser = :authUser where transId= :transId and transStatus='PENDING'");
			query.setParameter("transId",transId);
			query.setParameter("authUser",authUser);
			int r=query.executeUpdate();
			trans.commit();
			if(r==1){
				return new DBResult(true, "Transaction status set to REJECTED.");
			}
			else
			{
				trans.rollback();
				return new DBResult(false, "Transaction status could not be set to REJECTED.");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new DBResult(false, "Error occured while processing the request. Please try again later.");
		}

		finally{
			session.flush();
			session.close();
		}
	}

	// get transaction from a specific date
	public static List<trandetails> getTransFromDate(Date fromDate)
	{

		List<trandetails> tranlist = new ArrayList<trandetails>();
		Session session=null;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if(session == null){
				return null;
			}
			Query query=session.createQuery("from trandetails where timestamp > :date");
			query.setParameter("date",fromDate);
			tranlist=query.list();
			if(tranlist == null){
				return null;
			}
		}
		catch(Exception e)
		{	
			e.printStackTrace();
			return null;
		}
		finally{
			session.flush();
			session.close();
		}


		return tranlist;

	}
}