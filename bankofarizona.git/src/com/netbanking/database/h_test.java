package com.netbanking.database;

import java.io.File;
import java.io.FileInputStream;

import org.hibernate.Session;

import com.netbanking.database.userinfo.UserStatus;
import com.netbanking.database.userinfo.UserType;
import com.netbanking.database.util.CertificateHandler;
import com.netbanking.database.util.UserDBHandler;
import com.netbanking.util.OTPGenerator;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;




public class h_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	/*	SessionFactory SF = new Configuration().configure().buildSessionFactory();
		Session session = SF.openSession();
		session.beginTransaction();*/
	
		//session.save(usr);
		//session.getTransaction().commit();
		//session.close();
		//userinfo usr2 = new userinfo();
		//usr2 = (userinfo)session.get(userinfo.class, "Arpan");
		
	/*	userinfo usr = new userinfo();
		account acc = new account();
		authsender auth = new authsender();
		//sitekeywarehouse secW = new sitekeywarehouse();
		//quwarehouse QW = new quwarehouse(); 
		systemlog sysL = new systemlog();
		trandetails tr = new trandetails();
		usersec uSec = new usersec();
		piidbrequest pii = new piidbrequest();
		//secwarehouse sec = new secwarehouse();
		paymentrequest pym = new paymentrequest();
		session.getTransaction().commit();
		session.close();*/
		
		//LoginDBHandler ld = new LoginDBHandler();
		//System.out.println(ld.LoginCheck("Arpan", "123"));
		/*DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		userinfo usr = new userinfo("Prachi","123","ABC","XYZ","123456789",UserType.CUSTOMER,"Uni Dr",date,"abc@gmail",1234567890,Gender.MALE,UserStatus.LIVE,0);
		boolean flag = UserDBHandler.userSignUp(usr);
		System.out.println(flag);*/
		//String abc = Encryptor.codeSSN("123456789");
		//System.out.println(abc);
		//System.out.println(Encryptor.codeSSN(abc));
		
		/*DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		userinfo usr = new userinfo("Arpan","ABC","XYZ","123456789",UserType.CUSTOMER,"Uni Dr",date,"arpan.here.i.am@gmail.com",1234567890,Gender.MALE,UserStatus.LIVE,0);
		usersec uSec = new usersec("Arpan","123","Q1","ABC","Q2","DEF","Q3","GHT",0);
		account acc=new account(1000,"Arpan",date,date,1000,AccountStatus.OPEN,AccountType.CHECKING);
		boolean flag = UserDBHandler.userSignUp(usr,uSec,acc);*/

		/*quwarehouse QW = new quwarehouse("Q1","What is your mother's maiden name");
		quwarehouse QW2 = new quwarehouse("Q2","What is your pet's name");
		quwarehouse QW3 = new quwarehouse("Q3","What is your favourite colour");
		quwarehouse QW4 = new quwarehouse("Q4","What is your first car's model");
		quwarehouse QW5 = new quwarehouse("Q5","What is your favourite movie");
		session.save(QW);
		session.save(QW2);
		session.save(QW3);
		session.save(QW4);
		session.save(QW5);
		session.getTransaction().commit();
		session.close();*/ 
		/*account acc = new account();
		acc.setAccNum(AccountDBHandler.getLastAcctNum()+1);
		acc.setAccStatus(AccountStatus.OPEN);
		acc.setDateOfClose(null);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		acc.setDateOfOpen(date);
		acc.setAccType(AccountType.SAVING);
		acc.setBalance(555);
		acc.setUserId("Priyanka");
		session.save(acc);
		session.getTransaction().commit();
		session.close();*/
		/*Query query=session.createQuery("update account set balance= balance + :amt where accNum = :num");
		query.setParameter("num",12345679);
		query.setParameter("amt",100.00);
		int r=query.executeUpdate();
		session.getTransaction().commit();*/
		//System.out.println(UserDBHandler.addAuthorizedUser("priya.cummins@gmail.com", 12345678, "Amol"));
		try{
			/*OTPGenerator OTP = new OTPGenerator();
			OTP.sendCertificate("Abc", "arpan.here.i.am@gmail.com",12345678);*/
			
		/*System.out.println("Verifying");
		byte[] publicKeyBytes = IOUtils.toByteArray(new FileInputStream(new File("x_publicKey")));
		byte[] privateKeyBytes = IOUtils.toByteArray(new FileInputStream(new File("Arpan_privateKey")));
		byte[] certBytes = IOUtils.toByteArray(new FileInputStream(new File("x_cert.crt")));
		
		System.out.println(CertificateHandler.verifySignature(privateKeyBytes, publicKeyBytes, certBytes));*/
        
		}catch(Exception e){
			
		}
		
		userinfo user = new userinfo();
		usersec usec = new usersec();
		user.setUserId("arpan");
		user.seteMail("arpan.ghosh@asu.edu");
		user.setUserType(UserType.MANAGER);
		user.setUserStatus(UserStatus.LIVE);
		user.setSsn("123456789");
		usec.setEnabled(true);
		usec.setUserId("arpan");
		usec.setAns1("A1");
		usec.setAns2("A2");
		usec.setAns3("A3");
		usec.setQues1("Q1");
		usec.setQues2("Q2");
		usec.setQues3("Q3");
		usec.setPasswd("ead5642f9585bf046d8fd2f7bd4465d0");
		System.out.println(UserDBHandler.userSignUp(user, usec, null).isResult());
	}

}

