package com.netbanking.model;

public class AccessPIIModel {
	private String firstname;
	private String lastname;
	private String requesterName;
	private String requesterMailID;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getRequesterName() {
		return requesterName;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	public String getRequesterMailID() {
		return requesterMailID;
	}
	public void setRequesterMailID(String requesterMailID) {
		this.requesterMailID = requesterMailID;
	}
}
