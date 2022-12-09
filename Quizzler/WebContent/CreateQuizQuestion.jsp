<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*" %>

<!--- TODO: FIX THIS GARBAGE
 //PrintWriter memberPrint = request.getWriter();out.print(request.getAttribute("set_name")); 
-->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
		<title>Create A Quiz Question</title>
	</head>
	<h1 class="title">Create a Quiz Question</h1>
	<!-- $set_name doesnt place -->
	<body class="body">
		<div>
			<form method="post" action="Quizzes">
			<input type="hidden" name="creationType" value="question">
			<input type="hidden" name="quizID" value="${QID}">
					<label>Enter The Question Prompt Here:</label>
				<textarea name="prompt"/></textarea>	
				<input name="choiceA" type="text" placeholder="Choice A" />
				<input name="choiceB" type="text" placeholder="Choice B" />
				<input name="choiceC" type="text" placeholder="Choice C" />
				<input name="choiceD" type="text" placeholder="Choice D" />
				<label for="answer">Enter Correct Answer Choice</label>
				<input name="answer" type="text" maxlength="1" />
				<input type="submit" value="Create" />
			</form>
		</div>
		<div>
			<form method="get" action="sets">
				<button style="background-color: #800003;"
					name="createQuizQuestionCancel" type="submit" value="createCancel">Cancel</button>
			</form>
		</div>
	</body>
</html>