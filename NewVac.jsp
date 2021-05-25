<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Vaccine</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
	<div class="container mt-3">
		<form method="post">
			<div class="form-group">
				<label class="label font-weight-bold"> Name: </label> <input
					class="form-control w-75" type="text" name="name"> 
					
					<label class="label font-weight-bold"> Doses Required: </label> 
					<select class="form-control w-50" type="text" name="doses"> 
					<option>1</option>
					<option>2</option>
					</select>
					
					<label class="label font-weight-bold"> Days Between Doses: </label>
					<input class="form-control w-50" type="text" name="time"><br>
				<button class="btn btn-primary btn-med">Add</button>
			</div>
		</form>
	</div>
</body>
</html>