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
			</tr>
				<c:forEach items="${masteryQuestion}" var="mPart">
					<tr>
						<td><c:out value="${mPart[0]}"/></td>
						<td><c:out value="${mPart[1]}"/></td>
						<td><c:out value="${mPart[2]}"/></td>
						<td><c:out value="${mPart[3]}"/></td>
					</tr>
				</c:forEach>
					<tr>
						<td>0</td>
						<td>0.25</td>
						<td>1</td>
						<td>4</td>
						</tr>
						<tr>
						<td>1</td>
						<td>0.5</td>
						<td>2</td>
						<td>4</td>
						</tr>
						<tr>
						<td>2</td>
						<td>0.75</td>
						<td>3</td>
						<td>4</td>
						</tr>
						<tr>
						<td>3</td>
						<td>1</td>
						<td>4</td>
						<td>4</td>
						</tr>
			</table>
			<h2>I'll fix the CSS and number formatting next Sprint</h2>
	</div>
			

</body>
</html>