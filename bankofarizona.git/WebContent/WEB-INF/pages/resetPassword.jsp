<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<Title>Reset Password</Title>
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
	<c:choose>
			<c:when
				test="${usertype == 'MANAGER' || usertype == 'EMPLOYEE'}">
				<%@include file="/WEB-INF/pages/header2.jsp"%>
			</c:when>
			<c:otherwise>
        		<%@include file="/WEB-INF/pages/header3.jsp"%>
    		</c:otherwise>
		</c:choose>
	<form id="reset-password-form" method="POST"
		commandName="ResetPasswordModel"
		ModelAttribute="ResetPasswordModel">
		<div id= "errors" style="color: #ff0000">
		</div>
		Old Password <br>
		<input type="password"
			name="oldpassword" id="oldpassword" path="oldpassword" class="keyboardInput"><br /> <br>
		Enter New Password<br> 
		<input type="password"
			name="password" id="password" path="password" value="" class="keyboardInput"><br>
		Re-enter Password
		<br><input type="password" name="repassword" path="repassword"
			id="repassword" value="" class="keyboardInput"><br>
		Enter OTP<br><input type="text" name="otp" id="otp" path="otp" class="keyboardInput">
		<br /> <br> <br>
		<input type="hidden" id="salt" value="${salt }">
		<input type="submit"
			class="btn btn-lg btn-primary btn-block"
			onclick="return validateResetPasswordForm();" name="reset" id="reset"
			value="Reset"><br />
	</form>
</div>
<!--Scripts-->
<script src="js/custom.js" type="text/javascript"></script>
<script type="text/javascript" src="js/aes.js"></script>
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="js/keyboard.js" charset="UTF-8"></script>
</body>
</html>