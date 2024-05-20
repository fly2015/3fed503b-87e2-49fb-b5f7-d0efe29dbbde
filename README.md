## Environment

- Java version: 1.8
- Maven version: 3.*
- Spring Boot version: 2.3.4.RELEASE

## Data

Example of a Teacher data JSON object:

```json
{
  "id": 1,
  "name": "Mark",
  "students": "[]"
}
```

Example of a Student data JSON object:

```json
{
  "id": 1,
  "name": "Salo",
  "teachers": "[]"
}
```

## Requirements

This is a teacher and student management system for a typical school where a teacher has many students and a student has
many teachers.
Here we are using hibernate many-to-many relationship to manage such information and you have to complete the following
RESET endpoints.

You have to complete the implementation following 3 REST endpoints in `SchoolController`.

`POST` request to `/school/teacher/{teacherId}/addStudent`:

* assume that the given `{teacherId}` already exists in the database.
* request body will have a student instance and that might be new student or the existing one.
* assume that the given student has never been added to the given teacher.
* return the teacher instance after adding the given student with a status code of 200.

`GET` request to `/school/teacher/{teacherId}/students`:

* assume that the given `{teacherId}` already exists in the database.
* return the list of students associated with that given teacherId and status code 200.

`GET` request to `/school/student/{studentId}/teachers`:

* assume that the given `{studentId}` already exists in the database.
* return the list of teachers associated with that given studentId and status code 200.

## Commands

- run:

```bash
mvn clean spring-boot:run
```

- install:

```bash
mvn clean install
```

- test:

```bash
mvn clean test
```