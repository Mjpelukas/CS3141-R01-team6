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

				
	<div class="flash_container" >
		<c:forEach items="${terms}" var="term" varStatus="counter">
	
			<div class ="flashcard" id="flash_${counter.index}" onclick='document.getElementById("flash_${counter.index}").style.display = "none";
			document.getElementById("flash_back_${counter.index}").style.display = "inline-flex"'>
				<p > ${term[0]}</p >
			</div >
			<div class = "flash_back" id="flash_back_${counter.index}" 
			onclick='document.getElementById("flash_${counter.index}").style.display = "inline-flex"; document.getElementById("flash_back_${counter.index}").style.display = "none"'>
				<p > ${term[1]} </p >
			</div >
		</c:forEach>
	</div>
		
					
	<form method="get" action="sets">
			<button name="getFlashcardSetsName"
				style="background-color: #800003;" type="submit"
				value="getFlashcardSets">Back</button>
	</form>
</div>	
</body>
</html>