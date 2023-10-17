package com.examTaker.rjsm.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="teacher")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long teacher_id;

    @Column(name="name", nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;
    @Column(name="last_name", nullable = false, columnDefinition = "VARCHAR(100)")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "profile_created")
    @CreationTimestamp
    private LocalDateTime profileCreated;

    @Column(name = "last_modification")
    @LastModifiedDate
    private Date lastModification;

    //tests field to show tests that belong to this teacher
}
