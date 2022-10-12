<?php
    // Invoke file database.ini to connect to the database.
    function connect() 
    {
        $config = parse_ini_file("database.ini");
        $dbh = new PDO($config['dsn'], $config['username'], $config['password']);
        $dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        return $dbh;
    }

    // Let a user view a flashcard set corresponding to a provided set name.
    function viewFlashcardSet($setName, $username)
    {
        try 
        {
            // Connect to the database.
            $dbh = connect();

            // Set up the MySQL statement to obtain the rows matching the provided set name and username.
            $sqlstmt = "SELECT term, term_definition from Flashcards ";

            // Use prepared statements to prevent SQL injection.
            $statement = $dbh->prepare($sqlstmt.
                                        " where setName = :setName and username = :username");
            $statement->bindParam(":setName", $setName);                            
            $statement->bindParam(":username", $username);
            $result = $statement->execute();
            $flashcards = $statement->fetchAll();

            // Create the table header for displaying each flashcard's term and definition.
            ?>
            <table>
            <tr>
            <th>Term      </th>
            <th>Definition</th>
            </tr>
            <?php

            // Iterate through each flashcard's term and definition to be entered in the table.
            foreach($flashcards as $flashcard) 
            {
                echo "<tr>";
                echo "<td>" . $row[1] . "</td>";
                echo "<td>" . $row[2] . "</td>";
                echo "</tr>";
            }

            // Print the table to the screen.
            echo "<table>";

            $dbh=null;

            return;
        }
        // If provided invalid credentials, print an error message.
        catch (PDOException $error) 
        {
            print "Error! " . $error->getMessage() . "<br/>";
            die();
        }
    }
?>