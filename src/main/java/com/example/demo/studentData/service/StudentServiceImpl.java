package com.example.demo.studentData.service;

import com.example.demo.studentData.domain.Student;
import com.example.demo.studentData.exception.StudentAlreadyExistException;
import com.example.demo.studentData.exception.StudentNotFoundException;
import com.example.demo.studentData.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) throws StudentAlreadyExistException {
        if (studentRepository.findById(student.getRollNo()).isPresent()){
            throw new StudentAlreadyExistException();
        }
        return studentRepository.save(student);
    }

    @Override
    public boolean deleteStudentByRollNo(int rollNo) throws StudentNotFoundException {
        boolean result = false;
        if (studentRepository.findById(rollNo).isEmpty()){
            throw new StudentNotFoundException();
        }else {
            studentRepository.deleteById(rollNo);
            result = true;
        }
        return result;
    }

    @Override
    public Student getStudentByRollNo(int rollNo) throws StudentNotFoundException {
        return studentRepository.findById(rollNo).get();
    }


    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudentDetails(Student student, int rollNo) throws StudentNotFoundException {

        Optional<Student> optionalStudent = studentRepository.findById(rollNo);
        if (optionalStudent.isEmpty()){
            return null;
        }
        Student existingStudent = optionalStudent.get();
        if (student.getRollNo()!=0){
            existingStudent.setRollNo(student.getRollNo());
        }
        if (student.getFirstName()!= null){
            existingStudent.setFirstName(student.getFirstName());
        }
        if (student.getLastName()!= null){
            existingStudent.setLastName(student.getLastName());
        }
        if (student.getBloodGroup()!= null){
            existingStudent.setBloodGroup(student.getBloodGroup());
        }
        if (student.getEmail()!= null){
            existingStudent.setEmail(student.getEmail());
        }
        return studentRepository.save(existingStudent);
    }
}
