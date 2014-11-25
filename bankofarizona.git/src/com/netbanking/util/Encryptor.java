package com.netbanking.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.netbanking.database.usersec;
import com.netbanking.database.util.DBConn;


public class Encryptor implements PasswordEncoder {
	
	private static int SSNSALT = 10312014;
	
	public Encryptor(){
		
	}
	
	synchronized public boolean validateUser(String password, String passwordDB)
			throws SQLException, NoSuchAlgorithmException 
	{
		// INPUT VALIDATION
		if (password == null || passwordDB == null) 
		{
			return false;
		}
		return matches(password, passwordDB);
	}
	
	public static byte[] hashgenerator(int number_iteration, String password, byte[] salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		MessageDigest hashVal = MessageDigest.getInstance("SHA-1");
		hashVal.reset();
		hashVal.update(salt);
		
		byte[] input = hashVal.digest(password.getBytes("UTF-8"));
		
		for (int i = 0; i < number_iteration; i++) 
		{
			hashVal.reset();
			input = hashVal.digest(input);
		}
		
		return input;
	}
	
	//Encode passwd
	public static String encodePwd(String pwd){
		
		/*try{
		byte[] bSalt = Base64.decodeBase64(SALT);
		byte[] bEnteredPwd = hashgenerator(NO_OF_ITERATIONS, pwd, bSalt);
		sPwd=Base64.encodeBase64String(bEnteredPwd);
		} catch (Exception e){
			
		}*/
		String hashedPassword = "";
		int i = 0;
		while (i < 10) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		hashedPassword = passwordEncoder.encode(pwd);
		i++;
		}
		return hashedPassword;
	}
	
	// Encode & Decode ssn
	public static String codeSSN(String ssn){
		int numSsn=Integer.parseInt(ssn);
		int xorVal = numSsn^SSNSALT;
		String sSSN = Integer.toString(xorVal);
		return sSSN;
	}
	
	//Test method to enter hashed pwd in DB
	private static String updateHashPwd(String userName,String pwd)
	{
		Session session=DBConn.getSessionFactory().openSession();
		Query query=session.createQuery("from usersec where userId= :userid");
		query.setParameter("userid",userName);
		usersec user=(usersec)query.uniqueResult();
		String sPwd="";
		
		sPwd = encodePwd(pwd);
		user.setPasswd(sPwd);
		System.out.println("User retrieved"+user.getPasswd());
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
		return sPwd;
	}
	
	
	public static void main(String[] args)
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String login, password;
		try 
		{
			System.out.println("Please enter username: ");
			login = br.readLine();
			System.out.println("Please enter password: ");
			password = br.readLine();
			
			
			String newPwd =Encryptor.updateHashPwd(login, password);
			System.out.println("Hashed pwd: "+newPwd);
			
			
			//Boolean authenticated = EncryptDigest.authenticateUser(password, PASSWORD, SALT);
			//System.out.println("Authenticated = " + authenticated);
			
		}
		catch (IOException  e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public String encode(CharSequence arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean matches(CharSequence arg0, String arg1) {
		return BCrypt.checkpw(arg0.toString(), arg1);
	}
	

}
