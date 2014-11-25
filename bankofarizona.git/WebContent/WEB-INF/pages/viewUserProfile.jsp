<!DOCTYPE html>
<html>

<head>
<Title>United Bank Of Arizona</Title>
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    
}
label{
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
<%@include file="/WEB-INF/pages/header2.jsp" %>
	<h2>${message}</h2>
	<form id="view-user-profile-form" method="POST" action = "viewUserProfile1.html"
		commandName="ViewAccountModel" ModelAttribute="ViewAccountModel">
		<div id="errors" style="color: #ff0000"></div>
		<label>Enter the user Id</label> <input type="text" name="userId" id="userId"" path="userId"><br /> <br>
		<br>
		<label><input type="submit"
			class="btn btn-lg btn-primary btn-block"
		   name="submit"
			id="submit" value="Submit" onsubmit="submit"></label><br />
		<br />
	</form>
<table style="width:50%">
<tr style="background-color: Bisque">
   <th>User Id</th>
   <th>First Name</th>
   <th>Last Name</th>
   <th>User Type</th>
    <th>DOB</th>
    <th>Email</th>
    <th>Phone No</th>
    <th>Gender</th>
    <th>User Status</th>
 </tr>  
						<tr>
							<td><c:out value="${UserList1.getUserId() }" /></td>
							<td><c:out value="${UserList1.getFirstName() }" /></td>
							<td><c:out value="${UserList1.getLastName() }" /></td>
							<td><c:out value="${UserList1.getUserType() }" /></td>
							<td><c:out value="${UserList1.getDob() }" /></td>
							<td><c:out value="${UserList1.geteMail() }" /></td>
							<td><c:out value="${UserList1.getPhoneNo() }" /></td>
							<td><c:out value="${UserList1.getGender() }" /></td>
							<td><c:out value="${UserList1.getUserStatus() }" /></td>
						</tr>
</table>	
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	---OR---
	<br/>
	<br/>
	<form id="view-user-profile-form" method="GET" action = "viewUserProfile2.html"
		commandName="ViewAccountModel" ModelAttribute="ViewAccountModel">
		<div id="errors" style="color: #ff0000"></div>
		<label><input type="submit"
			class="btn btn-lg btn-primary"
		   name="submit"
			id="submit" value="View All User Profiles" onsubmit="submit"></label><br />
		<br />
	</form>
	

<table style="width:50%">
<tr style="background-color: Bisque">
   <th>User Id</th>
   <th>First Name</th>
   <th>Last Name</th>
   <th>User Type</th>
    <th>DOB</th>
    <th>Email</th>
    <th>Phone No</th>
    <th>Gender</th>
    <th>User Status</th>
 </tr>  
 <c:if test="${not empty UserList}">
					<c:forEach var="useritem" varStatus="status" items="${UserList}">
						<tr>
							<td><c:out value="${useritem.getUserId() }" /></td>
							<td><c:out value="${useritem.getFirstName() }" /></td>
							<td><c:out value="${useritem.getLastName() }" /></td>
							<td><c:out value="${useritem.getUserType() }" /></td>
							<td><c:out value="${useritem.getDob() }" /></td>
							<td><c:out value="${useritem.geteMail() }" /></td>
							<td><c:out value="${useritem.getPhoneNo() }" /></td>
							<td><c:out value="${useritem.getGender() }" /></td>
							<td><c:out value="${useritem.getUserStatus() }" /></td>
						</tr>
					</c:forEach>
				</c:if>
</table>
	
	
	</div>
	
	<!--Scripts-->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>