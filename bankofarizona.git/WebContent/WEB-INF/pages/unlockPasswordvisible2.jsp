<!DOCTYPE html>
<html>
<head>
<Title>Forgot Password</Title>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />


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
	<form id="unlock-password-form2" method="POST"
		commandName="UnlockPasswordModel" ModelAttribute="UnlockPasswordModel">
		<div id="errors" style="color: #ff0000"></div>
		<label>Enter your user name</label> <input type="text" name="username" id="username" path="username" value=${userid.getValue() } disabled><br /> <br>
		<br>
		<label>
			Enter the OTP
		</label> <input type="password" name="otp"
			id="otp" path="otp"><br /> <br>		
		<label><input type="submit"
			class="btn btn-lg btn-primary btn-block"
			onclick="return validateUnlockPasswordFormVisible2();" name="submit"
			id="submit" value="Submit"></label><br />
		<br />
	</form>
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/forgotPassword.js" type="text/javascript"></script>
</body>
</html>