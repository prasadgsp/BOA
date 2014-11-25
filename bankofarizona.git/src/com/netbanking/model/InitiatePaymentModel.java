package com.netbanking.model;

public class InitiatePaymentModel {
	private String email;
	private double amount;
	private String desc;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDesc() {
		//System.out.println("printing From model" + desc);
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
		System.out.println("printing From model" + desc);

	}
	
}
