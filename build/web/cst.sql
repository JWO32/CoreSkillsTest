drop core_skills_test;

create database if not exists core_skills_test;

use core_skills_test;

/*
 * User Database Data Model
 * 
 */

create table if not exists quiz_users
(
	user_id int not null auto_increment,
	user_name varchar(50),
	user_description varchar(255),
	user_email varchar(50),
	primary key(user_id)
);

create table if not exists quiz_groups
(
	group_id int not null auto_increment,
	group_name varchar(50),
	group_description varchar(200),
	primary key(group_id)
);


create table if not exists  users_groups
(
	group_id int not null references quiz_groups.group_id,
	user_id int not null references quiz_users.user_id,
	primary key(group_id, user_id)
);

/*
 * Results database data model
 * 
 */

create table if not exists user_results
(
	quiz_id int not null references quizzes.quiz_id,
	user_id int not null references quiz_users.user_id,
	attempt_id int not null,
	date date not null,
	score int not null,
	comment varchar(255),
	primary key (quiz_id)
);

create table if not exists user_profile
(
	profile_id int not null,
	user_id int not null, references(quiz_users.user_id),
	number_quizzes_complete int,
	average_score int,
	average_level varchar(20)
);


/*
 * Quiz Database Data Model
 * 
 */
create table if not exists quizzes
(
	quiz_id int not null auto increment,
	quiz_title varchar(255),
	quiz_subject varchar(255),
	quiz_duration int,
);

create table if not exists questions
(
	question_id int not null auto increment,
	quiz_id int not null references quizzes.quiz_id,
	question_text varchar(255) not null,
);

create table if not exists answer_options
(
	option_id int not null auto increment,
	question_id int not null references questions.question_id,
	option_text varchar(255) not null,
	is_correct boolean
);