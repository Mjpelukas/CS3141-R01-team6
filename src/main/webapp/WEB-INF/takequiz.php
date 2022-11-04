<?php
// Invoke file database.ini to connect to the database.
function connect() 
{
    $config = parse_ini_file("database.ini");
    $dbh = new PDO($config['dsn'], $config['username'], $config['password']);
    $dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    return $dbh;
}

// Let a user take a quiz corresponding to a provided quiz name.
function takeQuiz($quizName, $username)
{
    try
    {
        // Connect to the database.
        $dbh = connect();

        // Set up the MySQL statement to obtain the quiz questions matching the provided quiz name and username.
        $sqlstmt = "SELECT * from QuizQuestions ";
    
        // Use prepared statements to prevent SQL injection.
        $statement = $dbh->prepare($sqlstmt . " where quizName = :quizName and username = :username");
        $statement->bindParam(":quizName", $quizName);                            
        $statement->bindParam(":username", $username);
        $result = $statement->execute();
        $questions = $statement->fetchAll();

        // Begin the PHP form where the user takes the quiz.
        ?>
        <form class="quiz-form" action="takequiz.php" method="POST">
            <?php
            foreach($questions as $question)
            {
                // Display the current question with its corresponding prompt.
                echo("<p>$question['question']. $question['prompt']</p>");

                // If the question type is multiple choice, display the choices A-D.
                if($question['questionType'] == "Multiple Choice")
                {
                    echo("<input type='radio' id='multipleChoice' name='" . $question['question'] . "' value='" . $question['choiceA'] . "'>");
                    echo("<label for='multipleChoice'> A) " . $question['choiceA'] . "</label><br>");

                    echo("<input type='radio' id='multipleChoice' name='" . $question['question'] . "' value='" . $question['choiceB'] . "'>");
                    echo("<label for='multipleChoice'> B) " . $question['choiceB'] . "</label><br>");

                    echo("<input type='radio' id='multipleChoice' name='" . $question['question'] . "' value='" . $question['choiceC'] . "'>");
                    echo("<label for='multipleChoice'> C) " . $question['choiceC'] . "</label><br>");

                    echo("<input type='radio' id='multipleChoice' name='" . $question['question'] . "' value='" . $question['choiceD'] . "'>");
                    echo("<label for='multipleChoice'> D) " . $question['choiceD'] . "</label><br>");

                    // Prompt the user to select an answer from the question.
                    echo("<input type='submit' name='submitAnswer' value='submitAnswer'>");
                    
                    // Once the user selects an answer for this question, inform them whether their choice was correct.
                    if(isset($_POST['submitAnswer'])) 
                    {
                        // Set up the SQL statement to update the user's number of total guesses for this question.
                        $sqlstmt = "UPDATE Mastery SET totalGuesses = totalGuesses + 1";
    
                        // Use prepared statements to prevent SQL injection.
                        $statement = $dbh->prepare($sqlstmt . " where quizName = :quizName and question = :question and username = :username");
                        $statement->bindParam(":quizName", $quizName);
                        $statement->bindParam(":question", $question);                             
                        $statement->bindParam(":username", $username);
                        $statement->executeUpdate();

                        if($_POST['submitAnswer'] == $question['answer'])
                        {
                            echo("Correct!");

                            // Set up the SQL statement to update the user's number of correct guesses for this question.
                            $sqlstmt = "UPDATE Mastery SET correctGuesses = correctGuesses + 1";
        
                            // Use prepared statements to prevent SQL injection.
                            $statement = $dbh->prepare($sqlstmt . " where quizName = :quizName and question = :question and username = :username");
                            $statement->bindParam(":quizName", $quizName);
                            $statement->bindParam(":question", $question);                             
                            $statement->bindParam(":username", $username);
                            $statement->executeUpdate();
                        }
                        else
                        {
                            echo("Incorrect. The correct answer was '" . $question['answer'] . ".'");
                        }
                    }
                }
            }
    }
    catch(PDOException $exception)
    {   
        print "Error! " . $exception->getMessage() . "<br/>";
        die();
    }
}