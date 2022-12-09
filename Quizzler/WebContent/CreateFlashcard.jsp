<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*" %>

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
				<input type="submit" name="create" value="Create" />
			</form>
		</div>
		<div>
			<form method="get" action="flashcards">
				<button style="background-color: #800003;"
				name="set_name" type="submit" value="getFlashcards">Cancel</button>
			</form>
		</div>
		</div>
	</body>
</html>
