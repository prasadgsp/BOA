
function validateAccessFormForm() {
	if (document.getElementById('firstname').value == ""
			|| document.getElementById('firstname').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the firstname.";
		return false;
	}else if (document.getElementById('lastname').value == ""
			|| document.getElementById('lastname').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the lastname.";
		return false;
	}else if (document.getElementById('requesterMailID').value == ""
			|| document.getElementById('requesterMailID').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the requesterMailID.";
		return false;
	}else if (document.getElementById('requesterName').value == ""
			|| document.getElementById('requesterName').value == null) {
		document.getElementById('errors').innerHTML = "Please Enter the requesterName.";
		return false;
	} else if(!validateEmail(document.getElementById('requesterMailID'))) {
		return false;
	}

	document.getElementById('AccessPI-form').action = "accessPIISuccess.html";
	return true;
}