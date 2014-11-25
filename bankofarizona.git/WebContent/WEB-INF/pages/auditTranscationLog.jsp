

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<Title>Audit Transcations</Title>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />

<style>
label {
	width: 100em;
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
<%@include file="/WEB-INF/pages/header2.jsp"%>
	<form id="audit" class="audit" method="POST" commandName="AuditTranscationsModel"
		ModelAttribute="AuditTranscationsModel">
		<label>Audit start Date</label> 
		<input type="text" name="date" id="date" path="date" /> <br> 
		
		
		 <label><input name="Download file"
			type="submit" id="Download"
			value="Download" onclick="return validateauditForm();"></label><br />
		<div id="errors" style="color: #ff0000"></div>
		<!-- //<input type="submit" name="submit" id="submit" value="Click here to download the Audit file" onclick="validateauditForm()"></label>	 -->
		
		</form>
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>
