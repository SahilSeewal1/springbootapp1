package com.example.UserMovieProject.models.tables;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIES_TABLE")
public @Data class Movies {
    @Id
    @Column(name = "MOVIE_ID", nullable = false)
    int movieId;
    @Column(name = "MOVIE_TITLE", nullable = false)
    String movieTitle;
    @Column(name = "RELEASE_DATE", nullable = false)
    String releaseDate;
    @Column(name = "VIDEO_RELEASE_DATE", nullable = false)
    String videoReleaseDate;
    @Column(name = "IMDB_URL", nullable = false)
    String imdbUrl;
    @Column(name = "UNKNOWN", nullable = false)
    int unknown;
    @Column(name = "ACTION", nullable = false)
    int action;
    @Column(name = "ADVENTURE", nullable = false)
    int adventure;
    @Column(name = "ANIMATION", nullable = false)
    int animation;
    @Column(name = "CHILDREN", nullable = false)
    int children;
    @Column(name = "COMEDY", nullable = false)
    int comedy;
    @Column(name = "CRIME", nullable = false)
    int crime;
    @Column(name = "DOCUMENTARY", nullable = false)
    int documentary;
    @Column(name = "DRAMA", nullable = false)
    int drama;
    @Column(name = "FANTASY", nullable = false)
    int fantasy;
    @Column(name = "FILM_NOIR", nullable = false)
    int filmyNoir;
    @Column(name = "HORROR", nullable = false)
    int horror;
    @Column(name = "MUSICAL", nullable = false)
    int musical;
    @Column(name = "MYSTERY", nullable = false)
    int mystery;
    @Column(name = "ROMANCE", nullable = false)
    int romance;
    @Column(name = "SCI_FI", nullable = false)
    int sciFi;
    @Column(name = "THRILLER", nullable = false)
    int thriller;
    @Column(name = "WAR", nullable = false)
    int war;
    @Column(name = "WESTERN", nullable = false)
    int western;
}
