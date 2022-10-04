package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {

//    SELECT * FROM student WHERE email = ?;
//    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

//    @Query(value = "SELECT * FROM student  WHERE email = ?1", nativeQuery = true)
//    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    List<Student> findStudentsByName(String name);
}
