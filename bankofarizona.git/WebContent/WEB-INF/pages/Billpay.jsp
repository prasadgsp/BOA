<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<title> Pay Bills - UBA</title>
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
<%
int i = 1;
String paymentIDs = "";
%>

	<h3>
	Please enable JavaScript in your web browser!</h3>
 
	<style type="text/css">
		#main-content { display:none; }
	</style>
</noscript>

<%@include file="/WEB-INF/pages/header3.jsp" %>

<form id="billpay" method="POST" commandName="BillpayModel" ModelAttribute="BillpayModel">
<table class="table table-striped table-bordered">
				<tr>
					
					<th>Merchant Name</th>
					<th>Amount</th>
					<th>Description</th>
					<th>Authorize/Decline</th>
					
					
				</tr>	
				<c:if test="${not empty InitiatePaymentModel}">
					<c:forEach var="billitem" varStatus="status" items="${InitiatePaymentModel}">
					
					<%
							String name = "authorize/decline" + i;
							String id1 = "authorize" + i;
							String id2 = "decline" + i;
							String id3 = "paymentID" + i;
						%>
					
					
						<tr>
							
							<td><c:out value="${billitem.getMerchantId() }" /></td>
							<td><c:out value="${billitem.getPaymentAmount() }" /></td>
							<td><c:out value="${billitem.getPaymentDesc() }" /></td>
							
							
							<td> <input type="radio" name=<%=name%> id=<%=id1%>> Pay Now &nbsp;&nbsp;
							<input type="radio" name=<%=name%> id=<%=id2%>> Decline </td>
							
						</tr>
						<input type="hidden" id=<%=id3 %> value="<c:out value="${billitem.getPaymentId() }" />">
						<%
							i = i + 1;
						%>
					</c:forEach>
					
				</c:if>
			</table>
			
			<br>
			<br>
			<input type="hidden" id="paymentStatusString" name="paymentStatusString" path="paymentStatusString"/>
			<input type="hidden" id="paymentIDs" name="paymentIDs" path="paymentIDs" value=<%=paymentIDs %>/>
			
			<label>Select From Account </label> 
		<select name="fromAcct" size="1" id="fromAcct" path="fromAcct">
		<c:if test="${not empty acctlist}">
					<c:forEach var="acctitem" varStatus="status" items="${acctlist}">
						<option value="${acctitem.getAccNum()}"><c:out value="${acctitem.getAccNum()}" /></option>
					</c:forEach>
		</c:if>
		</select>
			
<label><input type="submit"
						onclick="return validateBillpay('<%=i%>');" name="billpay" id="billpay"
			value="submit"  /></label>
			
	
</form>
</body>
</html>
			

