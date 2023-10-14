package com.examTaker.rjsm.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "profilecreated")
    @CreationTimestamp
    private LocalDateTime profileCreated;

    @LastModifiedDate
    private Date lastModification;

}
