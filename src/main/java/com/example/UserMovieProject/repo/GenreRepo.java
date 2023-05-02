package com.example.UserMovieProject.repo;

import com.example.UserMovieProject.models.tables.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, String> {
}
