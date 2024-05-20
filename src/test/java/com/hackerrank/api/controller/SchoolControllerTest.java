package com.hackerrank.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.api.model.Student;
import com.hackerrank.api.model.Teacher;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
class SchoolControllerTest {
    ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addNewStudentToATeacher() throws Exception {
        Student expected = Student.builder()
                .name("New")
                .build();

        Teacher actual = om.readValue(mockMvc.perform(post("/school/teacher/1/addStudent")
                        .contentType("application/json")
                        .content(om.writeValueAsString(expected)))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), Teacher.class);

        Assert.assertEquals(1, actual.getStudents().stream().filter(s -> s.getName().equals(expected.getName())).count());
    }

    @Test
    public void addExistingStudentToATeacher() throws Exception {
        Student joy = Student.builder()
                .id(1)
                .name("Existing")
                .build();

        Teacher actual = om.readValue(mockMvc.perform(post("/school/teacher/1/addStudent")
                        .contentType("application/json")
                        .content(om.writeValueAsString(joy)))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), Teacher.class);

        Assert.assertEquals(1, actual.getStudents().stream().filter(s -> s.getName().equals("Salo")).count());
    }

    @Test
    public void getStudentsOfATeacher() throws Exception {
        addNewStudentToATeacher();
        addExistingStudentToATeacher();

        Set<Student> actual = om.readValue(mockMvc.perform(get("/school/teacher/1/students")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<Set<Student>>() {
        });

        Assert.assertEquals(2, actual.size());
        Assert.assertEquals("New,Salo", actual.stream().map(s -> s.getName()).sorted().collect(Collectors.joining(",")));
    }

    @Test
    public void getTeachersOfAStudent() throws Exception {
        addNewStudentToATeacher();
        addExistingStudentToATeacher();

        Set<Teacher> actual = om.readValue(mockMvc.perform(get("/school/student/1/teachers")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<Set<Teacher>>() {
        });

        Assert.assertEquals(1, actual.size());
        Assert.assertEquals("Mark", actual.stream().map(t -> t.getName()).sorted().collect(Collectors.joining(",")));
    }
}
