<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
		<title>Create Quiz</title>
	</head>
	<h1 class="title">Create Quiz</h1>
	<body class="body">
	<div>
		<form method="post" action="quizzes">
			<input type="hidden" name="creationType" value="blanketQuiz">
			<input name='quizName' type='text' id='quizName' placeholder="Quiz Name">
		
			<label>Public <input type="checkbox" id='public' name="visibility" value="public"/></label>
			
			<input type='submit' name='isPublicChoice' value='Create'>
		</form>
	</div>
	
	<!-- Back button -->
	<form method="get" action="Menu.html">
		<button name="Back" style="background-color: #800003;" type="submit"
			value="Back">Cancel</button>
	</form>	
	</body>
</html>
