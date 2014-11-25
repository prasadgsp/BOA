package com.netbanking.model;

public class CreateTransModel {
	
	
	private int transId;
	private double amount;
	private int fromAcct;
	private int toAcct;
	private String timestamp;
	private String transStatus;
	private String transType;
	
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getFromAcct() {
		return fromAcct;
	}
	public void setFromAcct(int fromAcct) {
		this.fromAcct = fromAcct;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	
	
}
