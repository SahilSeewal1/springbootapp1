package com.example.UserMovieProject.models.tables;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TABLE")
public @Data class User{
    @Id @Column(name = "USER_ID")
    int userId;
    @Column(name = "AGE", nullable = false)
    int age;
    @Column(name = "GENDER", nullable = false)
    String gender;
    @Column(name = "OCCUPATION", nullable = false)
    String occupation;
    @Column(name = "ZIP", nullable = false)
    String zip;
}
