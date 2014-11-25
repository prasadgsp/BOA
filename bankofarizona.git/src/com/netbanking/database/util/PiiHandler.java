package com.netbanking.database.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.netbanking.database.piidbrequest;
import com.netbanking.database.userinfo;
import com.netbanking.database.trandetails.TransStatus;
import com.netbanking.exception.DBResult;
import com.netbanking.util.OTPGenerator;

public class PiiHandler {
	
		//Creates new pii request
	public static DBResult createPIIRequest(String fName,String lName, String req,String reqMail){
		piidbrequest pii = new piidbrequest();
		//pii.setReqNum(getPiitNum()+1);
		pii.setStatus(TransStatus.PENDING);
		pii.setRequester(req);
		pii.setRequestermail(reqMail);
		String uid = UserDBHandler.fetchUserId(fName, lName);
		if(uid == null){
			return new DBResult(false,"Selected user not found");
		} else{
			pii.setUserId(uid);
		}
		
		Session session = null;
		try{		
		session=DBConn.getSessionFactory().openSession();
		if(session == null){
			return new DBResult(false,"Error in database session");
		}
		session.beginTransaction();
		session.save(pii);
		session.getTransaction().commit();
		session.close();
		return new DBResult(true,"PII requested registered");
		
		}catch(Exception e){
			return new DBResult(false,"Error occured while processing the request. Please try again later.");
		}
	} 
	
	//Auth a pii request
	public static DBResult authPIIRequest(int piiId){
		Session session = null;
		try{		
			session=DBConn.getSessionFactory().openSession();
			if(session == null){
				return new DBResult(false,"Exception in session creation");
			}
			
			session.beginTransaction();
			
			Query query = session.createQuery("from piidbrequest where reqNum = :ReqNum and status= :sta");
			query.setParameter("ReqNum", piiId);
			query.setParameter("sta", TransStatus.PENDING);
			piidbrequest PII = (piidbrequest) query.uniqueResult();
			if(PII == null){
				return new DBResult(false, "Error occured while fetching PII request from database");
			}			
			query = session.createQuery("update piidbrequest set status = :Sta where reqNum = :ReqNum");
			query.setParameter("ReqNum", piiId);
			query.setParameter("Sta", TransStatus.COMPLETED);			
			int result = query.executeUpdate();
			if (result == 1) {			   			    
			    userinfo user = UserDBHandler.getCustomerWithPII(PII.getUserId());
			    if(user == null) {
			    	return new DBResult(false, "Error occured while viewing profile. Possible cause: Invalid user ID.");
			    }
			    //Send Mail
			    OTPGenerator OTP = new OTPGenerator();
			    OTP.sendPII(PII.getReqNum(), PII.getRequestermail(), user.getSsn(),true);			    
			    session.getTransaction().commit();
			    session.close();
			    return new DBResult(true,"PII request authorized");
			}
		}	
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return new DBResult(false,"Error occured while processing the request. Please try again later.");
		}
		return new DBResult(false,"Error in PII authorization");
	}
	
	//Reject PII
	public static DBResult denyPii(int piiId){
		Session session = null;
		try{		
			session=DBConn.getSessionFactory().openSession();
			if(session == null){
				return new DBResult(false,"Exception in session creation");
			}
			session.beginTransaction();
			Query query = session.createQuery("update piidbrequest set status = :Status where reqNum = :ReqNum");
			query.setParameter("ReqNum", piiId);
			query.setParameter("Status", TransStatus.REJECTED);			
			int result = query.executeUpdate();
			if (result == 1) {	    
			    session.getTransaction().commit();
			    session.close();
			    return new DBResult(true,"PII request rejected successfully");
			}
			} catch(Exception e){
				return new DBResult(false,"Error occured while processing the request. Please try again later.");
			}
		return new DBResult(false,"Failed to change PII request status");
	}
	
	//Retrieves pending pii req
	public static List<piidbrequest> getPendingPii(){
		
		List<piidbrequest> piilist = new ArrayList<piidbrequest>();
		Session session=null;
		try
		{
			session=DBConn.getSessionFactory().openSession();
			if(session == null){
				return null;
			}
			Query query=session.createQuery("from piidbrequest where status= :Status");
			query.setParameter("Status",TransStatus.PENDING);
			
			piilist=query.list();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}	
		finally{
			session.flush();
			session.close();
		}
		return piilist;
		
	}
}
