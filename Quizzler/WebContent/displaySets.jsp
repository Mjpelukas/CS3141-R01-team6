<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles.css" />
<title>Sets</title>
</head>
<h1 class="title">Quizzler</h1>
<h2 class="subheader">Sets</h2>
<body class="body">
	<div class="buttonList">
		<form method="get" action="flashcards">
			<input style="margin-bottom: 30px;" type="button" value="Create Set"
				onclick="window.location='CreateSet.html'">
			
			<!-- setNames is a java arraylist, this takes it from context attributes
			     and applies a for each loop on it, creating buttons for each. -->
			<c:forEach items="${setNames}" var="item">
				
				<!-- the name for each button is set_name so that when it's submitted, the servlet
				     can access the parameter by the identity "set_name" 
 				     The value which is shown to the user is set to item, which is the set name taken from the arraylist -->
				<input type="submit" value="<c:out value = "${item}"/>" name="set_name"><br>
				
			</c:forEach>
		</form>
		<form>
			<input style="background-color: #800003; margin-top: 40px;"
				type="button" value="Back" onclick="window.location='loggedIn.html'">
		</form>
	</div>
</body>
</html>
