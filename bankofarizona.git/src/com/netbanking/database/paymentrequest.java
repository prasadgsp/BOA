package com.netbanking.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.netbanking.database.trandetails.TransStatus;

@Entity
public class paymentrequest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int paymentId;
	
	private String merchantId;
	private String payeeId;
	private double paymentAmount;
	private String paymentDesc;
	private TransStatus paymentStatus;
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentDesc() {
		return paymentDesc;
	}
	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}
	public TransStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(TransStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public paymentrequest(String merchantId, String payeeId,
			double paymentAmount, String paymentDesc, TransStatus paymentStatus) {
		this.merchantId = merchantId;
		this.payeeId = payeeId;
		this.paymentAmount = paymentAmount;
		this.paymentDesc = paymentDesc;
		this.paymentStatus = paymentStatus;
	}
	
	public paymentrequest(){}
}
