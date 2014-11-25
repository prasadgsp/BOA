package com.netbanking.exception;

public class StringEnvelope {
	private String value;

	public StringEnvelope(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
