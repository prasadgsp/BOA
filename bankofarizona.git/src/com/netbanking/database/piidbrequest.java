package com.netbanking.database;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.netbanking.database.trandetails.TransStatus;

// there will be a page where an external entity
//can create pii request

@Entity
public class piidbrequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reqNum;
	
	private String userId;
	private String requester;
	private String requestermail;
	private TransStatus status;
	public piidbrequest(String userId, String requester,
			String requestermail, TransStatus status) {
		this.userId = userId;
		this.requester = requester;
		this.requestermail = requestermail;
		this.status = status;
	}
	public piidbrequest(){}
	
	public int getReqNum() {
		return reqNum;
	}
	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRequester() {
		return requester;
	}
	public void setRequester(String requester) {
		this.requester = requester;
	}
	public String getRequestermail() {
		return requestermail;
	}
	public void setRequestermail(String requestermail) {
		this.requestermail = requestermail;
	}
	public TransStatus getStatus() {
		return status;
	}
	public void setStatus(TransStatus status) {
		this.status = status;
	}
	
	
}
