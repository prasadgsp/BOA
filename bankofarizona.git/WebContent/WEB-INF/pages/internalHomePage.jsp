<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<Title>United Bank Of Arizona</Title>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>

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

<body onbeforeunload="/j_spring_security_logout">
<noscript>
	<h3>
	Please enable JavaScript in your web browser!</h3>
 
	<style type="text/css">
		#main-content { display:none; }
	</style>
</noscript>

<%
int i = 1;
String transIDs = "";
%>
	<div id="main-content">
		<%@include file="/WEB-INF/pages/header2.jsp"%>
		<h2>${message}</h2>
		<form id="trans-status" method="POST"
		commandName="TransactionsModel" ModelAttribute="TransactionsModel">
		<center>
				<table style="width: 50%">
				<tr style="background-color: Bisque">
					<th>From Account</th>
					<th>To Account</th>
					<th>Amount</th>
					<th>Authorize/Decline</th>
				</tr>
				<c:if test="${not empty pendingTransactions}">
					<c:forEach var="transaction" varStatus="status"
						items="${pendingTransactions}">
						<%
							String name = "authorize/decline" + i;
							String id1 = "authorize" + i;
							String id2 = "decline" + i;
							String id3 = "transID" + i;
						%>
						<tr>
							<%-- <td style="display:none;"><c:out value="${transaction.getFromAcct() }"/></td> --%>
							<td><c:out value="${transaction.getFromAcct() }" /></td>
							<td><c:out value="${transaction.getToAcct() }" /></td>
							<td><c:out value="${transaction.getAmount() }" /></td>
							<td>
							<input type="radio" name=<%=name%> id=<%=id1%>> 
							Authorize &nbsp;&nbsp;
							<input type="radio"
								name=<%=name%> id=<%=id2%>>Decline</td>
						</tr>
						<input type="hidden" id=<%=id3 %> value="<c:out value="${transaction.getTransId() }" />">
						<%
							i = i + 1;
						%>
					</c:forEach>
				</c:if>
			</table>

			<br>
			<br>
			<input type="hidden" id="transStatusString" name="transStatusString" path="transStatusString"/>
			<input type="hidden" id="transIDs" name="transIDs" path="transIDs" value=<%=transIDs %>/>
			<glabel>
			<input type="submit" name="submit" id="submit" value="Submit" onclick="return validateTransStatusForm('<%=i%>');"></glabel>
			<br />
		</center>
		</form>
	</div>
</body>
</html>