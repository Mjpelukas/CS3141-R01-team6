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
 		<!-- sends name owner and id -->
		<form method="get" action="searchedQuizView">
		
			<label><input type="submit" value="<c:out value = "${item[0]}"/>" name="quiz_name">
			<input type="hidden" name="quiz_owner" value="${item[1]}">
			<input type="hidden" value="${item[q]}"> by 
		</form>	
	</c:forEach>
	
	<!-- Back button -->
	<form method="get" action="Menu.html">
		<button name="Back" style="background-color: #800003;" type="submit"
			value="Back">Back</button>
	</form>	
</body>
</html>