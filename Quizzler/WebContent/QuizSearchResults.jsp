<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
<title>Quiz Search Results</title>
</head>
<h1 class="title">Quizzler</h1>
<h2 class="subheader">Quiz Search Results</h2>
<body>
	<c:forEach items="${quizNames}" var="item">
		<!-- the name for each button is quiz_name so that when it's submitted, the servlet
			 can access the parameter by the identity "quiz_name" 
 			The value which is shown to the user is set to item, which is the quiz name 
 			taken from the arraylist -->
		<form method="get" action="searchedQuizView">
			<input type="submit" value="<c:out value = "${item[0]}"/>" name="quiz_name">
		</form>	
	</c:forEach>
</body>
</html>