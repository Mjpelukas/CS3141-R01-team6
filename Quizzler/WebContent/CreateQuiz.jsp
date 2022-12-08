<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
		<title>Insert title here</title>
	</head>
	<h1 class="title">Create Quiz</h1>
	<body class="body">
	<div>
		<form method="post" action="quizzes">
		<%
		//PrintWriter out = new PrintWriter();
		%>
		<input type="hidden" name="creationType" value="blanketQuiz">
		<input name='quizName' type='text' id='quizName' placeholder="Quiz Name">
		
		<input name='courseText' type='text' id='courseText' placeholder="Class(Optional)">
		 
		<!-- out.println("Please choose the visibility for this quiz: "); -->
		<!-- Please choose the visibility for this quiz: 
		<input type='radio' id='private' name='visibility' value='private'>
		<label for='private'>Private</label><br>-->
		<input type='checkbox' id='public' name='visibility' value='public'>
		<p>Public</p>
		
		<input type='submit' name='isPublicChoice' value='Create'>
		
		
		
		<%
		// Set up the MySQL statement to create the overall quiz.
		
		// Prompt the user to add each of the quiz questions.
		/*
		boolean isInProgress = true;
		while(isInProgress) {
			out.println("Do you wish to add another quiz question?");
			
			out.println("<input type='radio' id='yes' name='yes' value='yes'>");
			out.println("<label for='yes'>Yes</label><br>");
			
			out.println("<input type='radio' id='no' name='no' value='no'>");
			out.println("<label for='no'>No</label><br>");
			
			out.println("<input type='submit' name='isPublicChoice' value='isPublicChoice'>");
			//TODO: CONVERT THIS
			if (isset($_POST['yes'])) {
				createQuizQuestion($username, $quizName);
			} else {
				$isInProgress = false;
			}
		}
		*/
		
		%>
		</form>
		</div>
	</body>
</html>
