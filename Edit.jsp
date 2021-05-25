<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Vaccine</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<body>
	<div class="container mt-3">
		<form  method="post">
			<div class="form-group">
				<input action="EditVaccine" type="hidden" name="id"
					value="${vac.id}"> 
					<label for="name" class="label font-weight-bold">Name: </label> 
					<input class="form-control w-75" type="text" name="name" value="${vac.name}"><br>
					
				<label class="label font-weight-bold"> Doses Required: </label> 
					<select class="form-control w-50" type="text" name="doses"> 
					<option>1</option>
					<option>2</option>
					</select>
					
				<label class="label font-weight-bold">
				Days B/W Doses: </label>
					<input class="form-control w-50" type="text" name="time"
						value="${vac.time}"><br>
	
	
				<button class="btn btn-primary btn-med">Save</button>
				
		</form>
</div>

</body>
</html>