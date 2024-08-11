ITBcamp0824 - test task for the position of Java developer

Project Description

This is a project with a three-level development architecture. It is a backend Java 
application built using Java 17, Spring Boot 3, and MySQL 8. The application uses 
REST architectural style and JSON format for the API. It includes endpoints for adding:
projects, users, add user to project, get project with users.

Technologies

Java 17
Spring Boot 3
MySQL 8
Log4j2
Liquibase
JUnit
Mockito
Testcontainers

Endpoints

/user - POST endpoint for adding a user with fields for Surname, Firstname, Patronymic, Email, and Post.
Example json for a request to create a user :

{
"lastname": "vasiliev",
"firstname": "vasiliy",
"patronymic": "vasilievich",
"email": "vasia@gmail.com",
"post": "Project manager"
}

/project - POST endpoint for adding a project with fields for project name, and project description.
Example json for a request to create a project :

{
"projectName": "Bank aplication",
"projectDescription": "Banking application development"
}

/projectid/{projectId}/userid/{userId} - POST endpoint for adding a user to a project with fields for
projectId and userId.
Example json for a request for adding a user to a project :

{
"projectId": 1,
"userId": 1
}

/getproject - GET endpoint to get all projects with users by project name, project description and full name. 
Use ?page=0&size=10 for pagination. Entries are sorted alphabetically by project name and paginated to show 10 records.

Testing

Unit and integration tests have been included using JUnit and testcontainers to raise the test base in Docker. 
Docker must be running to pass integration tests