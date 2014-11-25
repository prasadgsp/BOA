package com.netbanking.model;

public class TransactionsModel {
	String transStatusString;
	String transIDs;

	public TransactionsModel(String transStatusString, String transIDs) {
		this.transStatusString = transStatusString;
		this.transIDs = transIDs;
	}

	public String getTransIDs() {
		return transIDs;
	}

	public void setTransIDs(String transIDs) {
		this.transIDs = transIDs;
	}

	public TransactionsModel() {
	}

	public String getTransStatusString() {
		return transStatusString;
	}
	
	public void setTransStatusString(String transStatusString) {
		this.transStatusString = transStatusString;
	}
}
