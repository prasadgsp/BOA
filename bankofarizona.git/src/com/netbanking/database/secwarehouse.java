package com.netbanking.database;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class secwarehouse {
	@Id
	private int question_ID;
	private String question;
	private int sitekeyID;
	private byte[] datablob;
	
	
	
	public secwarehouse(int question_ID, String question, int sitekeyID,
			byte[] datablob) {
		this.question_ID = question_ID;
		this.question = question;
		this.sitekeyID = sitekeyID;
		this.datablob = datablob;
	}
	public secwarehouse(){}
	public int getQuestion_ID() {
		return question_ID;
	}
	public void setQuestion_ID(int question_ID) {
		this.question_ID = question_ID;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getSitekeyID() {
		return sitekeyID;
	}
	public void setSitekeyID(int sitekeyID) {
		this.sitekeyID = sitekeyID;
	}
	public byte[] getDatablob() {
		return datablob;
	}
	public void setDatablob(byte[] datablob) {
		this.datablob = datablob;
	}
	
}
