package com.netbanking.model;

import java.util.Date;

public class AuditTranscationsModel {
	String filename;
	Date date;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
