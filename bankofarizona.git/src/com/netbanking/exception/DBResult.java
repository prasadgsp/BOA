package com.netbanking.exception;

public class DBResult {
	private boolean result;
	private String message;
	
	public DBResult(boolean result, String message) {
		this.result = result;
		this.message = message;
	}
	
	public DBResult() {
	}
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
