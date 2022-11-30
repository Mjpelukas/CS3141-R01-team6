<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
<title>Set Search Results</title>
</head>
<body>
	<c:forEach items="${setNames}" var="item">
				
				<!-- the name for each button is set_name so that when it's submitted, the servlet
				     can access the parameter by the identity "set_name" 
 				     The value which is shown to the user is set to item, which is the set name taken from the arraylist -->
				<form method="get" action="flashcards">
				<input type="submit" value="<c:out value = "${item}"/>" name="set_name">
				
</body>
</html>