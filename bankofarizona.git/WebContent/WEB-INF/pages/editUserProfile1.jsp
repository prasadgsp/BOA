<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<Title>Edit Profile</Title>
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
	<form id="edituserprofile1" method="POST" commandName="SignupModel"
		ModelAttribute="SignupModel">	
				<%@include file="/WEB-INF/pages/header2.jsp"%>
				
		<br /> <br>
		<label>User Id </label><input type="text"
			name="username" id="username" value="${userId}" path="username" readonly><br/><br/>
		<label>Email </label> <input type="text"
			name="email" id="email" value="${mail}" path="email" /><br />
		<br> <label>Phone Number</label> <input type="number"
			name="phonenumber" id="phonenumber" maxlength="10"
			value="${phoneNo}" path="phonenumber" /><br /><br/>
		<label>Address Line1</label> 
		<input type="text" name="addressline1" id="addressline1" path="addressline1" value="${addr1}"/><br /> <br>
		<label>Address Line2</label> 
		<input type="text" name="addressline2" id="addressline2" path="addressline2" value="${addr2}"/><br /> <br> 
		<label>City</label> <input type="text" name="city" id="city" path="city" value=${city }/><br /> <br>
		<label>State</label>
		<select name="state" size="1" id="state" path="state" selected=1 }>
			<option value=${state }>${state }</option>
			<option value="AL">Alabama</option>
			<option value="AK">Alaska</option>
			<option value="AZ">Arizona</option>
			<option value="AR">Arkansas</option>
			<option value="CA">California</option>
			<option value="CO">Colorado</option>
			<option value="CT">Connecticut</option>
			<option value="DE">Delaware</option>
			<option value="DC">Dist of Columbia</option>
			<option value="FL">Florida</option>
			<option value="GA">Georgia</option>
			<option value="HI">Hawaii</option>
			<option value="ID">Idaho</option>
			<option value="IL">Illinois</option>
			<option value="IN">Indiana</option>
			<option value="IA">Iowa</option>
			<option value="KS">Kansas</option>
			<option value="KY">Kentucky</option>
			<option value="LA">Louisiana</option>
			<option value="ME">Maine</option>
			<option value="MD">Maryland</option>
			<option value="MA">Massachusetts</option>
			<option value="MI">Michigan</option>
			<option value="MN">Minnesota</option>
			<option value="MS">Mississippi</option>
			<option value="MO">Missouri</option>
			<option value="MT">Montana</option>
			<option value="NE">Nebraska</option>
			<option value="NV">Nevada</option>
			<option value="NH">New Hampshire</option>
			<option value="NJ">New Jersey</option>
			<option value="NM">New Mexico</option>
			<option value="NY">New York</option>
			<option value="NC">North Carolina</option>
			<option value="ND">North Dakota</option>
			<option value="OH">Ohio</option>
			<option value="OK">Oklahoma</option>
			<option value="OR">Oregon</option>
			<option value="PA">Pennsylvania</option>
			<option value="RI">Rhode Island</option>
			<option value="SC">South Carolina</option>
			<option value="SD">South Dakota</option>
			<option value="TN">Tennessee</option>
			<option value="TX">Texas</option>
			<option value="UT">Utah</option>
			<option value="VT">Vermont</option>
			<option value="VA">Virginia</option>
			<option value="WA">Washington</option>
			<option value="WV">West Virginia</option>
			<option value="WI">Wisconsin</option>
			<option value="WY">Wyoming</option>
		</select> <br/>
		<br/>
		<label>Zip Code</label> <input type="number" name="zipcode" id="zipcode" path="zipcode" value=${zip }  /> <br />
		<br /> 
		<br> <label><input name="update"
			class="btn btn-lg btn-primary btn-block" type="submit" id="update"
			value="Update Profile" onclick="return validateuserprofileupdateForm();"></label><br />
		<div id="errors" style="color: #ff0000"></div>
	</form>
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>
