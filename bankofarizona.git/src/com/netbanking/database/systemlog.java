package com.netbanking.database;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class systemlog {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int logId;
	
	private String userID;
	private String description;
	@Enumerated(EnumType.STRING)
	private Severity severity;
	private Date timestamp;
	
	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public enum Severity {
		INFO,
		DEBUG,
		WARN,
		ERROR,
		FATAL;

	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public systemlog(int logId, String userID, String description,
			Severity severity, Date timestamp) {
		this.logId = logId;
		this.userID = userID;
		this.description = description;
		this.severity = severity;
		this.timestamp = timestamp;
	}

	public systemlog(){}
	
}



