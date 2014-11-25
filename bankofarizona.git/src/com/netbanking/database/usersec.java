package com.netbanking.database;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class usersec {	
	public enum AuthStatus {
		REQUESTED,
		APPROVED,
		REJECTED;
	}
	
	@Id
	private String userId;
	@Column(length = 1337)
	private String passwd;
	private String ques1;
	private String ans1;
	private String ques2;
	private String ans2;
	private String ques3;
	private String ans3;
	private int sitekeyId;
	private boolean enabled;
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	private AuthStatus authStatus = AuthStatus.REJECTED;
	private int noOfFailedLogin;
	
	public usersec(){}
	public usersec(String userId, String passwd, String ques1, String ans1,
			String ques2, String ans2, String ques3, String ans3, int sitekeyId) {
		this.userId = userId;
		this.passwd = passwd;
		this.ques1 = ques1;
		this.ans1 = ans1;
		this.ques2 = ques2;
		this.ans2 = ans2;
		this.ques3 = ques3;
		this.ans3 = ans3;
		this.sitekeyId = sitekeyId;
		this.authStatus = AuthStatus.REJECTED;
		this.noOfFailedLogin = 0;
		this.enabled = true;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getQues1() {
		return ques1;
	}
	public void setQues1(String ques1) {
		this.ques1 = ques1;
	}
	public String getAns1() {
		return ans1;
	}
	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}
	public String getQues2() {
		return ques2;
	}
	public void setQues2(String ques2) {
		this.ques2 = ques2;
	}
	public String getAns2() {
		return ans2;
	}
	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}
	public String getQues3() {
		return ques3;
	}
	public void setQues3(String ques3) {
		this.ques3 = ques3;
	}
	public String getAns3() {
		return ans3;
	}
	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}
	public int getSitekeyId() {
		return sitekeyId;
	}
	public void setSitekeyId(int sitekeyId) {
		this.sitekeyId = sitekeyId;
	}
	public AuthStatus getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(AuthStatus authStatus) {
		this.authStatus = authStatus;
	}
	public int getNoOfFailedLogin() {
		return noOfFailedLogin;
	}
	public void setNoOfFailedLogin(int noOfFailedLogin) {
		this.noOfFailedLogin = noOfFailedLogin;
	}
	
	
}
