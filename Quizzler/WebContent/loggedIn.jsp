<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<link rel="stylesheet" type="text/css" href="flashcards.css" />
	</head>
<body>
	<p>Hi you are now logged in (this is just a test shit web page)</p>
	<!-- <form method="post" action="logout"> -->
	<form>
		<input type="button" value="Thing Logout"
			onclick="window.location='base.html'">
	</form>
	<form method="get" action="temporary">
		<button name="getFlashcardSetsName" type="submit"
			value="getFlashcardSets">Get your Flashcard Sets</button>
	</form>
	
	
	
	<div class="flash_container">
		<%
		for (int i = 0; i < 10; i++) {
			out.println("<div class=\"flashcard\" id=\"flash" + i + "\" onclick='document.getElementById(\"flash" + i
			+ "\").style.display = \"none\"; document.getElementById(\"" + "flash_b" + i
			+ "\").style.display = \"inline-block\"'>");
				out.println("<p> test front " + i + "</p>");
			out.println("</div>");
			out.println("<div class=\"flash_back\" id=\"flash_b" + i + "\" onclick='document.getElementById(\"flash"
			+ i + "\").style.display = \"inline-block\"; document.getElementById(\"flash_b" + i
			+ "\").style.display = \"none\"'>");
				out.println("<p> test back " + i + "</p>");
			out.println("</div>");
		}
		%>
	</div>
</body>
</html>