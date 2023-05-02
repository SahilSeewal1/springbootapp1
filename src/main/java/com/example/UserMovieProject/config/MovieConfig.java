package com.example.UserMovieProject.config;

import com.example.UserMovieProject.models.tables.Movies;
import com.example.UserMovieProject.repo.MovieRepo;
import com.example.UserMovieProject.utils.MovieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class MovieConfig {
    @Autowired
    MovieRepo movieRepo;

    @Bean
    public MovieUtils getMovieUtils(){
        MovieUtils movieUtils = new MovieUtils();

        movieUtils.movieRatingMapping = new HashMap<>();

        List<Movies> moviesList = movieRepo.findAll();
        for(Movies movie: moviesList) {
            double ratingKey = movie.getAnimation() + movie.getAction()*2 + movie.getAdventure()*Math.pow(2, 2) +
            movie.getAnimation()*Math.pow(2, 3) + movie.getChildren()*Math.pow(2, 4) +
            movie.getComedy()*Math.pow(2, 5) + movie.getCrime()*Math.pow(2, 6) + movie.getDocumentary()*Math.pow(2, 7) +
            movie.getDrama()*Math.pow(2, 8) + movie.getFantasy()*Math.pow(2, 9) + movie.getFilmyNoir()*Math.pow(2, 10) +
            movie.getHorror()*Math.pow(2, 11) + movie.getMusical()*Math.pow(2, 12) + movie.getMystery()*Math.pow(2, 13) +
            movie.getRomance()*Math.pow(2, 14) + movie.getSciFi()*Math.pow(2, 15) + movie.getThriller()*Math.pow(2, 16) +
            movie.getWar()*Math.pow(2, 17) + movie.getWestern()*Math.pow(2, 18);

            if(!movieUtils.movieRatingMapping.containsKey(ratingKey)) {
                movieUtils.movieRatingMapping.put(ratingKey, new ArrayList<>());
            }
            movieUtils.movieRatingMapping.get(ratingKey).add(movie);
        }
        return movieUtils;
    }

}
