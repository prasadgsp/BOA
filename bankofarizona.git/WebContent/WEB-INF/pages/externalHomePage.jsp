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
	<h2>${message}</h2>
<center>
<table style="width:50%">
<tr style="background-color: Bisque">
   <th>Account No</th>
   <th>Available Balance</th>
 </tr>  
 <c:if test="${not empty AcctModel}">
					<c:forEach var="acctitem" varStatus="status" items="${AcctModel}">
						<tr>
							<td><c:out value="${acctitem.getAccNum() }" /></td>
							<td><c:out value="${acctitem.getBalance() }" /></td>
						</tr>
					</c:forEach>
				</c:if>
</table>
</center>
</div>
<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>