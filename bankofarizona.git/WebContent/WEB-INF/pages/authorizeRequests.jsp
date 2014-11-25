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
<%
int i = 1;
String requestIDs = "";
%>
	<div id="main-content">
		<%@include file="/WEB-INF/pages/header2.jsp"%>
		<h2>${message}</h2>
		<form id="request-status" method="POST"
		commandName="AuthorizePIIPendingRequestModel" ModelAttribute="AuthorizePIIPendingRequestModel">
		<center>
				<table style="width: 50%">
				<tr style="background-color: Bisque">
					<th>User Name </th>
			        <th>Requester</th>
					<th>Requester email</th>
					<th>Authorize/Decline</th>
				</tr>
				<c:if test="${not empty piipendingRequests}">
					<c:forEach var="piirequest" varStatus="status"
						items="${piipendingRequests}">
						<%
							String name = "authorize/decline" + i;
							String id1 = "authorize" + i;
							String id2 = "decline" + i;
							String id3 = "requestIDs" + i;
						%>
						<tr>
							<td><c:out value="${piirequest.getUserId() }" /></td>
							<td><c:out value="${piirequest.getRequester() }" /></td>
							<td><c:out value="${piirequest.getRequestermail() }" /></td>
							<td>
							<input type="radio" name=<%=name%> id=<%=id1%>> 
							Authorize &nbsp;&nbsp;
							<input type="radio"
								name=<%=name%> id=<%=id2%>>Decline</td>
						</tr>
						<input type="hidden" id=<%=id3 %> value="<c:out value="${piirequest.getReqNum() }" />">
						<%
							i = i + 1;
						%>
					</c:forEach>
				</c:if>
			</table>

			<br>
			<br>
			<input type="hidden" id="requestStatusString" name="requestStatusString" path="requestStatusString"/>
			<input type="hidden" id="requestIDs" name="requestIDs" path="requestIDs"/>
			<glabel>
			<input type="submit" name="submit" id="submit" value="Submit" onclick="return validatePendingRequestStatusForm('<%=i%>');"></glabel>
			<br />
		</center>
		
	</form>
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>