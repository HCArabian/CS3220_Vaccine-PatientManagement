<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Vaccine Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container m-5 text-center">
		<div class="form-group">
			<form action="FrontPage" method="Post">
				<label class="label font-weight-bold">Username: </label> <input type="text" name="user"> <br>
				<input type="hidden" id="custId" name="custId" value="3487"/>
				<label class="label font-weight-bold">Password: </label> <input type="password" name="pass"> <br>
				<input class="btn btn-secondary btn-small" role="button" type="submit" value="Login"/>
			</form>
		</div>
	</div>
</body>
</html>