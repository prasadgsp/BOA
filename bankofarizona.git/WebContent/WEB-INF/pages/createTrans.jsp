<html>

<head>
<Title>United Bank Of Arizona</Title>
<meta name="viewport" content="width=device-width">

<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">

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
	<form id="create-trans" method="POST"
		commandName="TransferModel" ModelAttribute="TransferModel">
		<label>Amount </label> 
		<input type="text" name="amount" id="amount" path="amount" maxlength="6"><br /> <br> 
		<label>Enter From Account</label> 
		<input type="text" name="fromAcct" id="fromAcct" path="fromAcct"><br /><br> 
		<label>Enter To Account if Exists</label> 
		<input type="text" name="toAcct" id="toAcct" path="toAcct"><br /> <br> 
				
		<longlabel>Transaction Type
		<select name="transType" id="transType" path="transType">
			<option value="TRANSFER">Transfer</option>
		</select> </longlabel>


		<br> <br> <label><input name="createtrans"
			class="btn btn-lg btn-primary btn-block" type="submit" id="createtrans"
			value="Create Transaction" onclick="return validateCreateTransForm();"></label><br />
		<div id="errors" style="color: #ff0000"></div>
	</form>
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>
