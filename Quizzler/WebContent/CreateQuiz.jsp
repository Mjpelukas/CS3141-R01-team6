<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="">
		<%
		//PrintWriter out = new PrintWriter();
		%>
		
		<input name='quizNameText' type='text' id='quizNameText' value='quizNameText'>
		<label for='quizNameText'>Enter Quiz Name: </label><br>
		
		<input name='courseText' type='text' id='courseText' value='courseText'>
		<label for='courseText'>Enter Relevant Course: </label><br>
		
		<!-- out.println("Please choose the visibility for this quiz: "); -->
		Please choose the visibility for this quiz:
		<input type='radio' id='private' name='visibility' value='private'>
		<label for='private'>Private</label><br>
		<input type='radio' id='public' name='visibility' value='public'>
		<label for='public'>Public</label><br>
		<input type='submit' name='isPublicChoice' value='isPublicChoice'>
		
		
		
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
	</body>
</html>
