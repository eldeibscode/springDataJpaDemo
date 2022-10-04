package com.example.demo.student;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student alia = new Student(
                    "Alia",
                    "ali@gmail.com",
                    LocalDate.of(2011, Month.MAY, 31)
            );

            Student moh = new Student(
                    "Alia",
                    "aaa@gmail.com",
                    LocalDate.of(1990, Month.MAY, 31)
            );

            studentRepository.saveAll(List.of(alia, moh));

            Faker faker = new Faker();
            for (int i = 0; i < 20; i++) {
                String n = faker.name().firstName();
                String e = String.format("%s@gmail.com", n);
                Student student = new Student(n, e,
                                              LocalDate.of(faker.number().numberBetween(1970, 2011),
                                                           faker.number().numberBetween(1, 12),
                                                           faker.number().numberBetween(1, 30)));
                studentRepository.save(student);
            }
        };
    }
}
