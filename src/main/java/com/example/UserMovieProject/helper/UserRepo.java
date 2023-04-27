package com.example.UserMovieProject.helper;

import com.example.UserMovieProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
