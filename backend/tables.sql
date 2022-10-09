/* Drop tables if they already exist in memory. */
drop table if exists Users;
drop table if exists FlashcardSets;
drop table if exists Flashcards;
drop table if exists Quizzes;
drop table if exists QuizQuestions;

/* Create Table SQL Statements */
create table Users
(
    email varchar(20) primary key,
    username varchar(30) not null,
    user_password varchar(256) not null,
    isLoggedIn int default 0
);

create table FlashcardSets
(
    setName varchar(30) primary key,
    setOwner varchar(20),
    course varchar(10) not null,
    isPublic int default 0,
    mastery int default 0,
    foreign key(setOwner) references Users(email)
);

create table Flashcards
(
    term varchar(50) primary key,
    term_definition varchar(256) not null,
    setName varchar(30),
    setOwner varchar(20),
    foreign key(setName) references FlashcardSets(setName),
    foreign key(setOwner) references Users(email)
);

create table Quizzes
(
    quizName varchar(30) primary key,
    quizOwner varchar(20),
    course varchar(10) not null,
    isPublic int default 0,
    mastery int default 0,
    foreign key(quizOwner) references Users(email)
);

create table QuizQuestions
(
    question int primary key,
    quizName varchar(30),
    questionType varchar(20) not null,
    question varchar(256) not null,
    answer varchar(256) not null,
    choiceA varchar(256) not null,
    choiceB varchar(256) not null,
    choiceC varchar(256) not null,
    choiceD varchar(256) not null,
    foreign key(quizID) references Quizzes(quizID)
);