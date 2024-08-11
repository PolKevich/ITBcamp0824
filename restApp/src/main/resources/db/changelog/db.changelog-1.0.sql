--liquibase formatted sql

--changeset PolKevich:1
create table if not exists project(
    project_id   bigint primary key auto_increment,
    projectname varchar(60) null,
    projectdescription varchar(150) null
);
--rollback DROP TABLE project

--changeset PolKevich:2
create table if not exists user(
    user_id bigint primary key auto_increment,
    lastname  varchar(40) null,
    firstname  varchar(20) null,
    patronymic varchar(40) null,
    email   varchar(50) null,
    post   varchar(40) null
);
--rollback DROP TABLE user

--changeset PolKevich:3
create table if not exists user_project(
    user_project_id bigint primary key auto_increment,
    project_id bigint,
    user_id bigint,
    FOREIGN KEY (project_id) REFERENCES project (project_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);
--rollback DROP TABLE user_project