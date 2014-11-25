<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
	<link href="css/keyboard.css" rel="stylesheet"
	type="text/css" />
	
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
<script src="js/keyboard.js" type="text/javascript"></script>

<title>Transfer - UBA</title>
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
<form id="transfer-form" method="POST"
		commandName="TransferModel"
		ModelAttribute="TransferModel" enctype="multipart/form-data">
		<div id= "errors" style="color: #ff0000">
		</div>
		<label>Enter Amount!! </label> 
		 <input type="text"
			name="amount" id="amount" path="amount" class="keyboardInput" > <br /> <br>	
		<label>Select From Account </label> 
		<select name="fromAcct" size="1" id="fromAcct" path="fromAcct">
		<c:if test="${not empty acctlist}">
					<c:forEach var="acctitem" varStatus="status" items="${acctlist}">
						<option value="${acctitem.getAccNum()}"><c:out value="${acctitem.getAccNum()}" /></option>
					</c:forEach>
		</c:if>
		</select>
		<!-- <label>Enter To Account Number </label> 
		<input type="toAcct"
			name="toAcct" id="toAcct" path="toAcct"><br /> <br> -->
				<label>Select To Account  </label> 	
		<select name="toAcct" size="1" id="toAcct" path="toAcct">
		<c:if test="${not empty authList}">
					<c:forEach var="authitem" varStatus="status" items="${authList}">
						<option value="${authitem.getAuthAccont()}"><c:out value="${authitem.getAuthAccont()} - ${authitem.getAuthSenderId()}" /></option>
					</c:forEach>
		</c:if>
		</select>
		
		<h3>Upload File</h3>
						
		<label>
			Public Key <br>
		<input name="secretKeyfile" type="file" class="form-control" />
		</label>
		<br><br>
			
		<label><input type="submit"
			class="btn btn-primary"
			onclick="return validateTransferForm();" name="Transfer" id="Transfer"
			value="Transfer" path="Transfer"></label>
	</form>

</div>


</body>
</html>