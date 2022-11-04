<?php
// Invoke file database.ini to connect to the database.
function connect() 
{
    $config = parse_ini_file("database.ini");
    $dbh = new PDO($config['dsn'], $config['username'], $config['password']);
    $dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    return $dbh;
}

// Let a user create a quiz with flexible options.
function createQuiz($username)
{
    try
    {
        // Connect to the database.
        $dbh = connect();

        // Prompt the user to input information about the overall quiz.
        echo("<input type='text' id='quizNameText' name='quizNameText' value='quizNameText'>");
        echo("<label for='quizNameText'>Enter Quiz Name: </label><br>");
        if(isset($_POST['quizNameText'])) {$quizName = $_POST['quizNameText'];}

        echo("<input type='text' id='courseText' name='courseText' value='courseText'>");
        echo("<label for='courseText'>Enter Relevant Course: </label><br>");
        if(isset($_POST['courseText'])) {$course = $_POST['courseText'];}

        echo("Please choose the visibility for this quiz: ");
        echo("<input type='radio' id='private' name='private' value='private'>");
        echo("<label for='private'>Private</label><br>");
        echo("<input type='radio' id='public' name='public' value='public'>");
        echo("<label for='public'>Public</label><br>");
        echo("<input type='submit' name='isPublicChoice' value='isPublicChoice'>");
        if(isset($_POST['isPublicChoice'])) {$isPublic = $_POST['isPublicChoice'];}

        // Set up the MySQL statement to create the overall quiz.
        $sqlstmt = "INSERT INTO Quizzes values($quizName, $username, $course, $isPublic)";
        $statement = $dbh->prepare($sqlstmt);
		$result = $statement->execute();

        // Prompt the user to add each of the quiz questions.
        $isInProgress = true;
        while($isInProgress)
        {
            echo("Do you wish to add another quiz question?");

            echo("<input type='radio' id='yes' name='yes' value='yes'>");
            echo("<label for='yes'>Yes</label><br>");

            echo("<input type='radio' id='no' name='no' value='no'>");
            echo("<label for='no'>No</label><br>");

            echo("<input type='submit' name='isPublicChoice' value='isPublicChoice'>");
            if(isset($_POST['yes'])) {createQuizQuestion($username, $quizName);}
            else {$isInProgress = false;}
        }
    }
    catch(PDOException $exception)
    {   
        print "Error! " . $exception->getMessage() . "<br/>";
        die();
    }
}

// Let a user create a quiz question belonging to a quiz name.
function createQuizQuestion($username, $quizName)
{
    // Prompt the user to select a question type.
    // TODO: do more question types besides multiple choice (MC)
    echo("Please select a question type: ");
    echo("<input type='radio' id='MC' name='MC' value='MC'>");
    echo("<label for='MC'>Multiple Choice</label><br>");
    if(isset($_POST['MC'])) {$questionType = $_POST['MC'];}

    // Prompt the user to enter this question's prompt.
    echo("<input type='text' id='prompt' name='prompt' value='prompt'>");
    echo("<label for='prompt'>Enter this Question's Prompt: </label><br>");
    if(isset($_POST['prompt'])) {$prompt = $_POST['prompt'];}

    // Prompt the user to enter this question's first answer choice.
    echo("<input type='text' id='choiceA' name='choiceA' value='choiceA'>");
    echo("<label for='choiceA'>Enter Choice A for this Question: </label><br>");
    if(isset($_POST['choiceA'])) {$choiceA = $_POST['choiceA'];}

    // Prompt the user to enter this question's second answer choice.
    echo("<input type='text' id='choiceB' name='choiceB' value='choiceB'>");
    echo("<label for='choiceB'>Enter Choice B for this Question: </label><br>");
    if(isset($_POST['choiceB'])) {$choiceB = $_POST['choiceB'];}

    // Prompt the user to enter this question's third answer choice.
    echo("<input type='text' id='choiceC' name='choiceC' value='choiceC'>");
    echo("<label for='choiceC'>Enter Choice C for this Question: </label><br>");
    if(isset($_POST['choiceC'])) {$choiceC = $_POST['choiceC'];}

    // Prompt the user to enter this question's fourth answer choice.
    echo("<input type='text' id='choiceD' name='choiceD' value='choiceD'>");
    echo("<label for='choiceD'>Enter Choice D for this Question: </label><br>");
    if(isset($_POST['choiceD'])) {$choiceD = $_POST['choiceD'];}

    // Prompt the user to select which one of the above choices A-D is the correct answer.
    echo("Please select which one of the above choices A-D is the correct answer: ");

    echo("<input type='radio' id='correctChoiceA' name='correctChoiceA' value='correctChoiceA'>");
    echo("<label for='correctChoiceA'>a) " . $choiceA . "</label><br>");

    echo("<input type='radio' id='correctChoiceB' name='correctChoiceB' value='correctChoiceB'>");
    echo("<label for='correctChoiceB'>b) " . $choiceB . "</label><br>");

    echo("<input type='radio' id='correctChoiceC' name='correctChoiceC' value='correctChoiceC'>");
    echo("<label for='correctChoiceC'>c) " . $choiceC . "</label><br>");

    echo("<input type='radio' id='correctChoiceD' name='correctChoiceD' value='correctChoiceD'>");
    echo("<label for='correctChoiceD'>d) " . $choiceD . "</label><br>");

    if(isset($_POST['correctChoiceA'])) {$answer = $choiceA;}
    else if(isset($_POST['correctChoiceB'])) {$answer = $choiceB;}
    else if(isset($_POST['correctChoiceC'])) {$answer = $choiceC;}
    else if(isset($_POST['correctChoiceD'])) {$answer = $choiceD;}

    // Set up the MySQL statement to create the individual quiz question.
    $sqlstmt = "INSERT INTO QuizQuestions(quizName, questionType, prompt, answer, choiceA, choiceB, choiceC, choiceD)
        values($quizName, $questionType, $prompt, $answer, $choiceA, $choiceB, $choiceC, $choiceD)";
    $statement = $dbh->prepare($sqlstmt);
    $result = $statement->execute();
}
?>