package com.example.demo.studentData.controller;

import com.example.demo.studentData.domain.Student;
import com.example.demo.studentData.exception.StudentAlreadyExistException;
import com.example.demo.studentData.exception.StudentNotFoundException;
import com.example.demo.studentData.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/")
public class StudentController {

    private ResponseEntity responseEntity;

    private StudentServiceImpl studentServiceImpl;

    @Autowired
    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

//    http://localhost:8082/student/addStudent
    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody Student student) throws StudentAlreadyExistException {
        return new ResponseEntity<>(studentServiceImpl.addStudent(student), HttpStatus.CREATED);
    }

    //    http://localhost:8082/student/deleteStudentByRollNo/rollNo
    @DeleteMapping("/deleteStudentByRollNo/{rollNo}")
    public ResponseEntity<?> deleteStudent(@PathVariable int rollNo) throws StudentNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(studentServiceImpl.deleteStudentByRollNo(rollNo),HttpStatus.OK);
        }catch (StudentNotFoundException e){
            throw new StudentNotFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //    http://localhost:8082/student/getStudentByRollNo/rollNo
    @GetMapping("/getStudentByRollNo/{rollNo}")
    public ResponseEntity<?> getStudentByRollNo(@PathVariable int rollNo) throws StudentNotFoundException {
        Student student = studentServiceImpl.getStudentByRollNo(rollNo);
        return responseEntity = new ResponseEntity<>(student,HttpStatus.OK);
    }

    //    http://localhost:8082/student/getAllStudent
    @GetMapping("/getAllStudent")
    public ResponseEntity<?> getAllStudent(){
        responseEntity = new ResponseEntity<>(studentServiceImpl.getAllStudent(),HttpStatus.OK);
        return responseEntity;
    }

    //    http://localhost:8082/student/updateStudent/rollNo
    @PutMapping("/updateStudent/{rollNo}")
    public ResponseEntity<?> updateRestaurant(@RequestBody Student student,@PathVariable int rollNo) throws StudentNotFoundException {
        try {
            return new ResponseEntity<>(studentServiceImpl.updateStudentDetails(student,rollNo),HttpStatus.CREATED);
        } catch (StudentNotFoundException e) {
            throw new StudentNotFoundException();
        }
    }
}
