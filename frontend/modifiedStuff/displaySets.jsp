<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<title>Sets</title>
	</head>
	<body class="body">
		<div>
		<form method="get" action="flashcards">
			<c:forEach items="${setNames}" var="item">
				<input type="submit" value="<c:out value = "${item}"/>"><br>	
			</c:forEach>
		</form>
		</div>

	</body>
</html>