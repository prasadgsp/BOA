package com.netbanking.model;

import java.util.Date;
 
public class ViewAccountModel {
   private int accNum;
	
	public int getAccNum() {
	return accNum;
}
public void setAccNum(int accNum) {
	this.accNum = accNum;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public Date getDateOfOpen() {
	return dateOfOpen;
}
public void setDateOfOpen(Date dateOfOpen) {
	this.dateOfOpen = dateOfOpen;
}
public Date getDateOfClose() {
	return dateOfClose;
}
public void setDateOfClose(Date dateOfClose) {
	this.dateOfClose = dateOfClose;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
public AccountType getAccType() {
	return accType;
}
public void setAccType(AccountType accType) {
	this.accType = accType;
}
	private String userId;
	private Date dateOfOpen;
	private Date dateOfClose;
	private double balance;
	public enum AccountType {
		CHECKING,
		SAVING;
	}
	private AccountType accType;
	
	

}
