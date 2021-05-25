<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employees Only</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<form>
		<div class="container mt-5 mx-auto">
			<div class="form-group">
				<a class="btn btn-primary" type="submit" name="action"
					value="Patient Management" href="PatientManagement">Patient
					Management</a>
						<a class="btn btn-dark btn-small" type="submit" name="action"
					value="Profile" href="Profile">Profile</a>
			</div>
				<a class="btn btn-secondary btn-small" type="submit" name="action"
					value="Logout" href="FrontPage">Logout</a>
		</div>
	</form>
</body>
</html>