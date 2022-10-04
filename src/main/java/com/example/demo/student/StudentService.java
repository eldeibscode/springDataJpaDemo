package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {



    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        prinntSomthing();
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll(
                Sort.by(Sort.Direction.ASC, "name")
                        .and(Sort.by(Sort.Direction.ASC, "id"))
        );
    }

    public void prinntSomthing(){
        System.out.println("### test test test ###");
    }
    public void addNewStudent(Student student) {
        System.out.println("___---___");
        System.out.println(student.toString());
        System.out.println("___---___");

        Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            System.out.println("this user is already in db");
            throw new IllegalStateException("email taken!");
        }else {
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            System.out.println("___---___ student not exist!");
            throw new IllegalStateException("student with id :" + studentId + "does not exist!");
        }
        else {
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow( () -> {
                    return new IllegalStateException("student with id:" + studentId + "does not exists");
                });

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken!!");
            }
            student.setEmail(email);
        }


    }

    public void addOrUpdateStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getStudentsByName(String name) {
        System.out.println("##### StudentService:getStudentsByName");
        return studentRepository.findStudentsByName(name);
    }
}
