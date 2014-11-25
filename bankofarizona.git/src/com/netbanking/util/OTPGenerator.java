package com.netbanking.util;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.netbanking.database.util.CertificateHandler;
import com.netbanking.exception.DBResult;

public class OTPGenerator {
	public OTPGenerator(String oTP, long timeStamp) {
		OTP = oTP;
		this.timeStamp = timeStamp;
	}

	public OTPGenerator() {
	}

	private SecureRandom rand = new SecureRandom();
	private String OTP = new BigInteger(39, rand).toString();
	private long timeStamp = System.currentTimeMillis();

	public String getOTP() {
		return OTP;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	private Session mailSetup() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"unitedbankofarizona@gmail.com", "SSGROUP9");
					}
				});

		return session;
	}

	public void sendOTP(String emailId) {
		try {
			Session session = this.mailSetup();

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("unitedbankofarizona@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailId));
			message.setSubject("OTP for your UBA account: " + OTP);

			message.setText(OTP);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendPII(int piiNum, String emailId, String ssn,
			boolean ifAuthorized) {
		try {
			Session session = this.mailSetup();

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("unitedbankofarizona@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailId));
			if (ifAuthorized) {
				message.setSubject("Your PII request: " + piiNum
						+ " has been authorized");
				message.setText("PII request no: " + piiNum + " PII: " + ssn);
				Transport.send(message);
			}

			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public DBResult sendCertificate(String userId, String emailId, int accNum) {

		try {

			KeyPairGenerator keyGen = null;
			keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
			keyGen.initialize(1024, new SecureRandom());
			KeyPair keypair = keyGen.generateKeyPair();
			String temporaryPath = System.getProperty("catalina.home") +"/Certificates/";
			String certiPath = temporaryPath + userId + "_cert.crt", publicKeyPath = temporaryPath
					+ userId + "_publicKey", privateKeyPath = temporaryPath
					+ userId + "_privateKey";
			// generate certificate
			byte[] certiByteArray = CertificateHandler.generateCertificate(
					keypair.getPublic(), keypair.getPrivate());
			File cFile = new File(certiPath);
			FileOutputStream certificatefile = new FileOutputStream(cFile,false);
			certificatefile.write(certiByteArray);
			certificatefile.close();

			
			File pubFile = new File(publicKeyPath);
			FileOutputStream publickeyFile = new FileOutputStream(pubFile,false);

			publickeyFile.write(keypair.getPublic().getEncoded());
			publickeyFile.close();
			
			File priFile = new File(privateKeyPath);
			FileOutputStream privatekeyFile = new FileOutputStream(
					priFile,false);
			privatekeyFile.write(keypair.getPrivate().getEncoded());
			privatekeyFile.close();

			Session session = this.mailSetup();

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("unitedbankofarizona@gmail.com"));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailId));

			message.setSubject("United Bank of Arizona: Your certificate for monetary transaction");

			MimeBodyPart messagePart = new MimeBodyPart();
			if(accNum != 0){
			messagePart
					.setText("Dear Customer,"
							+ "\n\n Your bank account "+accNum+" has been created. We have attached your Digital Certificate and Public and Private keys. Please save the files and use them while transferring money  \n\n "
							+ "\n Regards, \n The United Bank of Arizona");
			} 
			
			else
			{
				
				messagePart
				.setText("Dear Employee your internal account has been created.");
			} 
			MimeBodyPart attachmentCerti = new MimeBodyPart();
			// Certificate
			FileDataSource fileDataSource = new FileDataSource(certiPath) {
				@Override
				public String getContentType() {

					return "application/octet-stream";
				}
			};

			attachmentCerti.setDataHandler(new DataHandler(fileDataSource));
			attachmentCerti.setFileName(fileDataSource.getName());

			// Public Key
			MimeBodyPart attachmentPubKey = new MimeBodyPart();
			FileDataSource fileDataSourcePK = new FileDataSource(publicKeyPath) {
				@Override
				public String getContentType() {

					return "application/octet-stream";
				}
			};

			attachmentPubKey.setDataHandler(new DataHandler(fileDataSourcePK));
			attachmentPubKey.setFileName(fileDataSourcePK.getName());

			// Private Key
			MimeBodyPart attachmentPriKey = new MimeBodyPart();
			FileDataSource fileDataSourcePR = new FileDataSource(privateKeyPath) {
				@Override
				public String getContentType() {

					return "application/octet-stream";
				}
			};

			attachmentPriKey.setDataHandler(new DataHandler(fileDataSourcePR));
			attachmentPriKey.setFileName(fileDataSourcePR.getName());

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messagePart);

			multipart.addBodyPart(attachmentCerti);
			multipart.addBodyPart(attachmentPubKey);
			multipart.addBodyPart(attachmentPriKey);

			message.setContent(multipart);

			Transport.send(message);
			pubFile.delete();
			cFile.delete();
			
			return new DBResult(true,"Certificate sent successfully");

		} catch (Exception e) {
			return new DBResult(false, "Failed to generate certificate");
		}

	}

}
