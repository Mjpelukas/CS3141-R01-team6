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
        	<!-- <form method="post" action="/login.php" target="_self"> -->
        	<form method="post" action="login">
            	<!-- <label for="login_username">Username</label> -->
            	<input name="login_username" type="text" id="username" placeholder="Username"/>
            	
            	<!--<label for="login_password">Password</label>-->
            	<input name="login_password" type="password" id="password" placeholder="Password"/>
            	
            	<p>${error}</p>	
            	
            	<input type="submit" value="Login" />
            	
            	<input type="button" value="Create Account" onclick="window.location='CreateAccount.jsp'">
        	</form>
    	
    	<!-- <form>
    		<input type="button" value="Create Account" onclick="window.location='CreateAccount.jsp'">
		</form>-->
		</div>
	</body>
</html>