<!DOCTYPE html>
<html>

<head>
<Title>United Bank Of Arizona</Title>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
	<link rel="stylesheet" type="text/css" href="css/keyboard.css" />

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
	<form id="view-account-form" method="POST" action = "viewuserAccount.html"
		commandName="ViewAccountModel" ModelAttribute="ViewAccountModel">
		<div id="errors" style="color: #ff0000"></div>
		<label>Enter the account number</label> <input type="number" name="accNum" id="accNum" path="accNum" required="required"><br /> <br>
		<br>
		<label><input type="submit"
			class="btn btn-lg btn-primary btn-block"
		   name="submit"
			id="submit" value="Submit" onsubmit="submit"></label><br />
		<br />
	</form>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	---OR---
	<br/>
	<br/>
	<form id="view-account-form" method="GET" action = "viewAccounts.html"
		commandName="ViewAccountModel" ModelAttribute="ViewAccountModel">
		<div id="errors" style="color: #ff0000"></div>
		<label><input type="submit"
			class="btn btn-lg btn-primary"
		   name="submit"
			id="submit" value="View All Accounts" onsubmit="submit"></label><br />
		<br />
	</form>
	

<table style="width:50%">
<tr style="background-color: Bisque">
   <th>Account Numbers</th>
   <th>Balance</th>
   <th>User Id</th>
   <th>Date of Open</th>
    <th>Date of Close</th>
     <th>Account Status</th>
 </tr>  
 <c:if test="${not empty AcctModel}">
					<c:forEach var="acctitem" varStatus="status" items="${AcctModel}">
						<tr>
							<td><c:out value="${acctitem.getAccNum() }" /></td>
							<td><c:out value="${acctitem.getBalance() }" /></td>
							<td><c:out value="${acctitem.getUserId() }" /></td>
							<td><c:out value="${acctitem.getDateOfOpen() }" /></td>
							<td><c:out value="${acctitem.getDateOfClose() }" /></td>
							<td><c:out value="${acctitem.getAccStatus() }" /></td>
						</tr>
					</c:forEach>
				</c:if>
</table>
	
	
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
<script type="text/javascript" src="js/keyboard.js" charset="UTF-8"></script>
</body>
</html>