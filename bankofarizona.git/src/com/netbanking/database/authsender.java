package com.netbanking.database;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class authsender {
	public authsender(String userId, String authSenderId,int authAcc) {
		this.userId = userId;
		this.authSenderId = authSenderId;
		this.authAccont = authAcc;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int AuthId;
	private String userId;
	private String authSenderId;
	private int authAccont;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuthSenderId() {
		return authSenderId;
	}
	public void setAuthSenderId(String authSenderId) {
		this.authSenderId = authSenderId;
	}
	public int getAuthAccont() {
		return authAccont;
	}
	public void setAuthAccont(int authAccont) {
		this.authAccont = authAccont;
	}
	public authsender() {}
	public int getAuthId() {
		return AuthId;
	}
	public void setAuthId(int authId) {
		AuthId = authId;
	}
	 

}
