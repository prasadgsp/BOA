package com.netbanking.database;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class trandetails {
	
	public enum TransType {
		DEBIT,
		CREDIT,
		TRANSFER;
	}
	
	public enum TransStatus {
		PENDING,
		REJECTED,
		COMPLETED;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int transId;
	public int toAcct;
	public int fromAcct;
	public String authUser;
	public double amount;
	@Enumerated(EnumType.STRING)
	public TransType transType;
	@Enumerated(EnumType.STRING)
    public TransStatus transStatus;
    public Date timestamp;
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public int getToAcct() {
		return toAcct;
	}
	public void setToAcct(int toAcct) {
		this.toAcct = toAcct;
	}
	public int getFromAcct() {
		return fromAcct;
	}
	public void setFromAcct(int fromAcct) {
		this.fromAcct = fromAcct;
	}
	public String getAuthUser() {
		return authUser;
	}
	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransType getTransType() {
		return transType;
	}
	public void setTransType(TransType transType) {
		this.transType = transType;
	}
	public TransStatus getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(TransStatus transStatus) {
		this.transStatus = transStatus;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public trandetails(int transId, int toAcct, int fromAcct, String authUser,
			double amount, TransType transType, TransStatus transStatus,
			Date timestamp) {
		this.transId = transId;
		this.toAcct = toAcct;
		this.fromAcct = fromAcct;
		this.authUser = authUser;
		this.amount = amount;
		this.transType = transType;
		this.transStatus = transStatus;
		this.timestamp = timestamp;
	}
	public trandetails(){}
    
    
}
