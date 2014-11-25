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
<%@include file="/WEB-INF/pages/header2.jsp" %>
	<h2>${message}</h2>
	<form id="delete-account-form" method="POST" action = "deleteuserAccount.html"
		commandName="DeleteAccountModel" ModelAttribute="DeleteAccountModel">
		<div id="errors" style="color: #ff0000"></div>
		<label>Enter the Account Id</label> <input type="number" name="accNum" id="accNum" path="accNum" required="required"><br /> <br>
		<br>
		<label><input type="submit"
			class="btn btn-lg btn-primary btn-block"
		   name="submit"
			id="submit" value="Submit" onsubmit="submit"></label><br />
		
		<br />
	</form>
	</div>
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
	
</body>
</html>