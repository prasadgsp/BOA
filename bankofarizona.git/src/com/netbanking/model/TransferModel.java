package com.netbanking.model;

public class TransferModel {
	private int transId;
	private double amount;
	private int fromAcct;
	private int toAcct;
	private String timestamp;
	private String transStatus;
	private String transtype;
	
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
	public String getTranstype() {
		return transtype;
	}
	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}
	
	
}
