package com.example.UserMovieProject.repo;

import com.example.UserMovieProject.models.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
