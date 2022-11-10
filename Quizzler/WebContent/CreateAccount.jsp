<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<title>Insert title here</title>
	</head>
	<h1 class="title">Quizzler</h1>
	<h2 class="subheader">Login Page</h2>
	<body class="body">
	<div>
		<form method="post" action="accountCreated">
			<!-- Text Inputs -->
			<input name="create_username" type="text" placeholder="Username">
			<input name="create_password" type="password" placeholder="Password"/>
			<input name="confirm_password" type="password" placeholder="Confirm Password"/>
			
			<!-- this retrieves any error message that the create account servlet sets as an attribute -->
			<p>${error}</p>		
			
			<!-- Buttons -->
			<input type="submit" value="Create Account" />
			<input type="button" value="Cancel" onclick="window.location='base.jsp'">
		</form>
		</div>
	</body>
</html>
