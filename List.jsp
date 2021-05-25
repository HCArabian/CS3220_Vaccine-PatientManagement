<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Vaccines</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
<div class="container">
	<nav class="navbar sticky-top navbar-expand-lg flex-nowrap navbar-light bg-light">
		<div class="collapse navbar-collapse">
			<a class="nav-item nav-link active text-dark" href="NewVac">New
				Vaccine</a> <a class="nav-item nav-link color-info" href="NewDoses">New
				Doses</a>
		</div>
		<div class="navbar-collapse collapse dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-item nav-link active text-dark float-right" href="FrontPage">Logout</a>
            </li>
        </ul>
    </div>
	</nav>
	</div>

	<div class="container">
		<div class="table">
			<table class="table table-bordered-2">
				<tr class="font-weight-bold">
					<td>Vaccine</td>
					<td>Doses Required</td>
					<td>Days b/w Doses</td>
					<td>Doses Received</td>
					<td>Doses Remaining</td>
					<td></td>
				</tr>

				<c:forEach items="${vac}" var="vac">
					<tbody>
						<tr>
							<td>${vac.name}</td>
							<td>${vac.doses}</td>
							<td>${vac.time}</td>
							<td>${vac.vaccinated}</td>
							<td>${vac.remaining}</td>
							<td><a href="EditVaccine?id=${vac.id}">Edit</a>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>