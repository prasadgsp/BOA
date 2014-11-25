package com.netbanking.database.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.netbanking.exception.DBResult;

import sun.security.pkcs.PKCS10;
import sun.security.x509.X500Name;

public class CertificateHandler {
	private static PublicKey publicKey = null;
	private static PrivateKey privateKey = null;
	private static KeyPairGenerator keyGen = null;
	private static CertificateHandler CH = null;

	private CertificateHandler() {
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyGen.initialize(2048, new SecureRandom());
		KeyPair keypair = keyGen.generateKeyPair();
		publicKey = keypair.getPublic();
		privateKey = keypair.getPrivate();
	}

	public static CertificateHandler getInstance() {
		if (CH == null)
			CH = new CertificateHandler();
		return CH;
	}

	
	public PublicKey getPublicKey() {
		return publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public static byte[] GenerateSignaturedData(byte[] certificate,
			byte[] privKey) {
		byte[] signature = null;
		try {
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privKey);
			KeyFactory kf = KeyFactory.getInstance("DSA", "SUN");
			PrivateKey privateKey = kf.generatePrivate(spec);
			Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
			dsa.initSign(privateKey);
			dsa.update(certificate, 0, certificate.length);
			signature = dsa.sign();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			signature = null;
		}
		return signature;
	}

	public static byte[] generateCertificate(PublicKey publicKey, PrivateKey privKey) {
		byte[] certificate = null;
		try {
			String sigAlg = "SHA1withDSA";
			PKCS10 pkcs10 = new PKCS10(publicKey);
			Signature signature = Signature.getInstance(sigAlg);
			signature.initSign(privKey);

			X500Name x500Name = new X500Name("UnitedBankOfArizona", "ASU",
					"ASU", "Tempe", "Arizona", "US");
			pkcs10.encodeAndSign(x500Name, signature);
			certificate = pkcs10.getEncoded();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			certificate = null;
		}
		return certificate;
	}


	public static DBResult verifySignature(byte[] privateKeyByteArray,byte[] publicKeyBytes,byte[] certiByteArray){
		boolean isVerified = false;
		try {
			
			byte[] encodedSign = null;

			if (certiByteArray != null && privateKeyByteArray != null){ 
				encodedSign = CertificateHandler.GenerateSignaturedData(
						certiByteArray, privateKeyByteArray);}
			else {
				return new DBResult(false,"Error in encoding the certificate.Please provide valide files");
			}
			if (encodedSign != null) {
				X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(
						publicKeyBytes);

				KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
				PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

				// create a Signature object and initialize it with the public
				// key
				Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
				sig.initVerify(pubKey);

				// Update and verify the data
				sig.update(certiByteArray, 0, certiByteArray.length);
				isVerified = sig.verify(encodedSign);
			}
		} catch (Exception e) {
			return new DBResult(false,"Error occured while validating certificate");
		}
		return new DBResult(isVerified,"Certificate validation status"+isVerified);
	}

}
