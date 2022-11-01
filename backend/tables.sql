/* Drop tables if they already exist in memory. */
drop table if exists Users;
drop table if exists FlashcardSets;
drop table if exists Flashcards;
drop table if exists Quizzes;
drop table if exists QuizQuestions;
drop table if exists Mastery;

/* Create Table SQL Statements */
create table Users
(
    username varchar(30) primary key,
    email varchar(20) not null,
    user_password varchar(256) not null,
    isLoggedIn int default 0
);

create table FlashcardSets
(
    setName varchar(30) primary key,
    setOwner varchar(30),
    course varchar(10) not null,
    isPublic int default 0,
    mastery int default 0,
    foreign key(setOwner) references Users(username)
);

create table Flashcards
(
    term varchar(50) primary key,
    term_definition varchar(256) not null,
    setName varchar(30),
    foreign key(setName) references FlashcardSets(setName)
);

create table Quizzes
(
    quizName varchar(30) primary key,
    quizOwner varchar(30),
    course varchar(10) not null,
    isPublic int default 0,
    foreign key(quizOwner) references Users(username)
);

-- IDENTITY is the equivalent of AUTO_INCREMENT
create table QuizQuestions
(
    question int primary key IDENTITY,
    quizName varchar(30),
    questionType varchar(20) not null,
    prompt varchar(256) not null,
    answer varchar(256) not null,
    choiceA varchar(256) not null,
    choiceB varchar(256) not null,
    choiceC varchar(256) not null,
    choiceD varchar(256) not null,
    foreign key(quizName) references Quizzes(quizName)
);

create table Mastery
(
    username varchar(30),
    quizName varchar(30),
    question int,
    correctGuesses int default 0,
    totalGuesses int default 0,
    primary key(username, quizName, quizQuestion),
    foreign key(username) references Users(username),
    foreign key(quizName, question) references QuizQuestions(quizName, question)
);

/* Create Index SQL Statements */
CREATE INDEX usernameIndex ON TABLE Mastery(username);