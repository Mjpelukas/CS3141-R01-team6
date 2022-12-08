<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<<<<<<< HEAD
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
<title>Quizzes</title>
</head>
<h1 class="title">Quizzler</h1>
<h2 class="subheader">Quizzes</h2>
<body class="body">
		
			<input class="button" style="margin-bottom: 30px;" type="button" value="Create Quiz"
				onclick="window.location='CreateQuiz.jsp'">
			
			<!-- setNames is a java arraylist, this takes it from context attributes
			     and applies a for each loop on it, creating buttons for each. -->
			<c:forEach items="${quizNames}" var="item">
				
				<!-- the name for each button is set_name so that when it's submitted, the servlet
				     can access the parameter by the identity "set_name" 
 				     The value which is shown to the user is set to item, which is the set name taken from the arraylist -->
				<form method="get" action="quizQuestions">
				<input type="submit" value="<c:out value = "${item}"/>" name="quiz_name">
				
				<button 
					style="background-color: #770000; width: 30px; height: 30px; padding: 0;" 
					type="submit" value="<c:out value = "${item}"/>" name="delete">x</button><br>
				</form>
				</form>
			</c:forEach>
		
		<form>
			<input style="background-color: #800003; margin-top: 40px;"
				type="button" value="Back" onclick="window.location='Menu.html'">
		</form>
</body>
</html>
=======
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
		<title>Insert title here</title>
	</head>
	<h1 class="title">Quizzler</h1>
	<h2 class="subheader">Quizzes</h2>
	<body class="body">
		
		<input class="button" style="margin-bottom: 30px;" type="button" value="Create Quiz"
			onclick="window.location='CreateQuiz.jsp'">
		
		<!-- setNames is a java arraylist, this takes it from context attributes
		     and applies a for each loop on it, creating buttons for each. -->
		<c:forEach items="${quizNames}" var="item">
			
			<!-- the name for each button is set_name so that when it's submitted, the servlet
			     can access the parameter by the identity "set_name" 
 			     The value which is shown to the user is set to item, which is the set name taken from the arraylist -->
			<form method="get" action="quizQuestions">
				<input type="submit" value="<c:out value = "${item}"/>" name="quiz_name">
				
				<button 
					style="background-color: #770000; width: 30px; height: 30px; padding: 0;" 
					type="submit" value="<c:out value = "${item}"/>" name="delete">x</button><br>
			</form>
		</c:forEach>
		
		<form>
			<input style="background-color: #800003; margin-top: 40px;"
				type="button" value="Back" onclick="window.location='Menu.html'">
		</form>
	</body>
</html>
>>>>>>> refs/remotes/origin/mrpenoyar
