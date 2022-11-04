<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<title>Insert title here</title>
	</head>
	<h1 class="title">Create Flashcard</h1>
	<h3 id="member_set">${set_name}</h3>
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
			<form method="get" action="flashcards">
				<button style="background-color: #800003;"
					name="getFlashcardName" type="submit" value="getFlashcard">Cancel</button>
			</form>
		</div>
	</body>
</html>