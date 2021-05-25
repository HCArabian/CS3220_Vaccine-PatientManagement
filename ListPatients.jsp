<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Patients</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>
<div class="container">
	<nav class="navbar sticky-top navbar-expand-lg flex-nowrap navbar-light bg-light">
		 <div class="navbar-collapse collapse order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-item nav-link active text-dark" href="NewPatients">New Patients</a>
            </li>
        </ul>
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
		<div class="row">
			<table class="table table-bordered-2">
				<tr class="font-weight-bold">
					<td>ID</td>
					<td>Name</td>
					<td>Vaccine</td>
					<td>First Dose</td>
					<td>Second Dose</td>
					<td></td>
				</tr>

				<c:forEach items="${patList}" var="patList">
					<tr>
						<td>${patList.id}</td>
						<td>${patList.name}</td>
						<td>${patList.vaccineName}</td>
						<td>${patList.firstDoseDate}</td>
						<td>
							<c:choose>
								<c:when test="${patList.secondDoseDate != null}">
      							 ${patList.secondDoseDate}
								</c:when>
								<c:when test="${patList.vaccineDosesRequired == 1}">
								<p>---<p>
								</c:when>
								<c:when test="${patList.vaccineDosesLeft == 0}">
								<p>Out of Stock<p>
								</c:when>
								<c:otherwise>
								<a href="SecondDose?id=${patList.id}">Received.</a>
								</c:otherwise>
							</c:choose>
						</td>

						<%-- <td>${patients.secondDose}</td>
						<td><a href="SecondDose?id=${patients.id}">Received.</a> --%>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>