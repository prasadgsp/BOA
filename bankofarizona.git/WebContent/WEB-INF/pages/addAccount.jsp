<html>

<head>
<Title>Sign Up User</Title>
<meta name="viewport" content="width=device-width">

<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/jcap.js"></script>

<style>
label {
	width: 10em;
	float: left;
	margin-left: 5.5em;
	margin-right: 0.5em;
	display: block
}

longlabel {
	width: 50em;
	float: left;
	margin-left: 5.5em;
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

<%@include file="/WEB-INF/pages/header2.jsp" %>
	<form id="add-acc" method="POST"
		commandName="addAccountModel" ModelAttribute="addAccountModel">
		<label>UserID</label> 
		<input type="text" name="userID" id="userID" path="userID" maxlength="20" autocomplete="off"><br /> <br> 
		<label>Initial Balance </label> 
		<input type="text" name="balance" id="balance" path="balance" /><br /> <br> 
		<br> <br> <label><input name="signup"
			class="btn btn-lg btn-primary btn-block" type="submit" id="addAcc"
			value="Add" onclick="return validateaddaccForm();"></label><br />
		<div id="errors" style="color: #ff0000"></div>
	</form>
	
</div>
<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>
		
