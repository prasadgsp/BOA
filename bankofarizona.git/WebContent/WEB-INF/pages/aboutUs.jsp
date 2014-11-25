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
	<h4>United Bank of Arizona (abbreviated as UBA) is an American multinational 
	banking and financial services corporation headquartered in Tempe, 
	Arizona. It is the second largest bank holding company in the United States by 
	assets. As of 2013, Bank of America is the twenty-first largest 
	company in the United States by total revenue. In 2010, Forbes listed United Bank of Arizona  
	as the third biggest company in the world.
	</div>
	
	<!--Scripts-->
	<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
	<script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>