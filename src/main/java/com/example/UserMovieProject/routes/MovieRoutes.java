package com.example.UserMovieProject.routes;

import com.example.UserMovieProject.helper.UserRepo;
import com.example.UserMovieProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies-users-data")
public class MovieRoutes {

    @Autowired
    UserRepo user;
    @GetMapping("/getMovies")
    public String getMovieInfo(){
        return "Hello!!";
    }

    @GetMapping("/getUser")
    public String getUserInfo(){
        List<User> s = user.findAll();

        return s.toString();
    }
}
