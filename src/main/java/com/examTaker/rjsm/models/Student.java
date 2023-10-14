package com.examTaker.rjsm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long student_id;

    @Column(name="name", nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name="email", nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(name="grade", nullable = false, columnDefinition = "INT")
    private int grade;

}
