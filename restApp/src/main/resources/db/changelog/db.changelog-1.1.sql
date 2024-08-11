--liquibase formatted sql

--changeset PolKevich:1
INSERT INTO project (projectname, projectdescription)
VALUES ('ITBCamp in java', 'This is a project with a three-level development architecture.'),
('Testing application', 'This is a project testing application.'),
('Bank application', 'Banking mobile application.');

--changeset PolKevich:2
INSERT INTO user (lastname, firstname, patronymic, email, post)
VALUES ('ivanov','ivan','ivamovich', 'ivanov@mail.com', "java developer"),
('sergeev','sergei','sergeevich', 'serg@mail.com', "QA"),
('alexeev','alexei','alexeevich', 'lexa@mail.com', "devOps");

--changeset PolKevich:3
INSERT INTO user_project(project_id, user_id)
VALUES ( 1, 1),
(2,2),
(3,3);