package com.netbanking.database;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class sitekeywarehouse {
	@Id
	private int sitekeyID;
	private byte[] datablob;
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
	public sitekeywarehouse(int sitekeyID, byte[] datablob) {
		this.sitekeyID = sitekeyID;
		this.datablob = datablob;
	}
	public sitekeywarehouse(){}
}
