package com.netbanking.exception;

public class BOAZException extends Exception {

	private static final long serialVersionUID = 2180348972548317896L;

	public BOAZException() {
		super();
	}

	public BOAZException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		System.out.println("Exception Message: "+arg0);
		System.out.println("Exception Trace: "+arg1.getStackTrace());
	}

	public BOAZException(String arg0) {
		super(arg0);
		System.out.println("Exception Message: "+arg0);
	}

	public BOAZException(Throwable arg0) {
		super(arg0);
		System.out.println("Exception Trace: "+arg0.getStackTrace());
	}

	@Override
	public String toString() {
		return "[" + getMessage() + ", " + getClass() + "]";
	}
}
