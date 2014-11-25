package com.netbanking.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netbanking.util.*;
import com.netbanking.database.account;
import com.netbanking.database.account.AccountType;
import com.netbanking.database.trandetails;
import com.netbanking.database.userinfo;
import com.netbanking.database.userinfo.Gender;
import com.netbanking.database.userinfo.UserStatus;
import com.netbanking.database.userinfo.UserType;
import com.netbanking.database.usersec;
import com.netbanking.database.util.AccountDBHandler;
import com.netbanking.database.util.LoginDBHandler;
import com.netbanking.database.util.PiiHandler;
import com.netbanking.database.util.TransDBHandler;
import com.netbanking.database.util.UserDBHandler;
import com.netbanking.exception.DBResult;
import com.netbanking.exception.StringEnvelope;
import com.netbanking.model.AccessPIIModel;
import com.netbanking.model.ForgotPasswordModel;
import com.netbanking.model.LoginModel;
import com.netbanking.model.ResetPasswordModel;
import com.netbanking.model.SignupModel;
import com.netbanking.util.CustomLog;
import com.netbanking.util.GetRole;
import com.netbanking.util.OTPGenerator;
import com.netbanking.model.*;

@Controller
@RequestMapping("/")
public class LoginController {
	CustomLog log = new CustomLog();

	private String SALT = "GROUP9";

	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			   if (role.equals("MANAGER") ||role.equals("EMPLOYEE")) {
		    /* The user is logged in :) */
			List<trandetails> pendingTransactions = new ArrayList<trandetails>();
			pendingTransactions = TransDBHandler.getFirstTenPendingTrans();
			model.addAttribute("pendingTransactions", pendingTransactions);
			return "internalHomePage";
			   }
			   
