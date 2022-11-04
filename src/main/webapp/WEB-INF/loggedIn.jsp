<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
</head>
	<body>
    	<p>Hi you are now logged in (this is just a test shit web page)</p>
    	<!-- <form method="post" action="logout"> -->
    	<form>
    		<input type="button" value="Thing Logout" onclick="window.location='base.html'" >
    	</form>
        <form method="get" action="temporary">
        	<button name="getFlashcardSetsName" type="submit" value="getFlashcardSets">Get your Flashcard Sets</button>
        </form>
	</body>
</html>
