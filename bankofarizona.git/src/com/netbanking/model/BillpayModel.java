package com.netbanking.model;

public class BillpayModel {

	private String paymentStatusString;
	private String paymentIDs;
	private int fromAcct;
	
	
	//private String merchantID;
	
	public int getFromAcct() {
		return fromAcct;
	}
	public void setFromAcct(int fromAcct) {
		this.fromAcct = fromAcct;
	}
	public String getPaymentStatusString() {
		return paymentStatusString;
	}
	public void setPaymentStatusString(String paymentStatusString) {
		this.paymentStatusString = paymentStatusString;
	}
	public String getPaymentIDs() {
		return paymentIDs;
	}
	public void setPaymentIDs(String paymentIDs) {
		this.paymentIDs = paymentIDs;
	}
	
	
	
	
	
}
