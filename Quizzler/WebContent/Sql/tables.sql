use 0MkLwGAyw5;
/* Drop tables if they already exist in memory. */
drop table if exists Mastery;
drop table if exists QuizQuestions;
drop table if exists Quizzes;
drop table if exists Flashcards;
drop table if exists FlashcardSets;
drop table if exists Users;

/* Create Table SQL Statements */
create table Users
(
    username varchar(30) primary key,
    email varchar(20),
    user_password varchar(256) not null
);

create table FlashcardSets
(
    setName varchar(30),
    setOwner varchar(30),
    description text,
    course varchar(10),
    isPublic tinyint default 0,
    mastery int default 0,
    primary key(setName, setOwner),
    foreign key(setOwner) references Users(username)
    on delete cascade
    on update cascade
);

create table Flashcards
(
    term varchar(50),
    term_definition varchar(256) not null,
    setName varchar(30),
    setOwner varchar(30),
    primary key(term, setName, setOwner),
    foreign key(setName, setOwner) references FlashcardSets(setName, setOwner)
    on delete cascade
    on update cascade
);

create table Quizzes
(
    quizName varchar(30),
    quizOwner varchar(30),
    course varchar(10) not null,
    isPublic int default 0,
    primary key(quizName, quizOwner),
    foreign key(quizOwner) references Users(username)
    on delete cascade
    on update cascade
);

-- IDENTITY is the equivalent of AUTO_INCREMENT
create table QuizQuestions
(	
	quizID int not null,
	quizOwner varchar(30),
    quizName varchar(30),
    question int,
    questionType varchar(20) not null,
    prompt varchar(256) not null,
    answer char(1) not null,
    choiceA varchar(256) not null,
    choiceB varchar(256),
    choiceC varchar(256),
    choiceD varchar(256),
    primary key(quizID),
    foreign key(quizName, quizOwner) references Quizzes(quizName, quizOwner)
    on delete cascade
    on update cascade
);


create table Mastery
(
    quizOwner varchar(30),
    quizName varchar(30),
    question int,
    correctGuesses int default 0,
    totalGuesses int default 0,
    primary key(quizOwner, quizName, question),
    foreign key(quizName, quizOwner, question) references QuizQuestions(quizName, quizOwner, question)
    on delete cascade
    on update cascade
);

insert into Users values('username', null, sha2('password', 256));
/* Create Index SQL Statements 
CREATE INDEX usernameIndex ON TABLE Mastery(username);*/