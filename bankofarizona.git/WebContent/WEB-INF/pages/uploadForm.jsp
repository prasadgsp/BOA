<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
	<link rel="stylesheet" type="text/css" href="css/keyboard.css" />
<title>Transfer - UBA</title>
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

<!-- Main jumbotron for a primary marketing message or call to action  -->

	<form class="form-signin" method="POST"
						action="transferUpload.html" 
						 enctype="multipart/form-data">
						<h3>Upload Files</h3>
						
						<label>
							Upload Certificate <br>
							<input  name="certifcateFile" type="file" class="form-control" />
						</label>
						<br><br>
						<label>
							Secret Key <br>
							<input name="secretKeyfile" type="file" class="form-control" />
						</label>
						<br><br>
						<label>
							<button class="btn btn-lg btn-primary btn-block" type="submit" name="upload" value="upload">Upload Certificate</button>
						</label> 
						<label> 
							<button class="btn btn-lg btn-primary btn-block" type="submit" name="cancel">Cancel</button>
						</label> 
				</form>
				
			</div>
			
			<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
<script type="text/javascript" src="js/keyboard.js" charset="UTF-8"></script>
</body>
</html>
