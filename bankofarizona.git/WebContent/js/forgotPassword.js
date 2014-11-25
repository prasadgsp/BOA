/**
 * 
 */
//*****Forogt Password Validation Function
function validateForgotPasswordForm()
{
	if(document.getElementById('username').value=="" || document.getElementById('username').value== null) {
		document.getElementById('errors').innerHTML="Please Enter the Username.";
		return false;
	}		
	document.getElementById('forgot-password-form').action = "forgotPasswordvisible.html";
	return true;
}

//*****Forogt Password Validation Function
function validateForgotPasswordFormVisible2()
{
	if (document.getElementById('otp').value == ""
		|| document.getElementById('otp').value == null
		|| isNaN(document.getElementById('otp').value)) {
	document.getElementById('errors').innerHTML = "Please Enter the valid OTP.";
	return false;
	}	
	else if(document.getElementById('secanswer_1').value=="" || document.getElementById('secanswer_1').value== null) {
		document.getElementById('errors').innerHTML="Please Enter the Security Answer 1.";
		return false;
	}
	else if(document.getElementById('secanswer_2').value=="" || document.getElementById('secanswer_2').value== null) {
		document.getElementById('errors').innerHTML="Please Enter the Security Answer 2.";
		return false;
	}
	else if(document.getElementById('secanswer_3').value=="" || document.getElementById('secanswer_3').value== null) {
		document.getElementById('errors').innerHTML="Please Enter the Security Answer 3.";
		return false;	
	}
	// Check if passwords matches or not
	else if (document.getElementById('password').value != document
			.getElementById('repassword').value) {
		alert("The entered Passwords do not match.");
		return false;
	} else if(!validatePassword(document.getElementById('password'))) {
		alert('Password must contain 6 to 20 characters with at least one upper case, lower case and special character.')
		return false;
	}
	hashPassword();
	document.getElementById('forgot-password-form2').action = "checkAnswers.html";
	return true;
}

//*****Add Authorization Validation Function
function validateAddauthorizeuserForm()
{
	if(document.getElementById('email').value=="" || document.getElementById('email').value== null) {
		document.getElementById('errors').innerHTML="Please Enter the email Id.";
		return false;
	}		
	document.getElementById('add-authorization-form').action = "addAuthorization.html";
	return true;
}

//**Unlock Password Validation Function
function validateUnlockPasswordForm()
{
	if(document.getElementById('username').value=="" || document.getElementById('username').value== null) {
		document.getElementById('errors').innerHTML="Please Enter the Username.";
		return false;
	}		
	document.getElementById('unlock-password-form').action = "unlockPasswordvisible.html";
	return true;
}


//*****Unlock Password Validation Function
function validateUnlockPasswordFormVisible2()
{
	if(document.getElementById('otp').value=="" || document.getElementById('otp').value== null) {
		document.getElementById('errors').innerHTML="Please Enter the One Time Password.";
		return false;
	}	
	document.getElementById('unlock-password-form2').action = "validateOTP.html";
	return true;
}