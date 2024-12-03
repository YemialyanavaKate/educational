CREATE TABLE COURSE (number INT PRIMARY KEY, name VARCHAR (255), location VARCHAR (255), price DECIMAL, duration INT, start TIMESTAMP WITH TIME ZONE, stop TIMESTAMP WITH TIME ZONE, teacher_number INT, category_number INT);
CREATE TABLE STUDENT (number INT PRIMARY KEY, name VARCHAR (255), surname VARCHAR (255), login VARCHAR (255), password VARCHAR (255), role VARCHAR (255), balance DECIMAL);
CREATE TABLE TEACHER (number INT PRIMARY KEY, name VARCHAR (255), surname VARCHAR (255), login VARCHAR (255), password VARCHAR (255), role VARCHAR (255));
CREATE TABLE CATEGORY (number INT PRIMARY KEY, category VARCHAR (255));
CREATE TABLE COURSE_STUDENT (course_number INT , student_number INT, PRIMARY KEY (course_number, student_number), FOREIGN KEY (course_number) REFERENCES COURSE(number), FOREIGN KEY (student_number) REFERENCES STUDENT(number));

