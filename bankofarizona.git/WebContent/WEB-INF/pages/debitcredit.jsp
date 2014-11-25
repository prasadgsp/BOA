<!DOCTYPE html>
<html>

<head>
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
<script src="js/keyboard.js" type="text/javascript" charset="UTF-8"></script>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="css/keyboard.css" rel="stylesheet"
	type="text/css" />
	<!--Scripts-->

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
<form id="debit-credit-form" method="POST"
		commandName="DebitCreditModel"
		ModelAttribute="DebitCreditModel">
		<div id= "errors" style="color: #ff0000">
		</div>
		<label>Enter Amount </label> 
		<input type="text"
			name="amount" id="amount" path="amount" class="keyboardInput" ><br /> <br>
		<label>Select From Account </label> 
		<select name="fromAcct" size="1" id="fromAcct" path="fromAcct">
		<c:if test="${not empty acctlist}">
					<c:forEach var="acctitem" varStatus="status" items="${acctlist}">
						<option value="${acctitem.getAccNum()}"><c:out value="${acctitem.getAccNum()}" /></option>
					</c:forEach>
		</c:if>
		</select>
		<c:if test = "${otp1 == 'Yes'}">
		<label>Enter OTP</label> 
		<input type="number"
			name="otp" id="otp" path="otp" class="keyboardInput"><br /> <br>
		<label><input type="submit"
			class="btn btn-primary"
			onclick="return validateDebitCreditForm3();" name="Debit" id="Debit"
			value="Debit" path="Debit"></label>
		  <label><input type="submit"
			class="btn btn-primary "
			onclick="return validateDebitCreditForm4();" name="Credit" id="Credit"
			value="Credit" path="Credit"></label><br />	
			
		</c:if>
		
		<c:if test = "${otp1 == 'No'}">
		<label><input type="submit"
			class="btn btn-primary"
			onclick="return validateDebitCreditForm1();" name="Debit" id="Debit"
			value="Debit" path="Debit"></label>
		  <label><input type="submit"
			class="btn btn-primary "
			onclick="return validateDebitCreditForm2();" name="Credit" id="Credit"
			value="Credit" path="Credit"></label><br />
		</c:if>	
	</form>

</div>

</body>
</html>