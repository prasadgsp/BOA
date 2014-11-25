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
<form id="view-trans-form" method="GET"
		commandName="CreateTransModel"
		ModelAttribute="CreateTransModel" action="viewTransSuccess.html">	
	<label><input type="submit"
			class="btn btn-primary "
			 name="viewTrans" id="viewTrans"
			value="View Transactions"></label><br />
			
	
	
</form>
</div>
<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>
