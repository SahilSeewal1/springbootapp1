package com.example.UserMovieProject.models.tables;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "GENERE_TABLE")
public @Data class Genre {
    @Id
    @Column(name = "GENERE", nullable = false)
    String genere;
}
