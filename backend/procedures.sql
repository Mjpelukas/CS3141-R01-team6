/* Drop procedures if they already exist in memory. */
drop procedure if exists createUser;
drop procedure if exists createFlashcardSet;
drop procedure if exists createFlashcard;
drop procedure if exists createQuiz;
drop procedure if exists createQuizQuestion;

/* Create procedure SQL statements. */
delimiter //
create procedure createUser(email varchar(20), username varchar(30), user_password varchar(256))
    begin
        insert into Users values(email, username, sha2(user_password, 256), 0);
    end//
delimiter ;

delimiter //
create procedure createFlashcardSet(setName varchar(30), setOwner varchar(20), course varchar(10), isPublic int)
    begin
        insert into FlashcardSets values(setName, setOwner, course, isPublic, 0);
    end//
delimiter ;

delimiter //
create procedure createFlashcard(term varchar(50), term_definition varchar(256), setName varchar(30), setOwner varchar(20))
    begin
        insert into Flashcards values(term, term_definition, setName, setOwner);
    end//
delimiter ;

delimiter //
create procedure createQuiz(quizName varchar(30), quizOwner varchar(20), course varchar(10), isPublic int)
    begin
        insert into Quizzes values(quizName, quizOwner, course, isPublic, 0);
    end//
delimiter ;

delimiter //
create procedure createQuizQuestion(question int, quizName varchar(30), questionType varchar(20),
                                    question varchar(256), answer varchar(256), choiceA varchar(256),
                                    choiceB varchar(256), choiceC varchar(256), choiceD varchar(256))
    begin
        insert into QuizQuestions values(question, quizName, questionType, question, answer, 
                                            choiceA, choiceB, choiceC, choiceD);
    end//
delimiter ;
