<!DOCTYPE html>
<html>
<head>
<Title>Forgot Password</Title>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
	<link rel="stylesheet" type="text/css" href="css/keyboard.css" />

<style>
label {
	width: 10em;
	float: left;
	margin-left: 5.5em;
	margin-right: 0.5em;
	display: block
}

longlabel {
	width: 100em;
	float: left;
	margin-right: 0.5em;
	display: block
}
</style>
</head>
<body>
<noscript>
	<h3>
	Please enable JavaScript in your web browser!</h3>
 
	<style type="text/css">
		#main-content { display:none; }
	</style>
</noscript>
 
<div id="main-content">
	<%@include file="/WEB-INF/pages/header1.jsp" %>
	<form id="forgot-password-form2" method="POST"
		commandName="ForgotPasswordModel" ModelAttribute="ForgotPasswordModel">
		<div id="errors" style="color: #ff0000"></div>
		<label>Enter your user name</label> <input type="text" name="username" id="username" path="username" value=${userid.getValue() } disabled><br /> <br>
		<br>
		<label>
			Enter the OTP
		</label> <input type="password" name="otp"
			id="otp" path="otp" class="keyboardInput"><br /> <br>
		<label><p>
			 ${Questions.getQues1() }
		</p></label>
		<input type="text" name="secanswer_1" id="secanswer_1"
			path="secanswer_1"/></longlabel>
		<br /> <br>
		<p>
			 ${Questions.getQues2() }
		</p>
		<longlabel>
		<input type="text" name="secanswer_2" id="secanswer_2"
			path="secanswer_2"></longlabel>
		<br /> <br>
		<p>
			${Questions.getQues3() }
		</p>
		<longlabel>
		<input type="text" name="secanswer_3" id="secanswer_3"
			path="secanswer_3"/></longlabel>
		<br /> <br>
		<label>Enter Password</label> 
		<input type="password" name="password" id="password" path="password" required="required" class="keyboardInput" ><br /><br> 
		<label>Reenter Password</label> 
		<input type="password" name="repassword" id="repassword" path="repassword" required="required" class="keyboardInput"><br /> <br>
		
		<input type="hidden" id="salt" value="${salt }">
		
		<label><input type="submit"
			class="btn btn-lg btn-primary btn-block"
			onclick="return validateForgotPasswordFormVisible2();" name="submit"
			id="submit" value="Submit"></label><br />
		<br />
	</form>
	</div>
	
	<!--Scripts-->
<script type="text/javascript" src="js/aes.js"></script>
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/forgotPassword.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
<script type="text/javascript" src="js/keyboard.js" charset="UTF-8"></script>
</body>
</html>