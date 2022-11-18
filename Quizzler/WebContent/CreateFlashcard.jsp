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
		<title>Create A Flashcard</title>
	</head>
	<h1 class="title">Create a Flashcard</h1>
	<!-- $set_name doesnt place -->
	<h3 class="member_set">${set_name}</h3>
	<body class="body">
		<div>
			<form method="post" action="flashcards">
				<input name="term" type="text" id="term_name"
					placeholder="Term" />
				<textarea rows="5" cols="50" name="definition"
					id="definition_"
					placeholder="Definition"></textarea>
				<input type="submit" value="Create" />
			</form>
		</div>
		<div>
			<form method="get" action="sets">
				<button style="background-color: #800003;"
					name="createFlashcardCancel" type="submit" value="createCancel">Cancel</button>
			</form>
		</div>
	</body>
</html>