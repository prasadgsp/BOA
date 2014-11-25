<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
<Title>United Bank of Arizona</Title>
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

<table class="table table-striped table-bordered">
				<tr>
					<th>Transaction ID</th>
					<th>Amount</th>
					<th>From Account</th>
					<th>To Account</th>
					<th>Transaction Status</th>
					<th>Transaction Type</th>
					<th>Authorized By</th>
				</tr>	
				<c:if test="${not empty TransModel}">
					<c:forEach var="transitem" varStatus="status" items="${TransModel}">
						<tr>
							<td><c:out value="${transitem.getTransId() }" /></td>
							<td><c:out value="${transitem.getAmount() }" /></td>
							<td><c:out value="${transitem.getFromAcct() }" /></td>
							<td><c:out value="${transitem.getToAcct() }" /></td>
							<td><c:out value="${transitem.getTransStatus() }" /></td>
							<td><c:out value="${transitem.getTransType() }" /></td>
							<td><c:out value="${transitem.getAuthUser() }" /></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
</div>

<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>
