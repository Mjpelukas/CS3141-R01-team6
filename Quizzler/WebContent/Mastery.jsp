<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
<link rel="stylesheet" type="text/css" href="Styling/flashcards.css" />
<title>Mastery</title>
</head>

	<h1 class="title">Quizzler</h1>
	<h2 class="subheader">Mastery</h2>
	<body class="body">
		<div> <!-- idk why this div is here, but does help format maybe in future -->
			<table class="masteryTable">
				<tr class ="tableTitles">
				<td>Question</td><td>Percentage Mastery</td><td>Correct Guesses</td><td>Total Guesses</td>
					<c:forEach items="${masteryQuestion}" var="mPart">
						<tr>
							<td><c:out value="${mPart[0]}" /></td>
							<td>Percentage Calculation coming soon</td>
							<td><c:out value="${mPart[1]}"/></td>
							<td><c:out value="${mPart[2]}"/></td>
						</tr>
					</c:forEach>
			</table>
		</div>
	
	</body>
</html>
