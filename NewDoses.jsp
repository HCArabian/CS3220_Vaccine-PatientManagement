<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Doses</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form method="post">
			<div class="form-group">
				<label for="name" class="label font-weight-bold">Vaccine: </label> 
				<select class="form-control w-50" aria-label="multiple select example" name="name">
					<c:forEach items="${vac}" var="vac">
						<option class="form-control" value="${vac.name}">${vac.name}</option>
					</c:forEach>
				</select> <br>
				<label class="label font-weight-bold">New Doses Received: </label> 
					<input class="form-control w-25" type="text" name="AddDose"><br>
				<button class="btn-primary btn-small">Add</button>
			</div>
		</form>
	</div>


</body>
</html>