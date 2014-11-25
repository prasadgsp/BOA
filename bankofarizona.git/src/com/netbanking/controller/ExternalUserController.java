package com.netbanking.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.netbanking.database.account;
import com.netbanking.database.authsender;
import com.netbanking.database.paymentrequest;
import com.netbanking.database.trandetails;
import com.netbanking.database.trandetails.TransStatus;
import com.netbanking.database.trandetails.TransType;
import com.netbanking.database.userinfo;
import com.netbanking.database.util.AccountDBHandler;
import com.netbanking.database.util.CertificateHandler;
import com.netbanking.database.util.DBConn;
import com.netbanking.database.util.LoginDBHandler;
import com.netbanking.database.util.TransDBHandler;
import com.netbanking.database.util.UserDBHandler;
import com.netbanking.exception.DBResult;
import com.netbanking.exception.StringEnvelope;
import com.netbanking.model.AddAuthorizationModel;
import com.netbanking.model.BillpayModel;
import com.netbanking.model.DebitCreditModel;
import com.netbanking.model.InitiatePaymentModel;
import com.netbanking.model.SignupModel;
import com.netbanking.model.TransferModel;
import com.netbanking.model.ViewAcctModel;
import com.netbanking.util.CustomLog;
import com.netbanking.util.Encryptor;
import com.netbanking.util.GetRole;
import com.netbanking.util.OTPGenerator;
import com.netbanking.util.XSSChecker;

