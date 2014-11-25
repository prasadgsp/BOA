package com.netbanking.database.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DBConn {

	private static SessionFactory SF = null;
	
	private static SessionFactory createNewFactory(){
		try{
			return new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex){
			System.err.print("Failed to create Initial SessionFactory." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		if(SF == null) {
			SF = createNewFactory();
		}
				return SF;
	}
	
	
}
