package com.netbanking.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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

import com.netbanking.model.*;
import com.netbanking.database.account;
import com.netbanking.database.account.AccountStatus;
import com.netbanking.database.account.AccountType;
import com.netbanking.database.piidbrequest;
import com.netbanking.database.trandetails;
import com.netbanking.database.userinfo;
import com.netbanking.database.trandetails.TransStatus;
import com.netbanking.database.trandetails.TransType;
import com.netbanking.database.userinfo.UserType;
import com.netbanking.database.util.AccountDBHandler;
import com.netbanking.database.util.DBConn;
import com.netbanking.database.util.LoginDBHandler;
import com.netbanking.database.util.PiiHandler;
import com.netbanking.database.util.TransDBHandler;
import com.netbanking.database.util.UserDBHandler;
import com.netbanking.exception.DBResult;
import com.netbanking.model.AuthorizePIIPendingRequestModel;
import com.netbanking.model.CreateTransModel;
import com.netbanking.model.DeleteAccountModel;
import com.netbanking.model.TransactionsModel;
import com.netbanking.model.ViewAccountModel;
import com.netbanking.model.addAccountModel;
import com.netbanking.util.CustomLog;
import com.netbanking.util.GetRole;
import com.netbanking.util.XSSChecker;

@Controller
@RequestMapping("/")
public class InternalUserController {
	CustomLog log = new CustomLog();
	
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public String addAccount(ModelMap model) {
		return "addAccount";
	}
	
	
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/addNewAccount", method = RequestMethod.POST)
	public String addNewAccount(
			@ModelAttribute("addAccountModel") addAccountModel addAccount,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {
		String userid = XSSChecker.stripXSS(addAccount.getUserID());
		String role=GetRole.roles();
		modelMap.addAttribute("usertype",role);
		DBResult flag = LoginDBHandler.checkUserIdExists(userid);
		if(!flag.isResult())
		{
			modelMap.addAttribute("message", flag.getMessage());	
			return "result";
			
		}
	
		if(flag.isResult())
		{
			UserType isinternal = UserDBHandler.getCustomerType(userid);
			if(isinternal==null) {
				modelMap.addAttribute("message", "Error getting the user role!");
				return "result";
			}
			if(isinternal.equals(UserType.EMPLOYEE)||isinternal.equals(UserType.MANAGER))
			{
				modelMap.addAttribute("message", "Accounts cannot be created for Manager/Employee");	
				return "result";
			}
			else
			{
				Session session = null;
				try {
					session = DBConn.getSessionFactory().openSession();
					if (session == null) {
						modelMap.addAttribute("message","Database exception occured");
						return "result";		
						}
					Transaction tran=session.beginTransaction();
				account acct=new account();
				int lastacctid=AccountDBHandler.getLastAcctNum();
				acct.setAccNum(lastacctid+1);
				acct.setBalance(addAccount.getBalance());
				acct.setUserId(userid);
				acct.setAccStatus(AccountStatus.OPEN);
				acct.setAccType(AccountType.CHECKING);
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				acct.setDateOfOpen(date);
				session.save(acct);
				tran.commit();
				session.close();
				
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					modelMap.addAttribute("message","Database exception occured.");
					return "result";
				}
			}
				
		}
		
		modelMap.addAttribute("message", "Account Created!!");
		return "result";
	}
	

	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/viewTrans", method = RequestMethod.GET)
	public String viewTrans(ModelMap model) {
		return "viewTrans";
	}
	
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/viewUserProfile", method = RequestMethod.GET)
	public String viewUserProfile(ModelMap model) {
		return "viewUserProfile";
	}
	
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/editUserProfile", method = RequestMethod.GET)
	public String editUserProfile(ModelMap model) {
		return "editUserProfile";
	}
	
	@PreAuthorize("hasAnyRole('MANAGER')")
	@RequestMapping(value = "/delUserProfile", method = RequestMethod.GET)
	public String deleteUserProfile(ModelMap model) {
		return "delUserProfile";
	}
	
	@PreAuthorize("hasAnyRole('MANAGER')")
	@RequestMapping(value = "/delUserProfile1", method = RequestMethod.GET)
	public String delUserProfile1(@ModelAttribute("UserModel") UserModel usermodel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = auth.getName();
		DBResult res=UserDBHandler.removeUser(usermodel.getUserId(), userId);
		if(res.isResult())
		{
		String role=GetRole.roles();
		model.addAttribute("usertype",role);
		model.addAttribute("message","User Successfully Deleted");
		}
		else
		{
			String role=GetRole.roles();
			model.addAttribute("usertype",role);
			model.addAttribute("message",res.getMessage());
		}
		return "result";
	}
	
	
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/editUserProfile1", method = RequestMethod.GET)
	public String editUserProfile1(@ModelAttribute("UserModel") UserModel usermodel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = auth.getName();
		userinfo user=new userinfo();
		user=UserDBHandler.getUserProfile(usermodel.getUserId());
		if(user==null){
			model.addAttribute("message", "Error retrieving user profile.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		String role=GetRole.roles();
		if(user.getUserStatus().toString().equalsIgnoreCase("DEAD"))
		{
			String flag="Selected Account is disabled";
			model.addAttribute("message", flag);
			model.addAttribute("usertype", role);
			return "result";
		}
		if(role.equalsIgnoreCase("EMPLOYEE"))
		{
			if(user.getUserId().equalsIgnoreCase(userId))
			{
				
			}
			else
			{
				if(user.getUserType().toString().equalsIgnoreCase("MANAGER")||user.getUserType().toString().equalsIgnoreCase("EMPLOYEE"))
				{
					String flag="You are not authorized the edit the details of said user";
					model.addAttribute("message", flag);
					model.addAttribute("usertype", role);
					return "result";
				}
			}
		}
		
		String address = user.getAddress();
		model.addAttribute("userId",usermodel.getUserId());
		model.addAttribute("mail", user.geteMail());
		model.addAttribute("phoneNo", user.getPhoneNo());
		model.addAttribute("addr1", address.split("\\|")[0]);
		model.addAttribute("addr2", address.split("\\|")[1]);
		model.addAttribute("city", address.split("\\|")[2]);
		model.addAttribute("state", address.split("\\|")[3]);
		model.addAttribute("zip", address.split("\\|")[4]);
		model.addAttribute("usertype", user.getUserType());
		return "editUserProfile1";
	}
	
	
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/viewUserProfile1", method = RequestMethod.POST)
	public String viewUserProfile1(@ModelAttribute("UserModel") UserModel usermodel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		userinfo user=new userinfo();
		user=UserDBHandler.getUserProfile(usermodel.getUserId());
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		if(user==null){
			model.addAttribute("message", "Error retrieving user profile.");
			return "result";
		}
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = auth.getName();
		if(role.equalsIgnoreCase("EMPLOYEE"))
		{
			if(user.getUserId().equalsIgnoreCase(userId))
			{
				
			}
			else
			{
				if(user.getUserType().toString().equalsIgnoreCase("MANAGER")||user.getUserType().toString().equalsIgnoreCase("EMPLOYEE"))
				{
					String flag="You are not authorized the view the details of said user";
					model.addAttribute("message", flag);
					return "result";
				}
			}
		}
		model.addAttribute("UserList1", user);
		return "viewUserProfile";
	}
	
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/viewUserProfile2", method = RequestMethod.GET)
	public String viewUserProfile2(ModelMap model) {
		List<userinfo> userList = new ArrayList<userinfo>();
		userList=UserDBHandler.getUserProfiles();
		if(userList==null){
			model.addAttribute("message", "Error retrieving user profiles.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		model.addAttribute("UserList", userList);
		return "viewUserProfile";
	}

	@PreAuthorize("hasAnyRole('MANAGER')")
	@RequestMapping(value = "/showAuditlog", method = RequestMethod.GET)
	public String showAuditlog(ModelMap model) {
		// model.addAttribute("message", "Welcome to Bank of Arizona");
		return "auditTranscationLog";
	}

	@PreAuthorize("hasAnyRole('MANAGER')")
	@RequestMapping(value = "/showlog", method = RequestMethod.GET)
	public String accessPII(ModelMap model) {
		return "showlog";
	}

	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/viewTransSuccess", method = RequestMethod.GET)
	public String viewTransSuccess(ModelMap model) {
		List<trandetails> tranlist = new ArrayList<trandetails>();
		tranlist = TransDBHandler.getFirstTenTrans();
		if(tranlist == null) {
			model.addAttribute("message", "Error retrievng Transactions.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		model.addAttribute("TransModel", tranlist);

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		Logger logger = log.getLogger(this);
		logger.warn("View transcation Successful for" + userid);
		return "viewTransSuccess";
	}
	
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/viewAccounts", method = RequestMethod.GET)
	public String viewAccounts(
			@ModelAttribute("ViewAccountModel") ViewAccountModel acctitem,
			BindingResult result, ModelMap model, HttpServletRequest request) {

		List<account> acctlist = new ArrayList<account>();
		acctlist = AccountDBHandler.getAccounts();
			if (acctlist == null || acctlist.size() == 0) {
				model.addAttribute("Error", "Invalid Account Number");
			} else {
				model.addAttribute("AcctModel", acctlist);
		}	
		return "viewAccount";
	}

	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/createTrans", method = RequestMethod.GET)
	public String debitcredit(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		Logger logger = log.getLogger(this);
		logger.warn("Creating transcation Successful for" + userid);
		return "createTrans";

	}

	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/createTransSuccess", method = RequestMethod.POST)
	public String createtransferfinal(
			@ModelAttribute("CreateTransModel") CreateTransModel createtrans,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		String transType = XSSChecker.stripXSS(createtrans.getTransType());
		if (transType.equalsIgnoreCase("Debit")) {
			String flag1 = "";
			DBResult acctflag = TransDBHandler.chkValidAcct(createtrans
					.getFromAcct());
			int toacct1=createtrans.getToAcct();
			account toacct2=AccountDBHandler.getAccount(toacct1);
			
			DBResult balflag = TransDBHandler.chkBalance(
					createtrans.getFromAcct(), createtrans.getAmount());
			if (acctflag.isResult() && balflag.isResult()) {
				// int newtransId = TransDBHandler.getLastTrans() + 1;
				trandetails temptran = new trandetails();
				// temptran.setTransId(newtransId);
				temptran.setAmount(createtrans.getAmount());
				temptran.setFromAcct(createtrans.getFromAcct());
				temptran.setTransType(TransType.DEBIT);
				temptran.setTransStatus(TransStatus.COMPLETED);
				temptran.setToAcct(0);
				
				int account = createtrans.getFromAcct();
				double oldbalance = AccountDBHandler.getBalance(account);
				if (oldbalance == -999.00) {
					model.addAttribute("message",
							"Error retrieving account balance. Possible cause: invalid account number.");
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					return "result";
				}
				double newbalance = oldbalance - createtrans.getAmount();
				DBResult flag = new DBResult(false, "");
				DBResult balanceflag = AccountDBHandler.updateBalance(account,
						newbalance);
				if (balanceflag.isResult()) {
					flag = TransDBHandler.createTrans(temptran);
				}
				if (flag.isResult()) {
					flag1 = "Debit Successfully completed";

					
					Logger logger = log.getLogger(this);
					logger.warn("Debit Successfully completed for" + userid
							+ " for amount" + createtrans.getAmount());
				} else {

					flag1 = "Transaction failed";
				}

			} else {
				flag1 = "Account does not exist or Balance not enough";
			}
			model.addAttribute("message", flag1);

		} else if (transType.equalsIgnoreCase("Credit")) {

			String flag1 = "";
			DBResult acctflag = TransDBHandler.chkValidAcct(createtrans
					.getFromAcct());
			if (acctflag.isResult()) {
				// int newtransId = TransDBHandler.getLastTrans() + 1;
				trandetails temptran = new trandetails();
				// temptran.setTransId(newtransId);
				temptran.setAmount(createtrans.getAmount());
				temptran.setFromAcct(createtrans.getFromAcct());
				temptran.setTransType(TransType.CREDIT);
				temptran.setTransStatus(TransStatus.COMPLETED);
				temptran.setToAcct(0);
				int account = createtrans.getFromAcct();
				double oldbalance = AccountDBHandler.getBalance(account);
				double newbalance = oldbalance + createtrans.getAmount();
				DBResult flag = new DBResult(false, "");
				DBResult balanceflag = AccountDBHandler.updateBalance(account,
						newbalance);
				if (balanceflag.isResult()) {
					flag = TransDBHandler.createTrans(temptran);
				}
				if (flag.isResult()) {
					
					Logger logger = log.getLogger(this);
					logger.warn("Credit Successfully completed for" + userid
							+ " for amount" + createtrans.getAmount());

					flag1 = "Credit Successfully completed";
				} else {
					flag1 = "Transaction failed";
				}

			} else {
				flag1 = "Account does not exist";
			}
			model.addAttribute("message", flag1);
		} else if (transType.equalsIgnoreCase("Transfer")) {
			DBResult flag = new DBResult(false, "");
			String flag1 = "";
			DBResult fromacctflag = TransDBHandler.chkValidAcct(createtrans
					.getFromAcct());
			DBResult toacctflag = TransDBHandler.chkValidAcct(createtrans
					.getToAcct());
			account toacct2=AccountDBHandler.getAccount(createtrans.getToAcct());
			if(toacct2.getAccStatus().toString().equalsIgnoreCase("CLOSED"))
			{
				model.addAttribute("message",
						"To Account does not exist");
				String role = GetRole.roles();
				model.addAttribute("usertype", role);
				return "result";
				
			}
			
			if (createtrans.getFromAcct() == createtrans.getToAcct()) {
				fromacctflag.setResult(false);
			}
			DBResult balflag = TransDBHandler.chkBalance(
					createtrans.getFromAcct(), createtrans.getAmount());
			if (fromacctflag.isResult() && toacctflag.isResult()
					&& balflag.isResult()) {
				// int newtransId = TransDBHandler.getLastTrans() + 1;
				trandetails temptran = new trandetails();
				// temptran.setTransId(newtransId);
				temptran.setAmount(createtrans.getAmount());
				temptran.setFromAcct(createtrans.getFromAcct());
				temptran.setToAcct(createtrans.getToAcct());
				temptran.setTransType(TransType.TRANSFER);
				temptran.setAuthUser(userid);
				if (createtrans.getAmount() < 1000) {
					temptran.setTransStatus(TransStatus.COMPLETED);
					int account = createtrans.getFromAcct();
					double oldbalance = AccountDBHandler.getBalance(account);
					double newbalance = oldbalance - createtrans.getAmount();
					flag.setResult(false);
					DBResult balanceflag1 = AccountDBHandler.updateBalance(
							account, newbalance);
					DBResult balanceflag2 = new DBResult(false, "");
					if (balanceflag1.isResult()) {
						account = createtrans.getToAcct();
						oldbalance = AccountDBHandler.getBalance(account);
						newbalance = oldbalance + createtrans.getAmount();
						balanceflag2 = AccountDBHandler.updateBalance(account,
								newbalance);
						balanceflag2.setResult(true);
					}

					if (balanceflag2.isResult()) {
						flag = TransDBHandler.createTrans(temptran);
					}
					if (flag.isResult()) {

						Logger logger = log.getLogger(this);
						logger.warn("Transfer Successfully completed from account "
								+ createtrans.getFromAcct()
								+ "to account "
								+ createtrans.getToAcct()
								+ " for amount"
								+ createtrans.getAmount());

						flag1 = "Transfer Successfully completed";
					} else {
						flag1 = "Transaction failed";
						Logger logger = log.getLogger(this);
						logger.warn("Transfer failed from account "
								+ createtrans.getFromAcct() + "to account "
								+ createtrans.getToAcct() + " for amount"
								+ createtrans.getAmount());

					}
				} else {
					temptran.setTransStatus(TransStatus.PENDING);
					flag = TransDBHandler.createTrans(temptran);
					if (flag.isResult()) {
						flag1 = "Transfer Successfully completed";
						Logger logger = log.getLogger(this);
						logger.warn("Transfer Successfully completed from account "
								+ createtrans.getFromAcct()
								+ "to account "
								+ createtrans.getToAcct()
								+ " for amount"
								+ createtrans.getAmount());

					} else {
						flag1 = "Transaction failed";
						Logger logger = log.getLogger(this);
						logger.warn("Transfer failed from account "
								+ createtrans.getFromAcct() + "to account "
								+ createtrans.getToAcct() + " for amount"
								+ createtrans.getAmount());

					}
				}
			} else {
				flag1 = "From/To Account does not exist or Balance not enough to carry out transaction";
			}
			model.addAttribute("message", flag1);
		}
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		return "result";
	}

	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/authorizeTrans", method = RequestMethod.POST)
	public String authorizeTransactions(
			@ModelAttribute("TransactionsModel") TransactionsModel transactionsModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		String[] transStatus = transactionsModel.getTransStatusString().split(
				",");
		String[] transIDs = transactionsModel.getTransIDs().split(",");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();

		DBResult response = new DBResult(false, "");
		for (int i = 1; i < transStatus.length; i++) {
			System.out.println(transStatus[i]);
			System.out.println(transIDs[i]);
			switch (transStatus[i]) {
			case "true":
				response = TransDBHandler.setTransComplete(
						Integer.parseInt(transIDs[i]), userid);
				model.addAttribute("message", response.getMessage()
						+ "for Transaction " + Integer.parseInt(transIDs[i]));
				if (!response.isResult()) {
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					return "result";
				}
				break;
			case "false":
				response = TransDBHandler.setTransReject(
						Integer.parseInt(transIDs[i]), userid);
				model.addAttribute("message", response.getMessage()
						+ "for Transaction " + Integer.parseInt(transIDs[i]));
				if (!response.isResult()) {
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					return "result";
				}
				break;
			case "none":
				model.addAttribute("message", "Your requests are processed!");
				break;
			}
		}
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		return "result";
	}

	// Delete User Account
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
	public String deleteAccount(ModelMap model) {
		return "deleteAccount";
	}

	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/deleteuserAccount", method = RequestMethod.POST)
	public String deleteuserAccount(
			@ModelAttribute("DeleteAccountModel") DeleteAccountModel deleteAccountModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		account acct=AccountDBHandler.getAccount(deleteAccountModel
				.getAccNum());
		if(acct==null) {
			model.addAttribute("message",
					"Error retrieving the user accounts. Possible cause: invalid account number.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		if(acct.getAccStatus().toString().equalsIgnoreCase("CLOSED"))
		{
			model.addAttribute("message", "Account Already Closed");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
			
		}
		
		
		DBResult flag = AccountDBHandler.deleteAccount(deleteAccountModel
				.getAccNum());

		Logger logger = log.getLogger(this);
		logger.warn("Account deleted with account number"
				+ deleteAccountModel.getAccNum());

		model.addAttribute("message", flag.getMessage());
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		return "result";

	}

	// View User Account
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/viewAccount", method = RequestMethod.GET)
	public String viewAccount(ModelMap model) {
		return "viewAccount";
	}

	// View User Account
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/viewuserAccount", method = RequestMethod.POST)
	public String viewuserAccount(
			@ModelAttribute("ViewAccountModel") ViewAccountModel viewAccountModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		account accdetails = AccountDBHandler.getAccount(viewAccountModel
				.getAccNum());
		String role=GetRole.roles();
		if (accdetails == null) {
			model.addAttribute("message",
					"Account does not exist for given user.");
			model.addAttribute("usertype", role);
			return "result";
		}
		model.addAttribute("accdetails", accdetails);

		return "viewAccountResult";
	}

	// Authorize/decline Pending Requests
	@PreAuthorize("hasAnyRole('MANAGER')")
	@RequestMapping(value = "/authorizePendingRequests", method = RequestMethod.POST)
	public String authorizePendingRequests(
			@ModelAttribute("AuthorizePIIPendingRequestModel") AuthorizePIIPendingRequestModel piipendingModel,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		String[] requestStatus = piipendingModel.getRequestStatusString()
				.split(",");
		String[] requestIDs = piipendingModel.getRequestIDs().split(",");
		DBResult response = new DBResult(false, "");
		for (int i = 1; i < requestStatus.length; i++) {
			System.out.println(requestStatus[i]);
			System.out.println(requestIDs[i]);
			switch (requestStatus[i]) {
			case "true":
				response = PiiHandler.authPIIRequest(Integer
						.parseInt(requestIDs[i]));
				model.addAttribute("message", response.getMessage()
						+ "for Requests " + Integer.parseInt(requestIDs[i]));
				if (!response.isResult()) {
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					return "result";
				}
				break;
			case "false":
				response = PiiHandler.denyPii(Integer.parseInt(requestIDs[i]));
				model.addAttribute("message", response.getMessage()
						+ "for Requests " + Integer.parseInt(requestIDs[i]));
				if (!response.isResult()) {
					String role = GetRole.roles();
					model.addAttribute("usertype", role);
					return "result";
				}
				break;
			case "none":
				model.addAttribute("message", "Your requests are processed!");
				break;
			}
		}
		String role = GetRole.roles();
		model.addAttribute("usertype", role);
		return "result";
	}

	// Authorize/Decline requests
	@PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/authorizeRequests", method = RequestMethod.GET)
	public String authorizeRequests(ModelMap model) {
		List<piidbrequest> piipendingRequests = new ArrayList<piidbrequest>();
		piipendingRequests = PiiHandler.getPendingPii();
		if(piipendingRequests == null) {
			model.addAttribute("message", "Error retrievng PII requests.");
			String role = GetRole.roles();
			model.addAttribute("usertype", role);
			return "result";
		}
		model.addAttribute("piipendingRequests", piipendingRequests);
		return "authorizeRequests";
	}

}
