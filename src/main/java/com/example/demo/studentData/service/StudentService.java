package com.example.demo.studentData.service;

import com.example.demo.studentData.domain.Student;
import com.example.demo.studentData.exception.StudentAlreadyExistException;
import com.example.demo.studentData.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student) throws StudentAlreadyExistException;

    boolean deleteStudentByRollNo(int rollNo) throws StudentNotFoundException;

    Student getStudentByRollNo(int rollNo) throws StudentNotFoundException;


    List<Student> getAllStudent();

    Student updateStudentDetails(Student student, int rollNo) throws StudentNotFoundException ;
}
