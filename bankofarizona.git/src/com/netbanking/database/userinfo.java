package com.netbanking.database;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class userinfo {
	
	public enum UserType {
		CUSTOMER,
		MERCHANT,
		MANAGER,
		EMPLOYEE;
		}

		public enum Gender {
		MALE,
		FEMALE;
		}
		
		public enum UserStatus {
			LIVE,
			DEAD;
			}
		
	@Id
	private String userId;
	private String firstName;
	private String lastName;
	private String ssn;
	@Enumerated(EnumType.STRING)
	private UserType userType;
	private String address;
	private Date dob;
	private String eMail;
	private int phoneNo;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;
	private int empId;
	
	public userinfo(){}	
	public userinfo(String userId, String firstName,
			String lastName, String ssn, UserType userType,
			String address, Date dob, String eMail,
			int pnoneNo, Gender gender, UserStatus userStatus, int empId) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.userType = userType;
		this.address = address;
		this.dob = dob;
		this.eMail = eMail;
		this.phoneNo = pnoneNo;
		this.gender = gender;
		this.userStatus = userStatus;
		this.empId = empId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int pnoneNo) {
		this.phoneNo = pnoneNo;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public UserStatus getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	
}
