package com.netbanking.database;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class quwarehouse {
	@Id
	private String question_ID;
	private String question;
	public String getQuestion_ID() {
		return question_ID;
	}
	public void setQuestion_ID(String question_ID) {
		this.question_ID = question_ID;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public quwarehouse(String question_ID, String question) {
		this.question_ID = question_ID;
		this.question = question;
	}
	public quwarehouse(){}
	
}
