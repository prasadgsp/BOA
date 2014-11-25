<!DOCTYPE html>
<html>

<head>
<Title>United Bank Of Arizona</Title>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    
}
label{
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

<%@include file="/WEB-INF/pages/header3.jsp" %>
	<h2>${message}</h2>
	<form id="add-authorization" method="POST"
		commandName="AddAuthorizationModel" ModelAttribute="AddAuthorizationModel" action = "addAuthorizedUser.html">
		<label>Add Email</label> 
		<input type="text" name="email" id="email" path="email" required="required"><br /> <br> 
		<label>Add Account no. </label> 
		<input type="accountno" name="accountno" id="accountno" path="accountno" required="required"><br /><br> 
		<br> <br> <label><input name="addauthorization"
			class="btn btn-lg btn-primary btn-block" type="submit" id="addauthorization"
			value="Add Authorize User" onsubmit="submit"></label><br />
		<div id="errors" style="color: #ff0000"></div>
		</form>
		</div>
		
		<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>