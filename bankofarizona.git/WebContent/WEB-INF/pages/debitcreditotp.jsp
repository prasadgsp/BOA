<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />

<title>United Bank of Arizona</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    
}
glabel{
width: 7em;
float: center;
margin-left: 50em;
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
<form id="debit-credit-otp-form" method="GET"
		commandName="DebitCreditModel"
		ModelAttribute="DebitCreditModel">
		<div id= "errors" style="color: #ff0000">
		</div>
		<label>Is Debit/Credit a critical transaction ( > $10000) ?</label> 
	
		<select name="transtype" size="1" id="transtype" path="transtype">
						<option value="Yes">Yes</option>
						<option value="No">No</option>
		</select>
		<label><input type="submit"
			class="btn btn-primary"
			onclick="return validateDebitCreditOTPForm();" name="dcotp" id="dcotp"
			value="Submit" path="dcotp"></label>

	</form>

</div>
<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>