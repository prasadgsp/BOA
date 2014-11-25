package com.netbanking.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netbanking.database.userinfo;
import com.netbanking.database.util.UserDBHandler;
import com.netbanking.exception.DBResult;
import com.netbanking.model.SignupModel;
import com.netbanking.util.CustomLog;
import com.netbanking.util.GetRole;
import com.netbanking.util.XSSChecker;

@Controller
@RequestMapping("/")
public class UserProfileController {
	CustomLog log = new CustomLog();

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT','MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/viewProfile", method = RequestMethod.GET)
	public String homePage(ModelMap modelMap) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String userid = auth.getName();
		userinfo viewProfile_info;
		viewProfile_info = UserDBHandler.getCustomerWithoutPII(userid);
		if (viewProfile_info == null) {
			String role = GetRole.roles();
			modelMap.addAttribute("usertype", role);
			modelMap.addAttribute("message",
					"Error occured while viewing profile. Possible cause: Invalid user ID.");
			return "result";
		}
		modelMap.addAttribute("info", viewProfile_info);

		return "viewProfile";
	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT','MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String homePageError(ModelMap model) {
		String role = GetRole.roles();
		model.addAttribute("usertype", role);

		return "accessDenied";
	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT','MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String EditPage(ModelMap modelMap) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();
		userinfo viewProfile_info;
		viewProfile_info = UserDBHandler.getCustomerWithoutPII(userid);
		if (viewProfile_info == null) {
			String role = GetRole.roles();
			modelMap.addAttribute("usertype", role);
			modelMap.addAttribute("message",
					"Error occured while viewing profile. Possible cause: Invalid user ID.");
			return "result";
		}
		String address = viewProfile_info.getAddress();

		modelMap.addAttribute("mail", viewProfile_info.geteMail());
		modelMap.addAttribute("phoneNo", viewProfile_info.getPhoneNo());
		modelMap.addAttribute("addr1", address.split("\\|")[0]);
		modelMap.addAttribute("addr2", address.split("\\|")[1]);
		modelMap.addAttribute("city", address.split("\\|")[2]);
		modelMap.addAttribute("state", address.split("\\|")[3]);
		modelMap.addAttribute("zip", address.split("\\|")[4]);
		modelMap.addAttribute("usertype", viewProfile_info.getUserType());
		return "editProfile";
	}

	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT','MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/updateProfileSuccess", method = RequestMethod.POST)
	public String UpdateProfile(
			@ModelAttribute("SignupModel") SignupModel signupModel,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();

		String address = XSSChecker.stripXSS(signupModel.getAddressline1()
				+ " | " + signupModel.getAddressline2() + " | "
				+ signupModel.getCity() + " | " + signupModel.getState()
				+ " | " + signupModel.getZipcode());

		String emailID = XSSChecker.stripXSS(signupModel.getEmail());
		String role = GetRole.roles();
		
		DBResult flag = UserDBHandler.updateUserDetail(userid, address,
				emailID, signupModel.getPhonenumber());
		modelMap.addAttribute("usertype", role);
		modelMap.addAttribute("message", flag.getMessage());
		
	
		
		

		Logger logger = log.getLogger(this);
		logger.warn("updateProfile Successfully completed for user" + userid);
	
		return "result";
	}

	
	@PreAuthorize("hasAnyRole('CUSTOMER','MERCHANT','MANAGER','EMPLOYEE')")
	@RequestMapping(value = "/updateUserProfileSuccess", method = RequestMethod.POST)
	public String UpdateProfile1(
			@ModelAttribute("SignupModel") SignupModel signupModel,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userid = auth.getName();

		String address = XSSChecker.stripXSS(signupModel.getAddressline1()
				+ " | " + signupModel.getAddressline2() + " | "
				+ signupModel.getCity() + " | " + signupModel.getState()
				+ " | " + signupModel.getZipcode());

		String emailID = XSSChecker.stripXSS(signupModel.getEmail());
		String role = GetRole.roles();
		
		DBResult flag = UserDBHandler.updateUserDetail(signupModel.getUsername(), address,
				emailID, signupModel.getPhonenumber());
		modelMap.addAttribute("usertype", role);
		modelMap.addAttribute("message", flag.getMessage());
		
	
		
		

		Logger logger = log.getLogger(this);
		logger.warn("updateProfile Successfully completed for user" + userid);
	
		return "result";
	}
	
}
