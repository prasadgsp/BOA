<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<Title>United Bank of Arizona</Title>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />

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
	<c:choose>
			<c:when
				test="${usertype == 'MANAGER' || usertype == 'EMPLOYEE'}">
				<%@include file="/WEB-INF/pages/header2.jsp"%>
			</c:when>
			<c:when
				test="${usertype == 'CUSTOMER' || usertype == 'MERCHANT'}">
				<%@include file="/WEB-INF/pages/header3.jsp"%>
			</c:when>
			<c:otherwise>
        		<%@include file="/WEB-INF/pages/header1.jsp"%>
    		</c:otherwise>
		</c:choose>
	<h4>How do I access my account?</h4>
	<label>Log in using your username and password. In the homepage, the various accounts held 
	by you along with their details are listed. For fruther details and statements, click on the 
	Statements Tab and Select the account for which you wish to see the Statements </label>
	<br/>
	<h4>How do I enable javascript in my browser?</h4>
	<label><b>For Google Chrome</b></label>
	<br>Click the Chrome menu icon Chrome menu on the browser toolbar.</br>
    <br>Select Settings.</br>
    <br>On the Settings page, select Show advanced settings</br>
    <br>In the Privacy section, click Content settings</br>
    <br>Select Allow all sites to run JavaScript (recommended) in the JavaScript section.</br>
	<br/>
    <label><b>For Internet Explorer</b></label>
    <br>On the Tools menu, click Internet Options, and then click the Security tab.</br>
    <br>Select Internet zone.</br>
    <br> In the Security Settings,Internet Zone dialog box, click Enable for Active Scripting in the Scripting section<br/>
    <br>Click the Back button to return to the previous page
    and then click the Refresh button to run scripts.</br>
    <br/><br/>
	<h4>What are your hours?</h4>
	<label>Monday-Friday 9 AM to 6PM Local Time<br/>
			Saturday 10 AM to 4 PM Local Time<br/>
			Sunday Holiday
		</label>
	<br/>
	<br/>
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>