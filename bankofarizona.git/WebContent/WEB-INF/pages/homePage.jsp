<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<Title>Login Page</Title>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="css/keyboard.css" />

<style>
label {
	width: 7em;
	float: left;
	margin-right: 0.5em;
	margin-left: 5.5em;
	dispay: block
}

links {
	width: 20em;
	float: left;
	margin-right: 0.5em;
	margin-left: 5.5em;
	dispay: block
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
	<form id="login-form" name="login-form"method="POST"
	commandName="LoginModel" ModelAttribute="LoginModel">
		<div id= "errors" style="color: #ff0000">
		</div>
		<c:if test="${not empty error}">
                       <div class="error">${error}</div>
               </c:if>
               <c:if test="${not empty msg}">
                       <div class="msg">${msg}</div>
               </c:if>
		<br> <br> <label>User Name</label> <input type="text" id="username"
			name="j_username" path="username" placeholder="User ID" autofocus="autofocus" required="required" autocomplete="off"  class="keyboardInput"><br>
		<br> <label>Password</label> <input path="password" id="password"
			name="j_password" type="password" placeholder="Password" required="required" autocomplete="off"  class="keyboardInput">
		<br> <br> <label><button
				class="btn btn-lg btn-primary btn-block" type="submit" onclick="return hashPasswordLogin();">Sign
				in</button></label> <br> <br> <br>
		<links> Forgot your password? Click<a href="forgotPassword">
			here </a> </links>
		<br> <br>
		<links> Want to Access PII? Click<a href="accessPII">
			here </a> </links>
		<br> <br>
			<links> User Locked? Click to unlock<a href="unlockPassword">
			here </a> </links>
			
		<input type="hidden" id="salt" value="${salt }">
	</form>
	</div>
	
	<script>
	function hashPasswordLogin(){
		var encrypted = CryptoJS.MD5(document.getElementById('password').value+document.getElementById('salt').value);
		//alert(document.getElementById('password').value+document.getElementById('salt').value);
		document.getElementById('password').value = encrypted;
	    //alert(encrypted);
	    document.getElementById('login-form').action = "j_spring_security_check";
	    return true;
	}
	</script>

<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
<script type="text/javascript" src="js/keyboard.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/aes.js"></script>
</body>
</html>
