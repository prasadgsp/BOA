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
	<h4>Contact Us</h4>
	<h5>Email Address:</h5>
	<label>unitedbankofarizona@gmail.com</label> 
	<h5>Address:</h5>
	<label>699 S Mill Ave #101</label>
	<label>Tempe, AZ</label>
	<label>Phone:480 88 88888</label>
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>