package com.netbanking.model;

import java.util.Date;

import com.netbanking.database.account.AccountType;
import com.netbanking.database.userinfo.Gender;
import com.netbanking.database.userinfo.UserType;

public class SignupModel {
	public enum State{
		AL,
		AK,
		AZ,
		AR,
		CA,
		CO,
		CT,
		DE,
		DC,
		FL,
		GA,
		HI,
		ID,
		IL,
		IN,
		IA,
		KS,
		KY,
		LA,
		ME,
		MD,
		MA,
		MI,
		MN,
		MS,
		MO,
		MT,
		NE,
		NV,
		NH,
		NJ,
		NM,
		NY,
		NC,
		ND,
		OH,
		OK,
		OR,
		PA,
		RI,
		SC,
		SD,
		TN,
		TX,
		UT,
		VT,
		VA,
		WA,
		WV,
		WI,
		WY;
	}
	private String username;
	private String password;
	private String repassword;
	private String firstname;
	private String lastname;
	private String ssn;
	private UserType usertype;
	private int employeeid;
	private Date dob;
	private String addressline1;
	private String addressline2;
	private String  city;
	private State state;
	private int zipcode;
	private String email;
	private int phonenumber;
	private Gender gender;
	private String secQuestion_1;
	private String secQuestion_2;
	private String secQuestion_3;
	private String secanswer_1;
	private String secanswer_2;
	private String secanswer_3;
	private int accBalance;
	private AccountType accounttype;
	
	public int getAccBalance() {
		return accBalance;
	}
	public void setAccBalance(int accBalance) {
		this.accBalance = accBalance;
	}
	public AccountType getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(AccountType accounttype) {
		this.accounttype = accounttype;
	}
	public String getUsername() {
		return username;
	}
	public String getSecQuestion_1() {
		return secQuestion_1;
	}
	public void setSecQuestion_1(String secQuestion_1) {
		this.secQuestion_1 = secQuestion_1;
	}
	public String getSecQuestion_2() {
		return secQuestion_2;
	}
	public void setSecQuestion_2(String secQuestion_2) {
		this.secQuestion_2 = secQuestion_2;
	}
	public String getSecQuestion_3() {
		return secQuestion_3;
	}
	public void setSecQuestion_3(String secQuestion_) {
		this.secQuestion_3 = secQuestion_;
	}
	public String getSecanswer_1() {
		return secanswer_1;
	}
	public void setSecanswer_1(String secanswer_1) {
		this.secanswer_1 = secanswer_1;
	}
	public String getSecanswer_2() {
		return secanswer_2;
	}
	public void setSecanswer_2(String secanswer_2) {
		this.secanswer_2 = secanswer_2;
	}
	public String getSecanswer_3() {
		return secanswer_3;
	}
	public void setSecanswer_3(String secanswer_3) {
		this.secanswer_3 = secanswer_3;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
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
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public UserType getUsertype() {
		return usertype;
	}
	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
