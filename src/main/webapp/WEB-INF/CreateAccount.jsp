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
	<h2 class="subheader">Create Account</h2>
	<body class="body">
	<div>
		<form method="post" action="accountCreated">
			<!-- <label for="create_username">Create Username:</label> -->
			<input name="create_username" type="text" placeholder="Username">
			<!-- <label for="create_password">Create Password:</label> -->
			<input name="create_password" type="password" placeholder="Password"/>
			
			<input name="confirm_password" type="password" placeholder="Confirm Password"/>
			<p>${error}</p>			
			<input type="submit" value="Create Account" />
			<input type="button" style="background-color:#800003;" value="Cancel" onclick="window.location='base.jsp'">
		</form>
		<!-- <form>
    		<input type="button" value="Cancel" onclick="window.location='base.html'">
		</form>-->
		</div>
	</body>
</html>