
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<Title>View Account Details</Title>
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
	<form class="account-details" method="POST" commandName="ViewAccountModel"
		ModelAttribute="ViewAccountModel">
		<%@include file="/WEB-INF/pages/header2.jsp" %>
	<h2>${message}</h2>		
		<br> <label>User Id </label> <input type="text"
			name="userId" id="userId" value=${accdetails.getUserId() } readonly="readonly" /><br /> <br> 
		<br> <label>Account Number </label> <input type="text"
			name="accNum" id="accNum" value=${accdetails.getAccNum() } readonly="readonly" /><br /> <br> 
		<label>Balance</label> <input type="text" name="balance"
			id="balance" value=${accdetails.getBalance() } readonly="readonly" /><br />
		<br><label>Date Of Open</label><input type="text"  name="dateOfOpen" id="dateOfOpen"
		value=${accdetails.getDateOfOpen() } readonly="readonly"/><br /> <br>
		<br><label>Date Of Close</label><input readonly="readonly"  type="text" name="dateOfClose" id="dateOfClose"
		value=${accdetails.getDateOfClose() } ><br />
			<br /> <br> <label>Account Status </label> <input type="text"
			name="accStatus" id="accStatus" value=${accdetails.getAccStatus() } readonly="readonly" /> <br />
	</form>
	
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>