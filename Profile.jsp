<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/style.css'/>">
<meta charset='ISO-8859-1'>

<title>Insert title here</title>
</head>
<body>
	<h2>Profile</h2>	
	<form method='post' action='Profile'>
		<p>Edit First Name: <input type='text' name='fname' value='${param.first}'/></p> 
		<p>Edit Last Name: <input type='text' name='lname' value='${param.last}'/></p> 
		<p>Edit Password: <input type='text' name='pass' value='${param.pass}'/></p> 
		<input type='submit' value='Save'/>
	</form>
</body>
</html>

