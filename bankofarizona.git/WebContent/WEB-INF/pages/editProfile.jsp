<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>United Bank of Arizona</title>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />

<style>
label {
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
	<form id="editprofile" method="POST" commandName="SignupModel"
		ModelAttribute="SignupModel" onload="setstate();">
		<c:choose>
			<c:when
				test="${usertype == 'MANAGER' || usertype == 'EMPLOYEE'}">
				<%@include file="/WEB-INF/pages/header2.jsp"%>
			</c:when>
			<c:otherwise>
        		<%@include file="/WEB-INF/pages/header3.jsp"%>
    		</c:otherwise>
		</c:choose>
			<br /> <br> <label>Email </label> <input type="text"
			name="email" id="email" value="${mail}" path="email" /> <br />
		<br> <label>Phone Number</label> <input type="number"
			name="phonenumber" id="phonenumber" maxlength="10"
			value="${phoneNo}" path="phonenumber" /><br />
		<label>Address Line1</label> 
		<input type="text" name="addressline1" id="addressline1" path="addressline1" value="${addr1}"/><br /> <br>
		<label>Address Line2</label> 
		<input type="text" name="addressline2" id="addressline2" path="addressline2" value="${addr2}"/><br /> <br> 
		<label>City</label> <input type="text" name="city" id="city" path="city" value=${city }/><br /> <br>
			 
		<label>State</label> <input type="text" name="state" id="state" path="state" value=${state }/><br /> <br>
		<br /> 
		<label>Zip Code</label> <input type="text" name="zipcode" id="zipcode" path="zipcode" value=${zip }><br /> <br>
		<br> <br> <label><input name="update"
			class="btn btn-lg btn-primary btn-block" type="submit" id="update"
			value="update" onclick="return validateupdateForm();"></label><br />
		<div id="errors" style="color: #ff0000"></div>
	</form>
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>
