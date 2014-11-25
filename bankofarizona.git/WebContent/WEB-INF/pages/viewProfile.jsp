<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<Title>View Profile</Title>
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
	<form class="sign-up" method="POST" commandName="SignupModel"
		ModelAttribute="SignupModel">
		<c:choose>
			<c:when
				test="${info.getUserType() == 'MANAGER' || info.getUserType() == 'EMPLOYEE'}">
				<%@include file="/WEB-INF/pages/header2.jsp"%>
			</c:when>
			<c:otherwise>
        		<%@include file="/WEB-INF/pages/header3.jsp"%>
    		</c:otherwise>
		</c:choose>

		<br> <label>First Name </label> <input type="text"
			name="firstname" id="firstname" readonly="readonly"
			value=${info.getFirstName()} ><br /> <br> <label>Last
			Name</label> <input type="text" name="lastname" id="lastname"
			value=${info.getLastName() } readonly="readonly" /><br /> <br>
		<label>User Type</label> <input type="text" name="usertype"
			id="usertype" value=${info.getUserType() } readonly="readonly"><br />
		<br /> <br> <label>Date Of Birth</label> <input
			type="text" name="dob" id="dob" value=${info.getDob()} readonly="readonly" /><br /> <br> <label>Address</label>
		<textarea rows="4" cols="50" readonly="readonly">
		${info.getAddress().replace('|', ',') }
		</textarea>
		<br /> <br> <label>Email </label> <input type="text"
			name="email" id="email" value=${info.geteMail() } readonly="readonly" /> <br />
		<br> <label>Phone Number</label> <input type="number"
			name="phonenumber" id="phonenumber" maxlength="10"
			value=${info.getPhoneNo() } readonly="readonly" /><br />
		<br> <label>Gender</label> <input type="text" name="Gender"
			id="Gender" value=${info.getGender() } readonly="readonly" /><br />
	</form>
	
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>
