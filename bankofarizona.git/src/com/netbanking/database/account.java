package com.netbanking.database;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class account {

	@Id
	private int accNum;
	
	private String userId;
	private Date dateOfOpen;
	private Date dateOfClose;
	private double balance;
	private AccountStatus accStatus;
	private AccountType accType;
	
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

	public AccountStatus getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(AccountStatus accStatus) {
		this.accStatus = accStatus;
	}

	public AccountType getAccType() {
		return accType;
	}

	public void setAccType(AccountType accType) {
		this.accType = accType;
	}

	public enum AccountStatus {
		OPEN,
		CLOSED;
	}
	
	public enum AccountType {
		CHECKING,
		SAVING;
	}

	public account(int accNum, String userId, Date dateOfOpen,
			Date dateOfClose, double balance, AccountStatus accStatus,
			AccountType accType) {
		this.accNum = accNum;
		this.userId = userId;
		this.dateOfOpen = dateOfOpen;
		this.dateOfClose = dateOfClose;
		this.balance = balance;
		this.accStatus = accStatus;
		this.accType = accType;
	}
	public account(){}
	
}
