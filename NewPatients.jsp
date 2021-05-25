<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Patients</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container mt-3">
		<form method="post">
			<div class="form-group">
				<label class="label font-weight-bold"> Patient First Name: </label>
				<input class="form-control w-75" type="text" name="fname"> <label
					class="label font-weight-bold"> Patient Last Name: </label> <input
					class="form-control w-75" type="text" name="lname"> <label
					for="vaccineType" class="label font-weight-bold">Vaccine: </label>
				<select class="form-control w-50"
					aria-label="multiple select example" name="vaccineType">
					<optgroup label="*Out of Stock*">
						<c:forEach items="${vac}" var="vac">
						<c:choose>
							<c:when test="${vac.remaining == 0}">
								<option disabled>${vac.name}(Out of Stock)</option>
							</c:when>
						</c:choose>
					</c:forEach>
					</optgroup>
					<c:forEach items="${vac}" var="vac">
						<c:choose>
							<c:when test="${vac.remaining > 1}">
								<option>${vac.name}</option>
							</c:when>
						</c:choose>
						<%-- <option class="form-control" value="${vac.name}">${vac.name}</option> --%>
					</c:forEach>
				</select><br>
				<button class="btn btn-primary btn-med">Add</button>
			</div>
		</form>
	</div>
</body>