			   else
			   {
		String userid = auth.getName();
		List<account> acctlist = new ArrayList<account>();
		acctlist = AccountDBHandler.getAccounts(userid);
		model.addAttribute("AcctModel", acctlist);
		return "externalHomePage";
				   
			   }
		}
		
		
		model.addAttribute("salt", SALT);
		return "homePage";
	}

	@RequestMapping(value = "/accountLocked", method = RequestMethod.GET)
	public String accountLocked(ModelMap model) {
		return "accountLocked";
	}
	
	@RequestMapping(value = "/sessionError", method = RequestMethod.GET)
	public String sessionError(ModelMap model) {
		return "sessionError";
	}
	
	
	
	@RequestMapping(value = "/homePageError", method = RequestMethod.GET)
	public String homePageError(ModelMap model) {
		return "homePageError";
	}
	
	@RequestMapping(value = "/deadUser", method = RequestMethod.GET)
	public String deadUser(ModelMap model) {
		return "deadUser";
	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT','MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@ModelAttribute("LoginModel") LoginModel loginModel,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {
		request.getSession().invalidate();
		return "logout";
	}

	@RequestMapping(value = "/sessionTimeout", method = RequestMethod.GET)
	public String sessionTimeout(ModelMap model) {
		return "sessionTimeout";
	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT')")
	@RequestMapping(value = "/externalHomePage", method = RequestMethod.GET)
	public String externalHomePage(ModelMap model) {
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
		return "externalHomePage";
	}

	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/internalHomePage", method = RequestMethod.GET)
	public String internalHomePage(ModelMap model) {
		List<trandetails> pendingTransactions = new ArrayList<trandetails>();
		pendingTransactions = TransDBHandler.getFirstTenPendingTrans();
		if(pendingTransactions == null) {
			model.addAttribute("message", "Error retrievng Pending Transactions.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		model.addAttribute("pendingTransactions", pendingTransactions);
		return "internalHomePage";
	}

	@RequestMapping(value = "/accessPII", method = RequestMethod.GET)
	public String accessPII(ModelMap model) {
		model.addAttribute("message", "Welcome to Bank of Arizona");
		return "accessPII";
	}

	@RequestMapping(value = "/accessPIISuccess", method = RequestMethod.POST)
	public String accessPIISuccess(
			@ModelAttribute("AccessPIIModel") AccessPIIModel accessPIIModel,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {
		String firstName = XSSChecker.stripXSS(accessPIIModel.getFirstname());
		String lastName = XSSChecker.stripXSS(accessPIIModel.getLastname());
		String requesterName = XSSChecker.stripXSS(accessPIIModel
				.getRequesterName());
		String emailID = XSSChecker.stripXSS(accessPIIModel
				.getRequesterMailID());
		DBResult flag = PiiHandler.createPIIRequest(firstName, lastName,
				requesterName, emailID);
		modelMap.addAttribute("message", flag.getMessage());
		modelMap.addAttribute("usertype", "");
		return "result";
	}

	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/signUpUser", method = RequestMethod.GET)
	public String signUpPage(ModelMap model) {
		model.addAttribute("salt", SALT);
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		return "signUpUser";
	}

	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/createUserAccount", method = RequestMethod.POST)
	public String createUserAccount(
			@ModelAttribute("SignupModel") SignupModel signupModel,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {
		userinfo info = getUserInfoFromModel(signupModel);
		usersec sec = getUserSecFromModel(signupModel);
		account acc = getAccountFromModel(signupModel);
		DBResult flag = UserDBHandler.userSignUp(info, sec, acc);
		Logger logger = log.getLogger(this);
		logger.warn("Creating account for  " + signupModel.getFirstname());
		modelMap.addAttribute("message", flag.getMessage());
		String role = GetRole.roles();
		modelMap.addAttribute("usertype", role);
		return "result";
	}

	private account getAccountFromModel(SignupModel signupModel) {
		if (signupModel.getUsertype().equals(UserType.EMPLOYEE)
				|| signupModel.getUsertype().equals(UserType.MANAGER)) {
			return null;
		}
		String userId = XSSChecker.stripXSS(signupModel.getUsername());
		double balance = signupModel.getAccBalance();
		AccountType accType =AccountType.CHECKING;
		account acc = new account(0, userId, null, null, balance, null, accType);
		return acc;
	}

	private usersec getUserSecFromModel(SignupModel signupModel) {
		String userId = XSSChecker.stripXSS(signupModel.getUsername());
		String passwd = signupModel.getPassword();
		String ques1 = XSSChecker.stripXSS(signupModel.getSecQuestion_1());
		String ques2 = XSSChecker.stripXSS(signupModel.getSecQuestion_2());
		String ques3 = XSSChecker.stripXSS(signupModel.getSecQuestion_3());
		String ans1 = XSSChecker.stripXSS(signupModel.getSecanswer_1());
		String ans2 = XSSChecker.stripXSS(signupModel.getSecanswer_2());
		String ans3 = XSSChecker.stripXSS(signupModel.getSecanswer_3());
		// TODO implement sitekey
		usersec sec = new usersec(userId, passwd, ques1, ans1, ques2, ans2,
				ques3, ans3, 999);
		return sec;
	}

	private userinfo getUserInfoFromModel(SignupModel signupModel) {

		String userId = XSSChecker.stripXSS(signupModel.getUsername());
		String firstName = XSSChecker.stripXSS(signupModel.getFirstname());
		String lastName = XSSChecker.stripXSS(signupModel.getLastname());
		String ssn = XSSChecker.stripXSS(signupModel.getSsn());
		UserType userType = signupModel.getUsertype();
		String address = XSSChecker.stripXSS(signupModel.getAddressline1()
				+ " | " + signupModel.getAddressline2() + " | "
				+ signupModel.getCity() + " | " + signupModel.getState()
				+ " | " + signupModel.getZipcode());
		Date dob = signupModel.getDob();
		String eMail = XSSChecker.stripXSS(signupModel.getEmail());
		int phoneNo = signupModel.getPhonenumber();
		Gender gender = signupModel.getGender();
		int empId = signupModel.getEmployeeid();
		userinfo info = new userinfo(userId, firstName, lastName, ssn,
				userType, address, dob, eMail, phoneNo, gender,
				UserStatus.LIVE, empId);
		return info;
	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT','MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(
			@ModelAttribute("ResetPasswordModel") ResetPasswordModel resetPasswordModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		UserType type = UserDBHandler.getCustomerType(userid);
		if(type==null) {
			model.addAttribute("message", "Error getting the user role!");
			return "result";
		}
		OTPGenerator otp = LoginDBHandler.sendOTPToUser(userid);
		if (otp == null) {
			model.addAttribute("message",
					"Error occured while sending OTP. Possible cause: Invalid user ID.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		
		String new_otp=Encryptor.encodePwd(otp.getOTP());
		request.getSession().setAttribute("OTP",
				new StringEnvelope(new_otp));
		
		
		request.getSession().setAttribute("TimeStamp", otp.getTimeStamp());
		model.addAttribute("usertype", type);
		model.addAttribute("salt", SALT);

		return "resetPassword";
	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT','MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/createNewPassword", method = RequestMethod.POST)
	public String createNewPassword(
			@ModelAttribute("ResetPasswordModel") ResetPasswordModel resetPasswordModel,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		StringEnvelope env = (StringEnvelope) request.getSession()
				.getAttribute("OTP");
		String otp = env.getValue();
		long timeStamp = (Long) request.getSession().getAttribute("TimeStamp");
		String oldPassword = XSSChecker.stripXSS(resetPasswordModel
				.getOldpassword());
		String rePassword = XSSChecker.stripXSS(resetPasswordModel
				.getPassword());
		String newOTP = XSSChecker.stripXSS(resetPasswordModel.getOtp());
		OTPGenerator OTP = new OTPGenerator(otp, timeStamp);
		DBResult flag = LoginDBHandler.updatePassword(userid, oldPassword,
				rePassword, newOTP, OTP);
		modelMap.addAttribute("flag", flag.isResult());
		modelMap.addAttribute("message", flag.getMessage());

		Logger logger = log.getLogger(this);
		logger.warn("Reset Password for user" + userid + " " + flag.getMessage());
		String role = GetRole.roles();
		modelMap.addAttribute("usertype", role);
		return "result";
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap model) {
		model.addAttribute("salt", SALT);
		return "forgotPassword";
	}

	@RequestMapping(value = "/forgotPasswordvisible", method = RequestMethod.POST)
	public String forgotPasswordvisible(
			@ModelAttribute("forgotPasswordModel") ForgotPasswordModel forgotPasswordModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		String userid = XSSChecker.stripXSS(forgotPasswordModel.getUsername());
		DBResult flag = LoginDBHandler.checkUserIdExists(userid);
		model.addAttribute("message", flag.getMessage());
		if (!flag.isResult()) {
			model.addAttribute("usertype", "");
			return "result";
		} else {
			request.getSession().setAttribute("userid",
					new StringEnvelope(userid));
			OTPGenerator otp = LoginDBHandler.sendOTPToUser(userid);
			
			if (otp == null) {
				model.addAttribute("usertype", "");
				model.addAttribute("message",
						"Error occured while sending OTP. Possible cause: Invalid user ID.");
				return "result";
			}
			
			String new_otp=Encryptor.encodePwd(otp.getOTP());
			
			request.getSession().setAttribute("OTP",
					new StringEnvelope(new_otp));
			request.getSession().setAttribute("TimeStamp", otp.getTimeStamp());
			// Security Questions
			usersec secdata = UserDBHandler.getSecurityAnswers(userid);
			if (secdata == null) {
				model.addAttribute("message",
						"Could not retrieve user security data!");
				String role = GetRole.roles();
				model.addAttribute("usertype", role);
				return "result";
			}
			request.getSession().setAttribute("Ans1",
					new StringEnvelope(secdata.getAns1()));
			request.getSession().setAttribute("Ans2",
					new StringEnvelope(secdata.getAns2()));
			request.getSession().setAttribute("Ans3",
					new StringEnvelope(secdata.getAns3()));
			model.addAttribute("Questions", secdata);
			model.addAttribute("userid", new StringEnvelope(userid));
		}
		model.addAttribute("salt", SALT);
		// Invalid exception throw if user id correct send OTP
		return "forgotPasswordvisible2";

	}

	@RequestMapping(value = "/checkAnswers", method = RequestMethod.POST)
	public String forgotPasswordvisible2(
			@ModelAttribute("forgotPasswordModel") ForgotPasswordModel forgotPasswordModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		// check if the OPT is valid
		StringEnvelope env = (StringEnvelope) request.getSession()
				.getAttribute("userid");
		String userid = env.getValue();
		env = (StringEnvelope) request.getSession().getAttribute("OTP");
		String otp = env.getValue();
		
		
		long timeStamp = (Long) request.getSession().getAttribute("TimeStamp");
		OTPGenerator OTP = new OTPGenerator(otp, timeStamp);
		String newOTP = XSSChecker.stripXSS(forgotPasswordModel.getOtp());
		
		DBResult otpFlag = LoginDBHandler.checkOTP(userid, newOTP, OTP);
		model.addAttribute("flag", otpFlag.isResult());
		if (!otpFlag.isResult()) {
			model.addAttribute("message", otpFlag.getMessage());
			model.addAttribute("usertype", "");
			return "result";
		}
		// Get the questions in sessions and answers
		env = (StringEnvelope) request.getSession().getAttribute("Ans1");
		String Ans1 = env.getValue();
		env = (StringEnvelope) request.getSession().getAttribute("Ans2");
		String Ans2 = env.getValue();
		env = (StringEnvelope) request.getSession().getAttribute("Ans3");
		String Ans3 = env.getValue();
		String newAns1 = XSSChecker.stripXSS(forgotPasswordModel
				.getSecanswer_1());
		String newAns2 = XSSChecker.stripXSS(forgotPasswordModel
				.getSecanswer_2());
		String newAns3 = XSSChecker.stripXSS(forgotPasswordModel
				.getSecanswer_3());
		if (!(Ans1.equals(newAns1) && Ans2.equals(newAns2) && Ans3
				.equals(newAns3))) {
			model.addAttribute("message", "One or more answers are incorrect!");

		} else {

			// model.addAttribute("message", "Password Has been changed!");
			DBResult flag1 = LoginDBHandler.changePassword(userid,
					forgotPasswordModel.getPassword());
			model.addAttribute("flag1", flag1.isResult());
			model.addAttribute("message", flag1.getMessage());

		}
		model.addAttribute("usertype", "");
		return "result";
	}

	// Unlock the password if the password is locked
	@RequestMapping(value = "/unlockPassword", method = RequestMethod.GET)
	public String unlockPassword(ModelMap model) {
		return "unlockPassword";
	}

	// Unlock the password if the password is locked

	@RequestMapping(value = "/unlockPasswordvisible", method = RequestMethod.POST)
	public String unlockPasswordvisible(
			@ModelAttribute("unlockPasswordModel") UnlockPasswordModel unlockPasswordModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		String userid = XSSChecker.stripXSS(unlockPasswordModel.getUsername());
		DBResult flag = LoginDBHandler.checkUserIdExists(userid);
		model.addAttribute("message", flag.getMessage());
		if (!flag.isResult()) {
			model.addAttribute("usertype", "");
			return "result";
		} else {
			request.getSession().setAttribute("userid",
					new StringEnvelope(userid));
			OTPGenerator otp = LoginDBHandler.sendOTPToUser(userid);
			if (otp == null) {
				model.addAttribute("message",
						"Error occured while sending OTP. Possible cause: Invalid user ID.");
				model.addAttribute("usertype", "");
				return "result";
			}
			
             String new_otp=Encryptor.encodePwd(otp.getOTP());
			
			request.getSession().setAttribute("OTP",
					new StringEnvelope(new_otp));
			request.getSession().setAttribute("TimeStamp", otp.getTimeStamp());
			model.addAttribute("userid", new StringEnvelope(userid));
		}
		// Invalid exception throw if user id correct send OTP
		return "unlockPasswordvisible2";

	}

	// Validate if the OTP is correct for unlock password

	@RequestMapping(value = "/validateOTP", method = RequestMethod.POST)
	public String unlockPasswordvisible2(
			@ModelAttribute("UnlockPasswordModel") UnlockPasswordModel unlockPasswordModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		// check if the OPT is valid
		StringEnvelope env = (StringEnvelope) request.getSession()
				.getAttribute("userid");
		String userid = env.getValue();
		env = (StringEnvelope) request.getSession().getAttribute("OTP");
		String otp = env.getValue();
		long timeStamp = (Long) request.getSession().getAttribute("TimeStamp");
		OTPGenerator OTP = new OTPGenerator(otp, timeStamp);
		String newOTP = XSSChecker.stripXSS(unlockPasswordModel.getOtp());
		DBResult otpFlag = LoginDBHandler.checkOTP(userid, newOTP, OTP);
		model.addAttribute("flag", otpFlag.isResult());
		if (!otpFlag.isResult()) {
			model.addAttribute("message", otpFlag.getMessage());
			model.addAttribute("usertype", "");
			return "result";
		} else {
			DBResult flag1 = UserDBHandler.userUnlock(userid);
			model.addAttribute("flag1", flag1.isResult());
			model.addAttribute("message", flag1.getMessage());
		}
		model.addAttribute("usertype", "");
		return "result";
	}
	
	@RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
	public String aboutUs(ModelMap model) {
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		return "aboutUs";
	}

	@RequestMapping(value = "/FAQ", method = RequestMethod.GET)
	public String FAQ(ModelMap model) {
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		return "FAQ";

	}

	@RequestMapping(value = "/contactUs", method = RequestMethod.GET)
	public String contactUs(ModelMap model) {
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		
		return "contactUs";
	}
}