<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
<title>Edit Set</title>
</head>
<h1 class="title">Edit Set</h1>
<body class="body">
	<div>
		<form method="post" action="sets">
			<input type="hidden" name="ogSetName" value ="${setName}"> 
			<label>Change Set Name(Optional)</label>
			<input name="setName" type="text" id="set_name" value="${setName}"
			 placeholder= "${setName}" /> 

			<label for="setDescription">Description (Optional)</label>	
			<textarea rows="5" cols="50" name="setDescription"
				id="set_description" ><c:out value="${description}"/></textarea>
			
			<label>
      			Public <input type="checkbox" name="isPublic" value="true"/>
    		</label>
			<br>
			<input type="submit" name="edit" value="Submit Changes" />

		</form>
	</div>
	<div>
		<form method="get" action="sets">
			<button style="background-color: #800003;"
				name="getFlashcardSetsName" type="submit" value="getFlashcardSets">Cancel</button>
		</form>
	</div>
</body>
</html>