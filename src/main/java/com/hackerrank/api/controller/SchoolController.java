package com.hackerrank.api.controller;

import com.hackerrank.api.model.Student;
import com.hackerrank.api.model.Teacher;
import com.hackerrank.api.repository.StudentRepository;
import com.hackerrank.api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public SchoolController(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    //create teacher
    @PostMapping("/teacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher _teacher = teacherRepository.save(teacher);
        return new ResponseEntity<>(_teacher, HttpStatus.CREATED);
    }

    //create student
    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student _student = studentRepository.save(student);
        return new ResponseEntity<>(_student, HttpStatus.CREATED);
    }

    //add student to a teacher
    @PostMapping("/teacher/{teacherId}/addStudent")
    public ResponseEntity<Teacher> addStudent(@PathVariable(value = "teacherId") Long teacherId, @RequestBody Student addStudent) {
        //TODO
        return null;
    }

    //get students of a teacher
    @GetMapping("/teacher/{teacherId}/students")
    public ResponseEntity<Set<Student>> getStudentsOfATeacher(@PathVariable(value = "teacherId") Long teacherId) {
        //TODO
        return null;
    }

    // get teachers of a student
    @GetMapping("/student/{studentId}/teachers")
    public ResponseEntity<Set<Teacher>> getTeachersOfAStudent(@PathVariable(value = "studentId") Long studentId) {
        //TODO
        return null;
    }
}
