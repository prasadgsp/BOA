<!DOCTYPE html>
<html>
<head>
<Title>Unlock Password</Title>
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
<!--  <h1>Message : ${message}</h1> -->
<h1>Welcome to United Bank Of Arizona</h1>
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
	<form id="unlock-password-form" method="POST"
		commandName="UnlockPasswordModel" ModelAttribute=UnlockPasswordModel>
		<div id="errors" style="color: #ff0000"></div>
		<label>Enter your user name</label> <input type="text" name="username" id="username" path="username"><br /> <br>
		<br>
		<label><input type="submit"
			class="btn btn-lg btn-primary btn-block"
			onclick="return validateUnlockPasswordForm();" name="submit"
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