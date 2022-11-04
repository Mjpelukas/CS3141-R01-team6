<?php
    session_start();
?>

<html>
    <link rel="stylesheet" href="main.css">
    <body>
        <form action="login.php" method="post">
            <?php
                // If the user has not logged in, require them to fill the login form.
                if(!isset($_SESSION['username'])) 
                {
            ?>
                <input type="login" class="login" value='login' name="login">
            <?php
                } 
                // Otherwise, print a welcome message and provide a means for them to log out.
                else 
                {
                    echo "Welcome " . $_SESSION['username'] . "! Let's get studying.";
            ?>
                    <input type="logout" class="logout" value='logout' name="logout">
            <?php
                }
            ?>
        </form>
    </body>
</html>