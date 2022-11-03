<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<form method="post" action="accountCreated">
			<label for="create_username">Create Username:</label>
			<input name="create_username" type="text">
			<label for="create_password">Create Password:</label>
			<input name="create_password" type="password"/>
			<input type="submit" value="CreateAccount" />
		</form>
		<form>
    		<input type="button" value="Cancel" onclick="window.location='base.html'">
		</form>
	</body>
</html>