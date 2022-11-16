<!DOCTYPE HTML>
<html>
	<head>
    	<meta charset="UTF-8">
    	<link rel="stylesheet" type="text/css" href="Styling/styles.css" />
	</head>
	
	<!-- Testing new website format 
	<h1 class="title">Quizzler</h1>
	<h2 class="subheader">Login Page</h2>
	-->
	
	<body class="body">

	
	<ul>
			<li><a href=http://localhost:8080/Quizzler/Home.jsp>Home</a></li>
			<li><a href=http://localhost:8080/Quizzler/Home.jsp>About</a></li>
			<li><a href=http://localhost:8080/Quizzler/Home.jsp>Search</a></li>
			<li><a style="float:right" href=http://localhost:8080/Quizzler/CreateAccount.jsp>Sign Up</a></li>
		</ul>
	
	<h1 class="title">Quizzler</h1>
	<h2 class="subheader">Login Page</h2>
	
    	<div>
        	<form method="post" action="login">
            	<input name="login_username" type="text" id="username" placeholder="Username"/>
            	<input name="login_password" type="password" id="password" placeholder="Password"/>
            	
		<!-- This error message only displays things when there's an error outputted by login.java -->
            	<p>${error}</p>	
            	
            	<input type="submit" value="Login" />
            	<input type="button" value="Create Account" onclick="window.location='CreateAccount.jsp'">
        	</form>
		</div>
	</body>
</html>
