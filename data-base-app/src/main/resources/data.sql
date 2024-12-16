INSERT INTO COURSE (number, name, location, price, duration, start, stop, teacher_number, category_number) VALUES (1, 'JAVA_CORE', 'Minsk', '2000', 4, '2024-01-10 10:00:00+02', '2024-05-10 10:00:00+02', 1, 1);
INSERT INTO COURSE (number, name, location, price, duration, start, stop, teacher_number, category_number) VALUES (2, 'JAVA_ENTERPRISE', 'Brest', '3000', 5, '2026-01-10 10:00:00+02', '2026-05-10 10:00:00+02', 2, 1);
INSERT INTO COURSE (number, name, location, price, duration, start, stop, teacher_number, category_number) VALUES (3, 'JAVA_SUPER', 'Minsk', '3000', 5, '2026-06-10 10:00:00+02', '2026-11-10 10:00:00+02', 1, 1);
INSERT INTO COURSE (number, name, location, price, duration, start, stop, teacher_number, category_number) VALUES (4, 'Manager_core', 'Minsk', '1000', 3, '2026-02-10 10:00:00+02', '2026-05-10 10:00:00+02', 2, 2);
INSERT INTO STUDENT (number, name, surname, login, password, role, balance) VALUES (1, 'Anne', 'Fox', 'Fox', '$2a$12$5CNRriyXrjcAn.aEX3Vx9e9.MBPTA4h5Q5T2nU2lHEK2xffNzblBW', 'student', '9000');
INSERT INTO STUDENT (number, name, surname, login, password, role, balance) VALUES (2, 'Tom', 'Cruise', 'Cruise', '$2a$12$Z5rqcuYN70EswZ/3KuSCFe9xj4tk20ojQFJZ.OZGFDWLXVYgjme/G', 'student', '5000');
INSERT INTO STUDENT (number, name, surname, login, password, role, balance) VALUES (3, 'Brad', 'Pit', 'Pit', '$2a$12$ennHiFkyB962tDq98BgoFuM6SHausvtc322gDoxM/FPbVIAhM6.p.', 'student', '1000');
INSERT INTO STUDENT (number, name, surname, login, password, role, balance) VALUES (4, 'Tea', 'Bardo', 'Bardo', '$2a$12$FGsRH6EeFKP8hI3Wu5tQ0enF45MWmikexoX/K6czGqaYnTqUj.EVm', 'student', '3000');
INSERT INTO TEACHER (number, name, surname, login, password, role) VALUES (1, 'Eva', 'Smith', 'Smith', '$2a$12$5CNRriyXrjcAn.aEX3Vx9e9.MBPTA4h5Q5T2nU2lHEK2xffNzblBW', 'teacher');
INSERT INTO TEACHER (number, name, surname, login, password, role) VALUES (2, 'Jon', 'Adams', 'Adams', '$2a$12$d8zAVVlDhO3Xh30lKqUpfeJzlDClutRWdMWnILDXwI1gVDdVl60zm', 'teacher');
INSERT INTO CATEGORY (number, category) VALUES (1, 'IT');
INSERT INTO CATEGORY (number, category) VALUES (2, 'MANAGEMENT');
INSERT INTO COURSE_STUDENT (course_number, student_number) VALUES (1, 1);
INSERT INTO COURSE_STUDENT (course_number, student_number) VALUES (1, 2);
INSERT INTO COURSE_STUDENT (course_number, student_number) VALUES (2, 3);
INSERT INTO COURSE_STUDENT (course_number, student_number) VALUES (2, 4);
INSERT INTO COURSE_STUDENT (course_number, student_number) VALUES (2, 1);
INSERT INTO COURSE_STUDENT (course_number, student_number) VALUES (4, 1);





