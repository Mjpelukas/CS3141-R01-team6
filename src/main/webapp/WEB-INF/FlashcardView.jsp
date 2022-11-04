<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<link rel="stylesheet" type="text/css" href="flashcards.css" />
		<title>Sets</title>
	</head>
	<h1 class="title">Quizzler</h1>
	<h2 class="subheader">Flashcards</h2>
	<body class="body">
		<div>
		<form>
			<input type="button" value="Create Flashcard" onclick="window.location='createSet.html'">
		</form>
		<form>
			<table>
			<c:forEach items="${terms}" var="term">
					<tr>
					<td><c:out value = "${term[0]}"/></td>
					<td><c:out value = "${term[1]}"/></td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<form method="get" action="sets">
        	<button name="getFlashcardSetsName" style="background-color:#800003;" type="submit" value="getFlashcardSets">Back</button>
        </form>
		</div>
            
	</body>
</html>