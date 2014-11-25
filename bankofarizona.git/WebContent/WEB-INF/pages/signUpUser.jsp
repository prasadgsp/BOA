<html>

<head>
<Title>Sign Up User</Title>
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
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
<script type="text/javascript" src="js/aes.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/jcap.js"></script>
<!--  <h1>Message : ${message}</h1> -->
<h1>Welcome to United Bank Of Arizona</h1>
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
	<form id="sign-up" method="POST"
		commandName="SignupModel" ModelAttribute="SignupModel">
		Please delete your browser history and cookies before running this.<br><br><br>
		<label>User Name </label> 
		<input type="text" name="username" id="username" path="username" maxlength="20" autocomplete="off"><br /> <br> 
		<label>Enter Password</label> 
		<input type="password" name="password" id="password" path="password" autocomplete="off"><br /><br> 
		<label>Reenter Password</label> 
		<input type="password" name="repassword" id="repassword" path="repassword" autocomplete="off"><br /> <br> 
		<label>First Name </label> 
		<input type="text" name="firstname" id="firstname" path="firstname"><br /><br>
		<label>Last Name</label> 
		<input type="text" name="lastname" id="lastname" path="lastname" /><br /> <br> 
		<label>SSN </label> <input type="number" name="ssn" id="ssn" path="ssn" maxlength="9" /><br /> <br>
		<longlabel>User Type
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:choose>
				<c:when test="${usertype == 'MANAGER'}">
					<select name="usertype" id="usertype" path="usertype"
						onchange="showEmployeeId()">
						<option value="SELECT">Select</option>
						<option value="CUSTOMER">Customer</option>
						<option value="MERCHANT">Merchant</option>
						<option value="MANAGER">Manager</option>
						<option value="EMPLOYEE">Employee</option>
					</select>
				</c:when>
				<c:otherwise>
					<select name="usertype" id="usertype" path="usertype"
						onchange="showEmployeeId()">
						<option value="SELECT">Select</option>
						<option value="CUSTOMER">Customer</option>
						<option value="MERCHANT">Merchant</option>
					</select>
				</c:otherwise>
			</c:choose>
		&nbsp;&nbsp;
		<p id="empId" style="visibility: hidden">
			Employee Id <input type="text" name="employeeid" id="employeeid" path="employeeid" /> <br />
		</p>
		</longlabel>
		<br /> <br> <br> <br> 
		<label>Date Of Birth</label> 
		<input type="text" name="dob" id="dob" path="dob" /><br /> <br> 
		<label>Address Line1</label> 
		<input type="text" name="addressline1" id="addressline1" path="addressline1" /><br /> <br>
		<label>Address Line2</label> 
		<input type="text" name="addressline2" id="addressline2" path="addressline2" /><br /> <br> 
		<label>City</label> <input type="text" name="city" id="city" path="city" /><br /> <br>
		<longlabel>State
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<select name="state" size="1" id="state" path="state">
			<option value="State">State</option>
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
		</select> &nbsp;&nbsp; 
		Zip Code <input type="number" name="zipcode" id="zipcode" path="zipcode" /> <br />
		</longlabel>
		<br /> <br> <label>Email </label> 
		<input type="text" name="email" id="email" path="email" /><br /> <br> 
		<label>Phone Number</label> 
		<input type="number" name="phonenumber" id="phonenumber" path="phonenumber" maxlength="10" /><br /> <br> 
		<label>Gender</label> 
		<input type="radio" name="gender" value="MALE" id="gender" path="gender">
		<glabel>Male</glabel>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" name="gender" id="gender" path="gender" value="FEMALE" checked="checked">
		<glabel>Female</glabel>
		<br /> <br> <br> <br> 
		<label>Security Question 1</label>
		<select id="secQuestion_1" name="secQuestion_1" path="secQuestion_1">
		<option value="Security Questions">Security Questions</option>
			<option value="What is your Mother's maiden name?">What is your Mother's maiden name?</option>
			<option value="What is the name of your first crush?">What is the name of your first crush?</option>
			<option value="In which city you met your spouse the first time?">In which city you met your spouse the first time?</option>            
		</select>          
		<br> <label>Answer </label>
		<input type="text" id="secanswer_1" name="secanswer_1" path="secanswer_1"><br> <br>
		<label>Security Question 2 </label>
		<select id="secQuestion_2" name="secQuestion_2" path="secQuestion_2">
		<option value="Security Questions">Security Questions</option>
			<option value="What is your pet name?">What is your pet name?</option>
			<option value="What is your first Car?">What is your first Car?</option>
			<option value="Who is your childhood hero?">Who is your childhood hero?</option>            
		</select>  
		<br>  <label>Answer </label><input type="text" id="secanswer_2" name="secanswer_2" path="secanswer_2"><br>

		<br> <br> <label>Security Question 3 </label>
		<select id="secQuestion_3" name="secQuestion_3" path="secQuestion_3">
		<option value="Security Questions">Security Questions</option>
			<option value="What is your favorite color?">What is your favorite color?</option>
			<option value="Name the first thing you want to do before you die?">Name the first thing you want to do before you die?</option>
			<option value="What is your favorite film?">What is your favorite film?</option>            
		</select>  <br/>
		<label>Answer</label><input type="text" id="secanswer_3" name="secanswer_3" path="secanswer_3"><br> <br> <br>
		<p id="accountData" style="visibility: hidden"> 
		<label>Initial Deposit Amount </label> 
		<input type="number" name="accBalance" id="accBalance" path="accBalance" /><br /> <br>
		</p>
		
		<label>Enter the Code</label> 
		<label><script type="text/javascript">
			sjcap();
		</script></label> 
		<br> <br> <br> <br> <br>

		<input type="hidden" id="salt" value="${salt }">

		<longlabel> <input type="checkbox" name="checkbox" id="checkbox"> 
		I have read and understood the terms and conditions and accept the same </longlabel>
		<br> <br> <label><input name="signup"
			class="btn btn-lg btn-primary btn-block" type="submit" id="signup"
			value="Sign Up" onclick="return validateSignUpForm();"></label><br />
	</form>
	<div id="errors" style="color: #ff0000"></div>
	</div>
	

</body>
</html>