@Controller
@RequestMapping("/")
public class ExternalUserController {
	CustomLog log = new CustomLog();

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/debitcreditotp", method = RequestMethod.GET)
	public String debitcreditotp(ModelMap model) {

		
		return "debitcreditotp";

	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/debitcredit", method = RequestMethod.GET)
	public String debitcredit( @ModelAttribute("DebitCreditModel") DebitCreditModel debitcredit,
	BindingResult result, ModelMap model, HttpServletRequest request) {

		String otp1=debitcredit.getTranstype();
		System.out.println(otp1);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = auth.getName();
		if(otp1.equals("Yes"))
		{
		OTPGenerator otp = LoginDBHandler.sendOTPToUser(userid);
		if (otp == null) {
			model.addAttribute("message",
					"Error occured while sending OTP");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		String new_otp=Encryptor.encodePwd(otp.getOTP());
		request.getSession().setAttribute("OTP",
				new StringEnvelope(new_otp));
		request.getSession().setAttribute("TimeStamp", otp.getTimeStamp());
		}
		
		List<account> acctlist=new ArrayList<account>();
		acctlist=AccountDBHandler.getAccounts(userid);
		if(acctlist==null) {
			model.addAttribute("message",
					"Error retrieving the user accounts. Possible cause: invalid account number.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}

		model.addAttribute("acctlist", acctlist);
		model.addAttribute("otp1",otp1);
		return "debitcredit";

	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public String transfer(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();

		List<account> acctlist = new ArrayList<account>();
		acctlist = AccountDBHandler.getAccounts(userid);
		if(acctlist==null) {
			model.addAttribute("message",
					"Error retrieving the user accounts. Possible cause: invalid account number.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}

		model.addAttribute("acctlist", acctlist);

		List<authsender> authList = new ArrayList<authsender>();
		authList = UserDBHandler.getAllAuthSenders(userid);
		if(authList==null){
			model.addAttribute("message", "Error retrieving user profiles.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		// System.out.println(authList.size());
		model.addAttribute("authList", authList);
		return "transfer";
	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/viewStmts", method = RequestMethod.GET)
	public String viewStmts(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		List<account> acctlist = new ArrayList<account>();
		acctlist = AccountDBHandler.getAccounts(userid);
		model.addAttribute("AcctModel", acctlist);

		return "viewStmts";

	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/viewStmts1", method = RequestMethod.POST)
	public String viewStmts1(
			@ModelAttribute("ViewAcctModel") ViewAcctModel acctitem,
			BindingResult result, ModelMap model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		List<account> acctlist = new ArrayList<account>();
		acctlist = AccountDBHandler.getAccounts(userid);
		if(acctlist==null) {
			model.addAttribute("message",
					"Error retrieving the user accounts. Possible cause: invalid account number.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		model.addAttribute("AcctModel", acctlist);
		int acct = acctitem.getAcctId();
		DBResult useracctflag = AccountDBHandler.checkAccount(userid, acct);
		if (useracctflag.isResult()) {
			List<trandetails> tranlist = new ArrayList<trandetails>();
			tranlist = TransDBHandler.getTrans(acct);
			if (tranlist==null || tranlist.size() == 0) {
				model.addAttribute("Error", "Invalid Account Number");
			} else {
				model.addAttribute("TransModel", tranlist);
			}
		} else {
			model.addAttribute("message", useracctflag.getMessage());
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";

		}
		return "viewStmts";

	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/debitCreditSuccess1", method = RequestMethod.POST)
	public String debitcreditfinal1(
			@ModelAttribute("DebitCreditModel") DebitCreditModel debitcredit,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		String flag1 = "";
		double debitamount=debitcredit.getAmount();
		if(debitamount<0.01)
		{
			flag1="Debit Amount less than minimum amount possible";
			model.addAttribute("message", flag1);
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		else if(debitamount>10000.00)
		{
			flag1="Debit Amount greater than $10000. OTP is needed";
		}
		else
		{
		int checkacct = debitcredit.getFromAcct();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		DBResult acctuserflag = AccountDBHandler
				.checkAccount(userid, checkacct);
		
		DBResult acctflag = TransDBHandler.chkValidAcct(debitcredit
				.getFromAcct());
		DBResult balflag = TransDBHandler.chkBalance(debitcredit.getFromAcct(),
				debitcredit.getAmount());
		if (acctflag.isResult() && balflag.isResult()
				&& acctuserflag.isResult() == true) {
			// int newtransId = TransDBHandler.getLastTrans() + 1;
			trandetails temptran = new trandetails();
			// temptran.setTransId(newtransId);
			temptran.setAmount(debitcredit.getAmount());
			temptran.setFromAcct(debitcredit.getFromAcct());
			temptran.setTransType(TransType.DEBIT);
			temptran.setTransStatus(TransStatus.COMPLETED);
			temptran.setToAcct(0);
			int account = debitcredit.getFromAcct();
			double oldbalance = AccountDBHandler.getBalance(account);
			if (oldbalance == -999.00) {
				String role = GetRole.roles();
				model.addAttribute("usertype", role);
				model.addAttribute("message",
						"Error retrieving account balance. Possible cause: invalid account number.");
				return "result";
			}
			double newbalance = oldbalance - debitcredit.getAmount();
			DBResult flag = new DBResult(false, "");
			DBResult balanceflag = AccountDBHandler.updateBalance(account,
					newbalance);
			if (balanceflag.isResult()) {
				flag = TransDBHandler.createTrans(temptran);
			}
			if (flag.isResult()) {
				Logger logger = log.getLogger(this);
				logger.warn("Debit Successfully completed for" + userid
						+ " for amount" + debitcredit.getAmount());

				flag1 = "Debit Successfully completed";
			} else {
				flag1 = "Transaction failed";
				Logger logger = log.getLogger(this);
				logger.warn("Debit Transaction failed for" + userid
						+ " for amount" + debitcredit.getAmount());

			}

		} else {
			flag1 = "Account does not exist or Balance not enough";
		}
		}
		model.addAttribute("message", flag1);
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		
		return "result";
	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/debitCreditSuccess3", method = RequestMethod.POST)
	public String debitcreditfinal3(
			@ModelAttribute("DebitCreditModel") DebitCreditModel debitcredit,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws InvalidInputException {	
		String flag1="";
		double debitamount=debitcredit.getAmount();
		if(debitamount<0.01)
		{
			flag1="Debit Amount less than minimum amount possible";
			model.addAttribute("message", flag1);
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		int checkacct = debitcredit.getFromAcct();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		StringEnvelope env = (StringEnvelope) request.getSession().getAttribute("OTP");
		
		String otp = env.getValue();
		String newOTP = XSSChecker.stripXSS(debitcredit.getOtp());
		long timeStamp = (Long) request.getSession().getAttribute("TimeStamp");
		OTPGenerator OTP = new OTPGenerator(otp, timeStamp);
		DBResult otpFlag = LoginDBHandler.checkOTP(userid, newOTP, OTP);
		model.addAttribute("flag", otpFlag.isResult());
		if (!otpFlag.isResult()) {
			model.addAttribute("message", otpFlag.getMessage());
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}	
		
	DBResult acctuserflag = AccountDBHandler
			.checkAccount(userid, checkacct);
	flag1 = "";
	DBResult acctflag = TransDBHandler.chkValidAcct(debitcredit
			.getFromAcct());
	DBResult balflag = TransDBHandler.chkBalance(debitcredit.getFromAcct(),
			debitcredit.getAmount());
	if (acctflag.isResult() && balflag.isResult()
			&& acctuserflag.isResult() == true) {
		// int newtransId = TransDBHandler.getLastTrans() + 1;
		trandetails temptran = new trandetails();
		// temptran.setTransId(newtransId);
		temptran.setAmount(debitcredit.getAmount());
		temptran.setFromAcct(debitcredit.getFromAcct());
		temptran.setTransType(TransType.DEBIT);
		temptran.setTransStatus(TransStatus.COMPLETED);
		temptran.setToAcct(0);
		int account = debitcredit.getFromAcct();
		double oldbalance = AccountDBHandler.getBalance(account);
		if (oldbalance == -999.00) {
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			model.addAttribute("message",
					"Error retrieving account balance. Possible cause: invalid account number.");
			return "result";
		}
		double newbalance = oldbalance - debitcredit.getAmount();
		DBResult flag = new DBResult(false, "");
		DBResult balanceflag = AccountDBHandler.updateBalance(account,
				newbalance);
		if (balanceflag.isResult()) {
			flag = TransDBHandler.createTrans(temptran);
		}
		if (flag.isResult()) {
			Logger logger = log.getLogger(this);
			logger.warn("Debit Successfully completed for" + userid
					+ " for amount" + debitcredit.getAmount());

			flag1 = "Debit Successfully completed";
		} else {
			flag1 = "Transaction failed";
			Logger logger = log.getLogger(this);
			logger.warn("Debit Transaction failed for" + userid
					+ " for amount" + debitcredit.getAmount());
		}

	} else {
		flag1 = "Account does not exist or Balance not enough";
	}
	model.addAttribute("message", flag1);
	String role = GetRole.roles();
	model.addAttribute("usertype", role);
	
	return "result";
}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/debitCreditSuccess2", method = RequestMethod.POST)
	public String debitcreditfinal2(
			@ModelAttribute("DebitCreditModel") DebitCreditModel debitcredit,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws InvalidInputException {
		String flag1="";
		double debitamount=debitcredit.getAmount();
		if(debitamount<0.01)
		{
			flag1="Credit Amount less than minimum amount possible";
			model.addAttribute("message", flag1);
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		else if(debitamount>10000.00)
		{
			flag1="Credit Amount greater than $10000. OTP is needed";
		}
		else
		{
		
		int checkacct = debitcredit.getFromAcct();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		DBResult acctuserflag = AccountDBHandler
				.checkAccount(userid, checkacct);
		//String flag1 = "";
		DBResult acctflag = TransDBHandler.chkValidAcct(debitcredit
				.getFromAcct());
		if (acctflag.isResult() && acctuserflag.isResult()) {
			// int newtransId = TransDBHandler.getLastTrans() + 1;
			trandetails temptran = new trandetails();
			// temptran.setTransId(newtransId);
			temptran.setAmount(debitcredit.getAmount());
			temptran.setFromAcct(debitcredit.getFromAcct());
			temptran.setTransType(TransType.CREDIT);
			temptran.setTransStatus(TransStatus.COMPLETED);
			temptran.setToAcct(0);
			int account = debitcredit.getFromAcct();
			double oldbalance = AccountDBHandler.getBalance(account);
			if (oldbalance == -999.00) {
				String role = GetRole.roles();
				model.addAttribute("usertype", role);
				model.addAttribute("message",
						"Error retrieving account balance. Possible cause: invalid account number.");
				return "result";
			}
			double newbalance = oldbalance + debitcredit.getAmount();
			DBResult flag = new DBResult(false, "");
			DBResult balanceflag = AccountDBHandler.updateBalance(account,
					newbalance);
			if (balanceflag.isResult()) {
				flag = TransDBHandler.createTrans(temptran);
			}
			if (flag.isResult()) {

				Logger logger = log.getLogger(this);
				logger.warn("Credit Successfully completed for" + userid
						+ " for amount" + debitcredit.getAmount());

				flag1 = "Credit Successfully completed";
			} else {

				Logger logger = log.getLogger(this);
				logger.warn("Credit Transaction failed for" + userid
						+ " for amount" + debitcredit.getAmount());

				flag1 = "Transaction failed";
			}

		} else {
			flag1 = "Account does not exist";
		}
		}
		model.addAttribute("message", flag1);
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		return "result";

	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/debitCreditSuccess4", method = RequestMethod.POST)
	public String debitcreditfinal4(
			@ModelAttribute("DebitCreditModel") DebitCreditModel debitcredit,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws InvalidInputException {	
		String flag1="";
		double debitamount=debitcredit.getAmount();
		if(debitamount<0.01)
		{
			flag1="Credit Amount less than minimum amount possible";
			model.addAttribute("message", flag1);
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		
		int checkacct = debitcredit.getFromAcct();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		StringEnvelope env = (StringEnvelope) request.getSession().getAttribute("OTP");
		String otp = env.getValue();
		String newOTP = XSSChecker.stripXSS(debitcredit.getOtp());
		long timeStamp = (Long) request.getSession().getAttribute("TimeStamp");
		OTPGenerator OTP = new OTPGenerator(otp, timeStamp);
		DBResult otpFlag = LoginDBHandler.checkOTP(userid, newOTP, OTP);
		model.addAttribute("flag", otpFlag.isResult());
		if (!otpFlag.isResult()) {
			model.addAttribute("message", otpFlag.getMessage());
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}	
		DBResult acctuserflag = AccountDBHandler
				.checkAccount(userid, checkacct);
		flag1 = "";
		DBResult acctflag = TransDBHandler.chkValidAcct(debitcredit
				.getFromAcct());
		if (acctflag.isResult() && acctuserflag.isResult()) {
			// int newtransId = TransDBHandler.getLastTrans() + 1;
			trandetails temptran = new trandetails();
			// temptran.setTransId(newtransId);
			temptran.setAmount(debitcredit.getAmount());
			temptran.setFromAcct(debitcredit.getFromAcct());
			temptran.setTransType(TransType.CREDIT);
			temptran.setTransStatus(TransStatus.COMPLETED);
			temptran.setToAcct(0);
			int account = debitcredit.getFromAcct();
			double oldbalance = AccountDBHandler.getBalance(account);
			if (oldbalance == -999.00) {
				String role = GetRole.roles();
				model.addAttribute("usertype", role);
				model.addAttribute("message",
						"Error retrieving account balance. Possible cause: invalid account number.");
				return "result";
			}
			double newbalance = oldbalance + debitcredit.getAmount();
			DBResult flag = new DBResult(false, "");
			DBResult balanceflag = AccountDBHandler.updateBalance(account,
					newbalance);
			if (balanceflag.isResult()) {
				flag = TransDBHandler.createTrans(temptran);
			}
			if (flag.isResult()) {

				Logger logger = log.getLogger(this);
				logger.warn("Credit Successfully completed for" + userid
						+ " for amount" + debitcredit.getAmount());

				flag1 = "Credit Successfully completed";
			} else {

				Logger logger = log.getLogger(this);
				logger.warn("Credit Transaction failed for" + userid
						+ " for amount" + debitcredit.getAmount());

				flag1 = "Transaction failed";
			}

		} else {
			flag1 = "Account does not exist";
		}
		model.addAttribute("message", flag1);
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
	
	return "result";
}
	
	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/transferSuccess", method = RequestMethod.POST)
	public String transferfinal(
			@ModelAttribute("TransferModel") TransferModel transfer,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws InvalidInputException {
		
		MultipartFile pubKeyFile = null;
		DBResult isSignVerified = new DBResult(false, "");

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();

		String role = GetRole.roles();
		model.addAttribute("usertype", role);

		try {

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			pubKeyFile = multipartRequest.getFile("secretKeyfile");
		} catch (Exception e) {
			model.addAttribute("message", "Upload File size limit exceeded..");
			return "result";
		}

		if ( pubKeyFile.getSize() == 0) {
			model.addAttribute("message", "Please upload valid Public Key File.");
			return "result";
		}

		try {

			// sign the data with given key
			//byte[] certiByteArray = certiFile.getBytes();
			byte[] pubByteArray = pubKeyFile.getBytes();
			// get public key from file
			String temporaryPath = System.getProperty("catalina.home") +"/Certificates/";
			String priPath = temporaryPath+userid + "_privateKey";
			byte[] privateKeyBytes = IOUtils.toByteArray(new FileInputStream(
					new File(priPath)));
			String certiPath = temporaryPath+"GROUP9.crt";
			byte[] certiByteArray = IOUtils.toByteArray(new FileInputStream(
					new File(certiPath)));
			isSignVerified = CertificateHandler.verifySignature(privateKeyBytes,
					pubByteArray, certiByteArray);

			if (isSignVerified.isResult()) {
				if (request.getSession() == null) {
					return "homePage";
				} else {
		
					DBResult flag = new DBResult(false, "");
					int checkacct = transfer.getFromAcct();
					DBResult acctuserflag = AccountDBHandler
							.checkAccount(userid, checkacct);
					String flag1 = "";
					DBResult fromacctflag = TransDBHandler.chkValidAcct(transfer
							.getFromAcct());
					DBResult toacctflag = TransDBHandler.chkValidAcct(transfer.getToAcct());
					DBResult toacctauthflag=AccountDBHandler.checkAuthCombo(userid, transfer.getToAcct());
					
					if (transfer.getFromAcct() == transfer.getToAcct()) {
						fromacctflag.setResult(false);
					}
					DBResult balflag = TransDBHandler.chkBalance(transfer.getFromAcct(),
							transfer.getAmount());
					if (fromacctflag.isResult() && toacctflag.isResult()
							&& balflag.isResult() && acctuserflag.isResult() &&toacctauthflag.isResult()) {
						// int newtransId = TransDBHandler.getLastTrans() + 1;
						trandetails temptran = new trandetails();
						// temptran.setTransId(newtransId);
						temptran.setAmount(transfer.getAmount());
						temptran.setFromAcct(transfer.getFromAcct());
						temptran.setToAcct(transfer.getToAcct());
						temptran.setTransType(TransType.TRANSFER);
						temptran.setTransStatus(TransStatus.PENDING);
						int account = transfer.getFromAcct();
						double transamt = transfer.getAmount();
						if (transamt < 1000) {
							temptran.setTransStatus(TransStatus.COMPLETED);
							double oldbalance = AccountDBHandler.getBalance(account);
							if (oldbalance == -999.00) {
								model.addAttribute("message",
										"Error retrieving account balance. Possible cause: invalid account number.");
								model.addAttribute("usertype", role);
								return "result";
							}
							double newbalance = oldbalance - transfer.getAmount();
							flag = new DBResult(false, "");
							DBResult balanceflag1 = AccountDBHandler.updateBalance(account,
									newbalance);
							DBResult balanceflag2 = new DBResult(false, "");
							if (balanceflag1.isResult()) {
								account = transfer.getToAcct();
								oldbalance = AccountDBHandler.getBalance(account);
								newbalance = oldbalance + transfer.getAmount();
								balanceflag2 = AccountDBHandler.updateBalance(account,
										newbalance);
								balanceflag2.setResult(true);
							}

							if (balanceflag2.isResult()) {
								flag = TransDBHandler.createTrans(temptran);
							}
						} else {
							flag = TransDBHandler.createTrans(temptran);
						}
						if (flag.isResult()) {
							flag1 = "Transfer Successfully completed";
						} else {

							Logger logger = log.getLogger(this);
							logger.warn(" Transaction failed for" + userid + " for amount"
									+ transfer.getAmount() + "from account"
									+ transfer.getFromAcct());

							flag1 = "Transaction failed";
						}

					} else {
						flag1 = "From/To Account does not exist or Balance not enough to carry out transaction";
					}
					model.addAttribute("message", flag1);
					return "result";
				}
			} else {
				model.addAttribute("message", "Invalid Public key file");
				return "result";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Exception occured while validating public key");
			return "result";
		}
	}

	// Add Authorized User
	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/addAuthorization", method = RequestMethod.GET)
	public String addAuthorization(ModelMap model) {
		return "addAuthorization";
	}

	// add authorized user
	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/addAuthorizedUser", method = RequestMethod.POST)
	public String addAuthorizedUser(
			@ModelAttribute("AddAuthorizationModel") AddAuthorizationModel addauthuser,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws InvalidInputException {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		int acct=addauthuser.getaccountno();
		account acc=AccountDBHandler.getAccount(acct);
		if(acc==null) {
			model.addAttribute("message",
					"Error retrieving the user accounts. Possible cause: invalid account number.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		DBResult res=AccountDBHandler.checkAuthCombo(userid,acct);
		if(!res.isResult())
		{
		if(acc.getAccStatus().toString().equalsIgnoreCase("CLOSED"))
		{
			String fail_message = "Account does not exist";
			model.addAttribute("message", fail_message);
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
	
		String emailID = XSSChecker.stripXSS(addauthuser.getEmail());
		String userDetails = UserDBHandler.addAuthorizedUser(emailID,
				addauthuser.getaccountno(), userid);
		if (userDetails != null) {
			String success_message = "User Authorized!!";

			model.addAttribute("message", success_message);

		} else {
			String fail_message = "Error! User is not authorized successfully";
			model.addAttribute("message", fail_message);
		}
		}
		else
		{
			String acctexists_message = "Error! Account already authorized for user";
			model.addAttribute("message", acctexists_message);
			
		}
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		
		return "result";

	}

	@PreAuthorize("hasRole('MERCHANT')")
	@RequestMapping(value = "/InitiatePayment", method = RequestMethod.GET)
	public String InitiatePayment(ModelMap model) {

		return "InitiatePayment";

	}

	@PreAuthorize("hasAnyRole('MERCHANT')")
	@RequestMapping(value = "/InitiatePaymentDone", method = RequestMethod.GET)
	public String InitiatePaymentDone(
			@ModelAttribute("InitiatePaymentModel") InitiatePaymentModel InitiatePayment,
			BindingResult result, ModelMap model, HttpServletRequest request) {

		String custEmail = InitiatePayment.getEmail();
		String paymentDesc = InitiatePayment.getDesc();
		double paymentAmt = InitiatePayment.getAmount();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String merchantID = auth.getName();

		userinfo temp = LoginDBHandler.checkMailIdExists(custEmail);

		if (temp == null) {
			model.addAttribute("message", "email ID not found in database!");
		} else {

			DBResult flag = AccountDBHandler.insertPaymentDetails(custEmail, merchantID,
					paymentAmt, paymentDesc);

			model.addAttribute("message", flag.getMessage());
		}
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		return "result";

	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/Billpay", method = RequestMethod.GET)
	public String homePage(
			@ModelAttribute("InitiatePaymentModel") SignupModel InitiatePaymentModel,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();

		List<paymentrequest> pendingpayments = new ArrayList<paymentrequest>();

		pendingpayments = AccountDBHandler.retrievePaymentDetails(userid);
		if (pendingpayments == null) {
			modelMap.addAttribute("message", "No Pending payments");
			String role = GetRole.roles();
			modelMap.addAttribute("usertype", role);
			return "result";
		}
		modelMap.addAttribute("InitiatePaymentModel", pendingpayments);

		List<account> acctlist=new ArrayList<account>();
		acctlist=AccountDBHandler.getAccounts(userid);
		modelMap.addAttribute("acctlist", acctlist);
		return "Billpay";
	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/BillpaySuccess", method = RequestMethod.POST)
	public String authorizeTransactions(
			@ModelAttribute("BillpayModel") BillpayModel BillpayModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {

		int cust_accID = BillpayModel.getFromAcct();
		String[] paymentStatus = BillpayModel.getPaymentStatusString().split(
				",");
		String[] paymentIDs = BillpayModel.getPaymentIDs().split(",");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String custId = auth.getName();

		for (int i = 1; i < paymentStatus.length; i++) {
			System.out.println(paymentStatus[i]);
			System.out.println(paymentIDs[i]);
			switch (paymentStatus[i]) {
			case "true":
				
				int recipient_accID = AccountDBHandler
						.getMerchantAccountID(Integer.parseInt(paymentIDs[i]));
				double paymentamt = AccountDBHandler.getPaymentAmt(Integer
						.parseInt(paymentIDs[i]));

				// deduct amount from customer account
				double sender_oldbal = AccountDBHandler.getBalance(cust_accID);
				if (sender_oldbal == -999.00) {
					model.addAttribute("message",
							"Error retrieving account balance. Possible cause: invalid account number.");
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					return "result";
				}
				double sender_newbal = sender_oldbal - paymentamt;
				DBResult balanceflag = AccountDBHandler.updateBalance(
						cust_accID, sender_newbal);

				if (!balanceflag.isResult()) {
					model.addAttribute("message", balanceflag.getMessage());
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					return "result";
				}

				// add amount to merchant account
				double recipient_oldbal = AccountDBHandler
						.getBalance(recipient_accID);
				if (recipient_oldbal == -999.00) {
					model.addAttribute("message",
							"Error retrieving account balance. Possible cause: invalid account number.");

					break;
				}
				double recipient_newbal = recipient_oldbal + paymentamt;
				DBResult balanceflag2 = AccountDBHandler.updateBalance(
						recipient_accID, recipient_newbal);

				if (!balanceflag2.isResult()) {
					model.addAttribute("message", balanceflag2.getMessage());
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					return "result";
				}

				DBResult dbr = AccountDBHandler.updatePaymentStatus(
						Integer.parseInt(paymentIDs[i]), TransStatus.COMPLETED);
				if (!dbr.isResult()) {
					model.addAttribute("message", dbr.getMessage());
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					return "result";
				}
				Session session=null;
				try
				{
					session=DBConn.getSessionFactory().openSession();
					if (session == null) {
						model.addAttribute("message", "Error in Session.Try Again");
						String role = GetRole.roles();
						model.addAttribute("usertype", role);
						return "result";
					}
				Transaction trans=session.beginTransaction();
				trandetails tran=new trandetails();
				tran.setFromAcct(cust_accID);
				tran.setToAcct(recipient_accID);
				tran.setAmount(paymentamt);
				tran.setTransStatus(TransStatus.COMPLETED);
				tran.setTransType(TransType.TRANSFER);
				Date date = new Date();
				tran.setTimestamp(date);
				session.save(tran);
				trans.commit();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					model.addAttribute("message",
							"Database Error");
					return "result";
				}
				finally{
					session.flush();
					session.close();
				}
				model.addAttribute("message",
						"Payment Successfully Completed");
				break;

			case "false":
				DBResult dbr2 = AccountDBHandler.updatePaymentStatus(
						Integer.parseInt(paymentIDs[i]), TransStatus.REJECTED);
				if (!dbr2.isResult()) {
					model.addAttribute("message", dbr2.getMessage());
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					return "result";
				}
				model.addAttribute("message",
						"Option to pay Declined");
				break;

			case "none":
				model.addAttribute("message",
						"Payment No action");
				break;
			}
		}
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		return "result";
	}


}
