<!DOCTYPE HTML>
<html>
	<head>
    	<meta charset="UTF-8">
    	<link rel="stylesheet" type="text/css" href="styles.css" />
	</head>
	<h1 class="title">Quizzler</h1>
	<h2 class="subheader">Login Page</h2>
	<body class="body">
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
