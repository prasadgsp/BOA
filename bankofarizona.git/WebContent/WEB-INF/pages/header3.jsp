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
							<li class="active"><a href="externalHomePage.html">My Accounts</a></li>
							<li><a href="viewStmts.html">Statements</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Transfers<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="transfer.html">Transfer</a></li>
									<li><a href="debitcreditotp.html">Debit/Credit</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Payments<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="Billpay.html">Pay Bills</a></li>
									<li><a href="InitiatePayment.html">Initiate Payment</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Profile<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="viewProfile.html">View Profile</a></li>
									<li><a href="editProfile.html">Update Profile</a></li>
									<li><a href="resetPassword.html">Reset Password</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Authorizations<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="addAuthorization.html">Add Authorized User</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">About Us<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="aboutUs.html">About Us</a></li>
									<li><a href="contactUs.html">Contact Us</a></li>
									<li><a href="FAQ.html">FAQ</a></li>
								</ul></li>
							<li><a href="<c:url value="/j_spring_security_logout" />">Log out</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>