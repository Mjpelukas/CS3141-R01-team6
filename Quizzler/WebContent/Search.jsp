<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
		<title>Search Public Materials</title>
	</head>
	<h1 class="title">Search Public Materials</h1>
	<body class="body">
	<div>
		<!-- Search public flashcard sets by set name. -->
		<form method="post" action="searchResults">
		<input name='setName' type='text' id='searchSetName' placeholder="Search Flashcard Set Name">
		<input type='submit' name='searchSetName' value='Search'>
		</form> 
		
		<!-- Search public quizzes by quiz name. -->
		<form method="post" action="searchResults">
		<input name='quizName' type='text' id='searchQuizName' placeholder="Search Quiz Name">
		<input type='submit' name='searchQuizName' value='Search'>
		</form> 
		
		<!-- Back button -->
		<form method="get" action="sets">
			<button name="getFlashcardSetsName"
				style="background-color: #800003;" type="submit"
				value="getFlashcardSets">Back</button>
		</form>
		</div>
	</body>
</html>
