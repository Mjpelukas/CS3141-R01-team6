<?php
    // Require database.php functionality.
    require "database.php";
    session_start();

    // When the user clicks the login button, 
    if(isset($_POST['login'])) 
    {
        // Verify their credentials.
        if(authenticate($_POST['username'], $_POST['password']) == 1)
        {
            // Bind the user's valid username to the current website session.
            $_SESSION['username']=$_POST['username'];	

            // Redirect the user to the main.php page.
            return;
        } 
        // If provided invalid credentials, print an error message.
        else 
        {
			echo '<p style="color:red;" align="center">Incorrect username and/or password.</p>';
        }
    }

    // When the user clicks the logout button, end the current website session.
    if(isset($_POST["logout"])) 
    {
       SESSION_DESTROY();
    }

    // Authenticate a user's credentials when they attempt to log in.
    function authenticate($username, $password) 
    {
        try 
        {
            // Connect to the database.
            $dbh = connect();

            // Set up the MySQL statement to obtain the row matching the entered username and password.
            $sqlstmt = "SELECT count(*) FROM
                        (SELECT username, user_password AS password FROM User ";

            // Use prepared statements to prevent SQL injection.
            $statement = $dbh->prepare($sqlstmt.
                                        " where username = :username and user_password = sha2(:user_password, 256) ");
            $statement->bindParam(":username", $username);
            $statement->bindParam(":password", $password);
            $result = $statement->execute();
            $row=$statement->fetch();
            $dbh=null;

            // Return the number of table rows matching a user's username and password.
            return $row[0];
        }
        // If provided invalid user credentials, print an error message.
        catch (PDOException $error) 
        {
            print "Error! " . $error -> getMessage() . "<br/>";
            die();
        }
    }
?>