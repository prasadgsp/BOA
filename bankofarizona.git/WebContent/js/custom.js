function validateLoginForm() {
	if (document.getElementById('username').value == ""
			|| document.getElementById('username').value == null) {
		document.getElemenstById('errors').innerHTML = "Please Enter the Username.";
		return false;
	}
	if (document.getElementById('password').value == ""
			|| document.getElementById('password').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Password.";
		return false;
	}
	return true;
}

function validateaddaccForm() {
	if (document.getElementById('userID').value == ""
			|| document.getElementById('userID').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the userID.";
		return false;
	}

	if (document.getElementById('balance').value == ""
			|| document.getElementById('balance').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the balance.";
		return false;
	}
	if (isNaN(document.getElementById('balance').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid balance.";
		return false;
	}

	document.getElementById('add-acc').action = "addNewAccount.html";
	return true
}
function validateauditForm() {
	if (isDate(document.getElementById('date').value)) {
		document.getElementById('audit').action = "downloadAudit";
		return true;
	}
	return false;
}

function validateDebitCreditOTPForm() {
	document.getElementById('debit-credit-otp-form').action = "debitcredit.html";
}

function validateDebitCreditForm3() {

	if (document.getElementById('amount').value == ""
			|| document.getElementById('amount').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the amount to Debit/Credit.";
		return false;
	}

	else if (isNaN(document.getElementById('amount').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid amount.";
		return false;
	} else if (document.getElementById('fromAcct').value == ""
			|| document.getElementById('fromAcct').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Account Number.";
		return false;
	}

	else if (isNaN(document.getElementById('fromAcct').value)) {
		document.getElementById('errors').innerHTML = "Please Enter valid Account Number.";
		return false;
	} else if (document.getElementById('amount').value > 100000
			|| document.getElementById('amount').value < 1) {
		document.getElementById('errors').innerHTML = "The amout allowed is between $1 and $100000.";
		return false;
	} else if (document.getElementById('otp').value == ""
		|| document.getElementById('otp').value == null
		|| isNaN(document.getElementById('otp').value)) {
	document.getElementById('errors').innerHTML = "Please Enter the valid OTP.";
	return false;
	}
	document.getElementById('debit-credit-form').action = "debitCreditSuccess3.html";
	return true;
}

function validateDebitCreditForm4() {

	if (document.getElementById('amount').value == ""
			|| document.getElementById('amount').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the amount to Debit/Credit.";
		return false;
	} else if (isNaN(document.getElementById('amount').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid amount.";
		return false;
	} else if (document.getElementById('fromAcct').value == ""
			|| document.getElementById('fromAcct').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Account Number.";
		return false;
	}

	else if (isNaN(document.getElementById('fromAcct').value)) {
		document.getElementById('errors').innerHTML = "Please Enter valid Account Number.";
		return false;
	} else if (document.getElementById('amount').value > 100000
			|| document.getElementById('amount').value < 1) {
		document.getElementById('errors').innerHTML = "The amout allowed is between $1 and $100000.";
		return false;
	}
	else if (document.getElementById('otp').value == ""
		|| document.getElementById('otp').value == null
		|| isNaN(document.getElementById('otp').value)) {
	document.getElementById('errors').innerHTML = "Please Enter the valid OTP.";
	return false;
	}
	document.getElementById('debit-credit-form').action = "debitCreditSuccess4.html";
	return true;
}

function validateupdateForm() {

	if (document.getElementById('addressline1').value == ""
			|| document.getElementById('addressline1').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Address Line 1.";
		return false;
	} else if (document.getElementById('addressline2').value == ""
			|| document.getElementById('addressline2').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Address Line 2.";
		return false;
	} else if (document.getElementById('city').value == ""
			|| document.getElementById('city').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the City.";
		return false;
	} else if (document.getElementById('state').value == ""
			|| document.getElementById('state').value == null
			|| document.getElementById('state').value == "State") {
		document.getElementById('errors').innerHTML = "Please Enter the State.";
		return false;
	} else if (document.getElementById('zipcode').value == ""
			|| document.getElementById('zipcode').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the valid Zip Code.";
		return false;
	} else if (isNaN(document.getElementById('zipcode').value)) {	
		document.getElementById('errors').innerHTML = "Please Enter the valid Zip Code.";
		return false;
	} else if (document.getElementById('email').value == ""
			|| document.getElementById('email').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Email Id.";
		return false;
	} else if (document.getElementById('phonenumber').value == ""
			|| document.getElementById('phonenumber').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the valid Phone Number.";
		return false;
	}

	else if (isNaN(document.getElementById('phonenumber').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid phone number.";
		return false;
	}
	if (validateEmail(document.getElementById('email'))) {
		if (validatePhone(document.getElementById('phonenumber'))) {
			if (validateState(document.getElementById('state').value)) {
				document.getElementById('editprofile').action = "updateProfileSuccess.html";
				return true;
			}
		}
	}
	return false;
}

function validateuserprofileupdateForm() {
	if (document.getElementById('addressline1').value == ""
			|| document.getElementById('addressline1').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Address Line 1.";
		return false;
	} else if (document.getElementById('addressline2').value == ""
			|| document.getElementById('addressline2').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Address Line 2.";
		return false;
	} else if (document.getElementById('city').value == ""
			|| document.getElementById('city').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the City.";
		return false;
	} else if (document.getElementById('state').value == ""
			|| document.getElementById('state').value == null
			|| document.getElementById('state').value == "State") {
		document.getElementById('errors').innerHTML = "Please Enter the State.";
		return false;
	} else if (document.getElementById('zipcode').value == ""
			|| document.getElementById('zipcode').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the valid Zip Code.";
		return false;
	} else if (isNaN(document.getElementById('zipcode').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid Zip Code.";
		return false;
	} else if (document.getElementById('email').value == ""
			|| document.getElementById('email').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Email Id.";
		return false;
	} else if (document.getElementById('phonenumber').value == ""
			|| document.getElementById('phonenumber').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the valid Phone Number.";
		return false;
	} else if (isNaN(document.getElementById('phonenumber').value)) {
		document.getElementById('errors').innerHTML = "Please Enter valid phone Number.";
		return false;
	}
	document.getElementById('edituserprofile1').action = "updateUserProfileSuccess.html";
	return true;
}

function validateCreateTransForm() {
	var e = document.getElementById('transType');
	var strUser = e.options[e.selectedIndex].value;
	if (document.getElementById('amount').value == ""
			|| document.getElementById('amount').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Amount to transfer";
		return false;
	}

	else if (isNaN(document.getElementById('amount').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid amount.";
		return false;
	}

	else if (document.getElementById('fromAcct').value == ""
			|| document.getElementById('fromAcct').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the From Account number";
		return false;
	}

	else if (isNaN(document.getElementById('fromAcct').value)) {
		document.getElementById('errors').innerHTML = "Please Enter valid account Number.";
		return false;
	} else if (strUser == 'TRANSFER') {
		if (document.getElementById('toAcct').value == ""
				|| document.getElementById('toAcct').value == null) {
			document.getElementById('errors').innerHTML = "Please Enter the To Account Number ";
			return false;
		} else if (isNaN(document.getElementById('toAcct').value)) {
			document.getElementById('errors').innerHTML = "Please Enter the valid To Account number.";
			return false;
		} else if (document.getElementById('amount').value == null
				|| document.getElementById('amount').value > 100000
				|| document.getElementById('amount').value < 1) {
			document.getElementById('errors').innerHTML = "The amout allowed is between $1 and $100000.";
			return false;
		} else if (document.getElementById('toAcct').value == document
				.getElementById('fromAcct').value) {
			document.getElementById('errors').innerHTML = "From and To Account number can not be same.";
			return false;
		}
	}
	document.getElementById('create-trans').action = "createTransSuccess.html";
	return true;
}

function validateSignUpForm() {
	if (checkIfEmpty()) {
		if (validatePassword(document.getElementById('password'))) {
			if (checkPassword()) {
				if (isDate(document.getElementById('dob').value)) {
					if (validateEmail(document.getElementById('email'))) {
						if (validatePhone(document
								.getElementById('phonenumber'))) {
							if (validateSSN(document.getElementById('ssn'))) {
								if (checkAnswers()) {
									if (jcap()) {
										hashPassword();
										document.getElementById('sign-up').action = "createUserAccount.html";
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	return false;
}

function validateResetPasswordForm() {
	if (checkEmptyFields()) {
		if (checkPassword()) {
			if (validatePassword(document.getElementById('password'))) {
				if (checkOldNewPassword()) {
					hashPassword();
					var encrypted = CryptoJS.MD5(document
							.getElementById('oldpassword').value
							+ document.getElementById('salt').value);
					document.getElementById('oldpassword').value = encrypted;
					document.getElementById('reset-password-form').action = "createNewPassword.html";
					return true;
				}
			}
		}
	}
	return false;
}

function validateDebitCreditForm1() {

	if (document.getElementById('amount').value == ""
			|| document.getElementById('amount').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the amount to Debit/Credit.";
		return false;
	}

	else if (isNaN(document.getElementById('amount').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid amount.";
		return false;
	} else if (document.getElementById('fromAcct').value == ""
			|| document.getElementById('fromAcct').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Account Number.";
		return false;
	}

	else if (isNaN(document.getElementById('fromAcct').value)) {
		document.getElementById('errors').innerHTML = "Please Enter valid account Number.";
		return false;
	}

	else if (document.getElementById('amount').value == null
			|| document.getElementById('amount').value > 100000
			|| document.getElementById('amount').value < 1) {
		document.getElementById('errors').innerHTML = "The amout allowed is between $1 and $100000.";
		return false;
	}
	document.getElementById('debit-credit-form').action = "debitCreditSuccess1.html";
	return true;
}

function validateeditUserProfile() {

	if (document.getElementById('userId').value == ""
			|| document.getElementById('userId').value == null) {
		document.getElementById('errors').innerHTML = "Please enter User Id";
		return false;
	}
	document.getElementById('view-edituser-profile-form').action = "editUserProfile1.html";
	return true;
}

function validatedelUserProfile() {

	if (document.getElementById('userId').value == ""
			|| document.getElementById('userId').value == null) {
		document.getElementById('errors').innerHTML = "Please enter User Id";
		return false;
	}
	document.getElementById('view-deluser-profile-form').action = "delUserProfile1.html";
	return true;
}

function validateDebitCreditForm2() {

	if (document.getElementById('amount').value == ""
			|| document.getElementById('amount').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the amount to Debit/Credit.";
		return false;
	}

	else if (isNaN(document.getElementById('amount').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid amount.";
		return false;
	} else if (document.getElementById('fromAcct').value == ""
			|| document.getElementById('fromAcct').value == null 
			|| isNaN(document.getElementById('fromAcct').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid Account Number.";
		return false;
	} else if (document.getElementById('amount').value == null
			|| document.getElementById('amount').value > 100000
			|| document.getElementById('amount').value < 1) {
		document.getElementById('errors').innerHTML = "The amout allowed is between $1 and $100000.";
		return false;
	}
	document.getElementById('debit-credit-form').action = "debitCreditSuccess2.html";
	return true;
}

function validateTransferForm() {
	if (document.getElementById('fromAcct').value == ""
			|| document.getElementById('fromAcct').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Amount to transfer";
		return false;
	} else if (isNaN(document.getElementById('fromAcct').value)) {
		document.getElementById('errors').innerHTML = "Please Enter valid account Number.";
		return false;
	}

	else if (document.getElementById('amount').value == ""
			|| document.getElementById('amount').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the From Account number";
		return false;
	}

	else if (isNaN(document.getElementById('amount').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid amount.";
		return false;
	} else if (document.getElementById('toAcct').value == ""
			|| document.getElementById('toAcct').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the To Account Number ";
		return false;
	} else if (document.getElementById('toAcct').value == document
			.getElementById('fromAcct').value) {
		document.getElementById('errors').innerHTML = "From and To Account number can not be same.";
		return false;
	}

	else if (isNaN(document.getElementById('toAcct').value)) {
		document.getElementById('errors').innerHTML = "Please Enter valid account Number.";
		return false;
	}

	else if (document.getElementById('amount').value > 100000
			|| document.getElementById('amount').value < 1) {
		document.getElementById('errors').innerHTML = "The amout allowed is between $1 and $100000.";
		return false;
	}
	document.getElementById('transfer-form').action = "transferSuccess.html";
	return true;
}

function checkPassword() {
	if (document.getElementById('password').value != document
			.getElementById('repassword').value) {
		alert("The entered Passwords do not match.");
		return false;
	}
	return true;
}

function validateViewStmtForm() {

	if (document.getElementById('acctId').value == ""
			|| document.getElementById('acctId').value == null
			|| isNaN(document.getElementById('acctId').value)) {
		document.getElementById('errors').innerHTML = "Please Enter an Account Number to check the statements";
		return false;
	}
	document.getElementById('view-stmts-form').action = "viewStmts1.html";
	return true;
}

function validatePassword(pwd) {
	var reggex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{6,20}$/;
	if (!pwd.value.match(reggex)) {
		alert('Password must contain 6 to 20 characters with at least one upper case, lower case and special character.')
		return false;
	}
	return true;
}

function checkIfEmpty() {
	if (document.getElementById('username').value == ""
			|| document.getElementById('username').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Username.";
		return false;
	} else if (document.getElementById('password').value == ""
			|| document.getElementById('password').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Password.";
		return false;
	} else if (document.getElementById('repassword').value == ""
			|| document.getElementById('repassword').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Password second time.";
		return false;
	} else if (document.getElementById('firstname').value == ""
			|| document.getElementById('firstname').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the First Name.";
		return false;
	} else if (document.getElementById('lastname').value == ""
			|| document.getElementById('lastname').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Last Name.";
		return false;
	}

	else if (document.getElementById('ssn').value == ""
			|| document.getElementById('ssn').value == null 
			|| isNaN(document.getElementById('ssn').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid SSN.";
		return false;
	}

	else if (document.getElementById('usertype').value == "SELECT"
			|| document.getElementById('usertype').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the User Type.";
		return false;
	} else if (document.getElementById('usertype').value == "EMPLOYEE"
			|| document.getElementById('usertype').value == "MANAGER") {
		if (document.getElementById('employeeid').value == ""
				|| document.getElementById('employeeid').value == null) {
			document.getElementById('errors').innerHTML = "Please Enter the Employee Id.";
			return false;
		}
	} else if (document.getElementById('dob').value == ""
			|| document.getElementById('dob').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Date Of Birth.";
		return false;
	} else if (document.getElementById('addressline1').value == ""
			|| document.getElementById('addressline1').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Address Line 1.";
		return false;
	} else if (document.getElementById('addressline2').value == ""
			|| document.getElementById('addressline2').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Address Line 2.";
		return false;
	} else if (document.getElementById('city').value == ""
			|| document.getElementById('city').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the City.";
		return false;
	} else if (document.getElementById('state').value == ""
			|| document.getElementById('state').value == null
			|| document.getElementById('state').value == "State") {
		document.getElementById('errors').innerHTML = "Please Enter the State.";
		return false;
	} else if (document.getElementById('zipcode').value == ""
			|| document.getElementById('zipcode').value == null
			|| isNaN(document.getElementById('zipcode').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid Zip Code.";
		return false;
	} else if (document.getElementById('email').value == ""
			|| document.getElementById('email').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Email Id.";
		return false;
	} else if (document.getElementById('phonenumber').value == ""
			|| document.getElementById('phonenumber').value == null
			|| isNaN(document.getElementById('phonenumber').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid Phone Number.";
		return false;
	} else if (document.getElementById('gender').value == ""
			|| document.getElementById('gender').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Gender.";
		return false;
	} else if (document.getElementById('checkbox').checked == false) {
		document.getElementById('errors').innerHTML = "Please accept the agreement.";
		return false;
	} else if (document.getElementById('secQuestion_1').value == ""
			|| document.getElementById('secQuestion_1').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Security Question 1.";
		return false;
	} else if (document.getElementById('secQuestion_2').value == ""
			|| document.getElementById('secQuestion_2').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Security Question 2.";
		return false;
	} else if (document.getElementById('secQuestion_3').value == ""
			|| document.getElementById('secQuestion_3').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Security Question 3.";
		return false;
	} else if (document.getElementById('secanswer_1').value == ""
			|| document.getElementById('secanswer_1').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Security Answer 1.";
		return false;
	} else if (document.getElementById('secanswer_2').value == ""
			|| document.getElementById('secanswer_2').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Security Answer 2.";
		return false;
	} else if (document.getElementById('secanswer_3').value == ""
			|| document.getElementById('secanswer_3').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Security Answer 3.";
		return false;
	} else if (document.getElementById('usertype').value == "CUSTOMER"
			|| document.getElementById('usertype').value == "MERCHANT") {
		if (document.getElementById('accBalance').value == ""
				|| document.getElementById('accBalance').value == null
				|| isNaN(document.getElementById('accBalance').value)) {
			document.getElementById('errors').innerHTML = "Please Enter the valid Account Balance.";
			return false;
		} else if (document.getElementById('accBalance').value <= 0
				|| document.getElementById('accBalance').value == null) {
			document.getElementById('errors').innerHTML = "Account Balance should be greater than zero";
			return false;
		}
	}
	return true;
}

function validateEmail(emailField) {
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if (reg.test(emailField.value) == false) {
		alert('Invalid Email Address!');
		return false;
	}
	return true;
}

function validatePhone(phoneNumber) {
	if (isNaN(parseFloat(phoneNumber.value)) || phoneNumber.value.length != 10) {
		alert('Invalid Phone Number!');
		return false;
	}
	return true;
}

function validateSSN(ssn) {
	if (isNaN(parseFloat(ssn.value)) || ssn.value.length != 9) {
		alert('Invalid SSN!');
		return false;
	}
	return true;
}

function showEmployeeId() {
	if (document.getElementById('usertype').value == "EMPLOYEE"
			|| document.getElementById('usertype').value == "MANAGER") {
		document.getElementById('accountData').style.visibility = "hidden";
		document.getElementById('empId').style.visibility = "visible";
	} else {
		document.getElementById('accountData').style.visibility = "visible";
		document.getElementById('empId').style.visibility = "hidden";
	}
}

function checkEmptyFields() {
	if (document.getElementById('oldpassword').value == ""
			|| document.getElementById('oldpassword').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Old Password.";
		return false;
	} else if (document.getElementById('password').value == ""
			|| document.getElementById('password').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the New Password.";
		return false;
	} else if (document.getElementById('repassword').value == ""
			|| document.getElementById('repassword').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the Password second time.";
		return false;
	} else if (document.getElementById('otp').value == ""
			|| document.getElementById('otp').value == null
			|| isNaN(document.getElementById('otp').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the valid OTP.";
		return false;
	}
	return true;
}

// Declaring valid date character, minimum year and maximum year
var dtCh = "/";
var minYear = 1900;
var maxYear = 2100;

function isInteger(s) {
	var i;
	for (i = 0; i < s.length; i++) {
		// Check that current character is number.
		var c = s.charAt(i);
		if (((c < "0") || (c > "9")))
			return false;
	}
	// All characters are numbers.
	return true;
}

function stripCharsInBag(s, bag) {
	var i;
	var returnString = "";
	// Search through string's characters one by one.
	// If character is not in bag, append to returnString.
	for (i = 0; i < s.length; i++) {
		var c = s.charAt(i);
		if (bag.indexOf(c) == -1)
			returnString += c;
	}
	return returnString;
}

function daysInFebruary(year) {
	// February has 29 days in any year evenly divisible by four,
	// EXCEPT for centurial years which are not also divisible by 400.
	return (((year % 4 == 0) && ((!(year % 100 == 0)) || (year % 400 == 0))) ? 29
			: 28);
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i == 4 || i == 6 || i == 9 || i == 11) {
			this[i] = 30
		}
		if (i == 2) {
			this[i] = 29
		}
	}
	return this
}

function isDate(dtStr) {
	var daysInMonth = DaysArray(12)
	var pos1 = dtStr.indexOf(dtCh)
	var pos2 = dtStr.indexOf(dtCh, pos1 + 1)
	var strMonth = dtStr.substring(0, pos1)
	var strDay = dtStr.substring(pos1 + 1, pos2)
	var strYear = dtStr.substring(pos2 + 1)
	strYr = strYear
	if (strDay.charAt(0) == "0" && strDay.length > 1)
		strDay = strDay.substring(1)
	if (strMonth.charAt(0) == "0" && strMonth.length > 1)
		strMonth = strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0) == "0" && strYr.length > 1)
			strYr = strYr.substring(1)
	}
	month = parseInt(strMonth)
	day = parseInt(strDay)
	year = parseInt(strYr)
	if (pos1 == -1 || pos2 == -1) {
		alert("The date format should be : mm/dd/yyyy")
		return false
	}
	if (strMonth.length < 1 || month < 1 || month > 12) {
		alert("Please enter a valid month")
		return false
	}
	if (strDay.length < 1 || day < 1 || day > 31
			|| (month == 2 && day > daysInFebruary(year))
			|| day > daysInMonth[month]) {
		alert("Please enter a valid day")
		return false
	}
	if (strYear.length != 4 || year == 0 || year < minYear || year > maxYear) {
		alert("Please enter a valid 4 digit year between " + minYear + " and "
				+ maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh, pos2 + 1) != -1
			|| isInteger(stripCharsInBag(dtStr, dtCh)) == false) {
		alert("Please enter a valid date")
		return false
	}
	return true
}

function validateTransStatusForm(i) {
	var transStatusString = "";
	var transIDs = "";
	for (j = 1; j < i; j++) {
		transIDs = transIDs + ","
				+ document.getElementById("transID" + j).value;
		if (document.getElementById("authorize" + j).checked == true) {
			transStatusString = transStatusString + "," + "true";
		} else if (document.getElementById("decline" + j).checked == true) {
			transStatusString = transStatusString + "," + "false";
		} else {
			transStatusString = transStatusString + "," + "none";
		}
	}
	document.getElementById("transStatusString").value = transStatusString;
	document.getElementById("transIDs").value = transIDs;
	document.getElementById('trans-status').action = "authorizeTrans.html";
}

//Validate the pending requests
function validatePendingRequestStatusForm(i) {
	var requestStatusString = "";
	var requestIDs = "";
	for (j = 1; j < i; j++) {
		requestIDs = requestIDs + ","
				+ document.getElementById("requestIDs" + j).value;
		if (document.getElementById("authorize" + j).checked == true) {
			requestStatusString = requestStatusString + "," + "true";
		} else if (document.getElementById("decline" + j).checked == true) {
			requestStatusString = requestStatusString + "," + "false";
		} else {
			requestStatusString = requestStatusString + "," + "none";

		}
	}
	document.getElementById("requestStatusString").value = requestStatusString;
	document.getElementById("requestIDs").value = requestIDs;

	document.getElementById('request-status').action = "authorizePendingRequests.html";
}

function hashPassword() {
	var encrypted = CryptoJS.MD5(document.getElementById('password').value
			+ document.getElementById('salt').value);
	document.getElementById('password').value = encrypted;
}

function validateInitpayment() {

	if (document.getElementById('email').value == ""
			|| document.getElementById('email').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the email.";
		return false;
	} else if (validateEmail(document.getElementById('email')) == false) {
		document.getElementById('errors').innerHTML = "Please Enter a valid email.";
		return false;
	} else if (document.getElementById('amount').value == ""
			|| document.getElementById('amount').value == null
			|| isNaN(document.getElementById('amount').value)) {
		document.getElementById('errors').innerHTML = "Please Enter the Amount.";
		return false;
	} else if (document.getElementById('amount').value > 100000
			|| document.getElementById('amount').value < 1) {
		document.getElementById('errors').innerHTML = "The amout allowed is between $1 and $100000.";
		return false;
	}
	document.getElementById('initiate-payment-form').action = "InitiatePaymentDone.html";
	return true;
}

function validateBillpay(n) {
	var paymentStatusString = "";
	var paymentIDs = "";
	for (j = 1; j < n; j++) {
		paymentIDs = paymentIDs + ","
				+ document.getElementById("paymentID" + j).value;
		if (document.getElementById("authorize" + j).checked == true) {
			paymentStatusString = paymentStatusString + "," + "true";
		} else if (document.getElementById("decline" + j).checked == true) {
			paymentStatusString = paymentStatusString + "," + "false";
		} else {
			paymentStatusString = paymentStatusString + "," + "none";
		}
	}
	document.getElementById("paymentStatusString").value = paymentStatusString;
	document.getElementById("paymentIDs").value = paymentIDs;
	document.getElementById('billpay').action = "BillpaySuccess.html";
}

function checkOldNewPassword() {
	if (document.getElementById('password') == document
			.getElementById('oldpassword')) {
		alert("New password can not be same as old password!");
		return false;
	}
	return true;
}

function validateState(sstate) {
	sstates = "wa|or|ca|ak|nv|id|ut|az|hi|mt|wy"
			+ "co|nm|nd|sd|ne|ks|ok|tx|mn|ia|mo"
			+ "ar|la|wi|il|ms|mi|in|ky|tn|al|fl"
			+ "ga|sc|nc|oh|wv|va|pa|ny|vt|me|nh" + "ma|ri|ct|nj|de|md|dc";
	if (sstates.indexOf(sstate.toLowerCase() + "|") > -1) {
		return true;
	}
	alert("Enter the correct state code.");
	return false;
}

function checkAnswers() {
	if (document.getElementById('secanswer_1').value.length < 6
			|| document.getElementById('secanswer_2').value.length < 6
			|| document.getElementById('secanswer_3').value.length < 6) {
		document.getElementById('errors').innerHTML = "The minimum length for security answers is 6.";
		return false;
	}
	return true;
}