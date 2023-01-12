<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>student form</title>
</head>
<body>
	<form action="/SpringMVC_Demo2/root/submitForm" method="POST">
		<p>
			Student name: <input type="text" name="stuName">
		</p>
		<p>
			Subject: <input type="text" name="subject">
		</p>
		<input type="submit" value="Submit form">
	</form>
</body>
</html>