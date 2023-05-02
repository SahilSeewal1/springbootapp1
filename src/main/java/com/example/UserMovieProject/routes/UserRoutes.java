package com.example.UserMovieProject.routes;

import com.example.UserMovieProject.models.tables.Movies;
import com.example.UserMovieProject.models.tables.User;
import com.example.UserMovieProject.repo.MovieRepo;
import com.example.UserMovieProject.repo.RatingsRepo;
import com.example.UserMovieProject.repo.UserRepo;
import com.example.UserMovieProject.utils.MovieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users-data")
public class UserRoutes {

    @Autowired
    RatingsRepo ratingsRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    MovieRepo movieRepo;
    @Autowired
    MovieUtils movieUtils;

    @GetMapping("/mostActiveUser")
    // Method returns most active user
    public Optional<User> getMostActiveUser(){
        return userRepo.findById(ratingsRepo.findMostUserId());
    }

    @GetMapping("/recommendMoviesToUser")
    // Method returns recommendations for movies to user based on their interest
    public List<Movies> getMovieRecommendations(@RequestParam(value = "userId") int userId){
        List<Movies> moviesList = new ArrayList<Movies>();
        List<Integer> moviesIdList = ratingsRepo.findMoviesBIdyUserId(userId);

        for(int movieId: moviesIdList){
            Movies movie = movieRepo.findById(movieId).orElse(null);
            if(movie != null)
                moviesList.add(movie);
        }

        Map<Double, List<Movies>> movieRatingMap = movieUtils.findMovieRatingKey(moviesList);

        List<Movies> recommendedMovies = movieUtils.findRecommendedMovieList(movieRatingMap);

        int attempts = 1;

        while (recommendedMovies.isEmpty() && attempts <= 10){
            Map<Double,List<Movies>> newMovieRatingMap = movieUtils.findOtherRecommendedMovies(movieRatingMap);
            recommendedMovies = movieUtils.findRecommendedMovieList(newMovieRatingMap);
            movieRatingMap = newMovieRatingMap;
            attempts++;
        }

        if(recommendedMovies.size() > 5){
            return recommendedMovies.subList(0, 6);
        }
        return recommendedMovies;
    }
}
