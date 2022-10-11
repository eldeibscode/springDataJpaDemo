package com.example.demo.student;

import javax.persistence.*;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card")
public class StudentIdCard {

    @Id
    @SequenceGenerator(
            name = "student_card_id_seq",
            sequenceName= "student_card_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_card_id_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
}
