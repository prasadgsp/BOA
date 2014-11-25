<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
window.history.forward();
</script>	
	<div class="row-fluid">
		<div class="navbar navbar-inverse">
			<div class="navbar-inner">
				<div class="container-fluid">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="active"><a href="internalHomePage.html">My Home</a></li>
							<li><a href="authorizeRequests.html">Authorize PII Requests</a></li>
							<li><a href="signUpUser.html">Sign Up</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Transactions<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="viewTrans.html">View Transactions</a></li>
									<li><a href="createTrans.html">Create Transaction</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Profile<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="viewProfile.html">View Profile</a></li>
									<li><a href="editProfile.html">Update Profile</a></li>
									<li><a href="resetPassword.html">Reset Password</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">External User Profiles<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="viewUserProfile.html">View User Profiles</a></li>
									<li><a href="editUserProfile.html">Edit User Profiles</a></li>
									<li><a href="delUserProfile.html">Delete User Profiles</a></li>
								</ul></li>	
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">User Accounts<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="viewAccount.html">View Account</a></li>
									<li><a href="addAccount.html">Add Account</a></li>
									<li><a href="deleteAccount.html">Delete Account</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">About Us<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="aboutUs.html">About Us</a></li>
									<li><a href="contactUs.html">Contact Us</a></li>
									<li><a href="FAQ.html">FAQ</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Logs<b class="caret"></b></a>
								<ul class="dropdown-menu">
								<li><a href="showlog">System Logs</a></li>
								<li><a href="showAuditlog">Audit</a></li>
							</ul></li>
							
							<li><a href="<c:url value="/j_spring_security_logout" />">Log out</a></li>
							
							
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>