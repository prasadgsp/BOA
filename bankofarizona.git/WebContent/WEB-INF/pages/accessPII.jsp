<!DOCTYPE html>
<html>
<head>
<Title>Access PII</Title>
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
	<%@include file="/WEB-INF/pages/header1.jsp" %>
	<h2>${message}</h2>
	<form id="AccessPI-form" method="POST"
		commandName="AccessPIIModel"
		ModelAttribute="AccessPIIModel">
		<div id= "errors" style="color: #ff0000">
		</div>
		<label>Firstname </label> 
		<input type="text"
			name="firstname" id="firstname" path="firstname"><br /> <br>
		<label>Lastname</label> 
		<input type="text"
			name="lastname" id="lastname"><br /> <br> 
			<label>Requester Name</label> <input type="text" name="requesterName"
			id="requesterName"><br /> <br> <label>Requester Mail ID</label> <input
			type="text" name="requesterMailID" id="requesterMailID"><br /> <br> <br>
		<label><input type="submit"
			class="btn btn-lg btn-primary btn-block"
			onclick="return validateAccessFormForm();" name="Submit" id="Submit"
			value="Submit"></label><br />
	</form>
</div>

<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/validation.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>