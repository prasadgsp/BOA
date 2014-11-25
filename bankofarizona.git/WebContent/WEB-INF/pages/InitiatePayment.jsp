<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
<title>Initiate Payment - UBA</title>
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
<%@include file="/WEB-INF/pages/header3.jsp" %>


<form id="initiate-payment-form" method="GET"
		commandName="InitiatePaymentModel"
		ModelAttribute="InitiatePaymentModel">
		<div id= "errors" style="color: #ff0000">
		</div>
		
		<label>Enter Customer Email </label> 
		<input type="text"
			name="email" id="email" path="email" /> <br /> <br>
			
		<label>Enter Customer Account Number </label> 
		<input type="text"
			name="acctnum" id="acctnum" path="acctnum" /> <br /> <br>
			
		<label>Enter Amount Due </label> 
		<input type="text"
			name="amount" id="amount" path="amount" /> <br /> <br>
			
		<label>Description </label> 
		<input type="text"
			name="desc" id="desc" path="desc" /> <br /> <br>
			
		<label><input type="submit"
			class="btn btn-primary"
			onclick="return validateInitpayment();" name="initpay" id="initpay"
			value="submit"  /></label>
			
</form>
</body>
</html>

		