<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
<link rel="stylesheet" type="text/css" href="Styling/flashcards.css" />
<title>Sets</title>
</head>
<h1 class="title">Quizzler</h1>
<h2 class="subheader">Flashcards</h2>
<body class="body">
	<div>
		<form>
			<input type="button" value="Create Flashcard"
				onclick="window.location='CreateFlashcard.jsp'">
		</form>
		<form>
			<table>
				<c:forEach items="${terms}" var="term">
					<tr>
						<td><c:out value="${term[0]}" /></td>
						<td><c:out value="${term[1]}"/></td>
					</tr>
				</c:forEach>
			</table>
			
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
			
			
			
		</form>
		<form method="get" action="sets">
			<button name="getFlashcardSetsName"
				style="background-color: #800003;" type="submit"
				value="getFlashcardSets">Back</button>
		</form>
	</div>

</body>
</html>