package com.example.UserMovieProject.routes;

import com.example.UserMovieProject.models.queryModels.GenreWatchCount;
import com.example.UserMovieProject.models.queryModels.GenreYear;
import com.example.UserMovieProject.models.tables.Movies;
import com.example.UserMovieProject.repo.GenreRepo;
import com.example.UserMovieProject.repo.MovieRepo;
import com.example.UserMovieProject.repo.RatingsRepo;
import com.example.UserMovieProject.utils.MovieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies-data")
public class MovieRoutes {
    @Autowired
    GenreRepo genreRepo;

    @Autowired
    MovieUtils movieUtils;

    @Autowired
    RatingsRepo ratingsRepo;

    @Autowired
    MovieRepo movieRepo;

    @GetMapping("/topMoviesByGenre")
    // Method returns best movie by genre
    public HashMap<String, String> getTopMovieByGenre(){

        // Get list of movie objects sorted by the movies mostly rated 5 in descending order
        List<Movies> sortedMovieDetailList = movieUtils.findSortedMovieDetailList();

        // Genre list
        List<String> genreMap = genreRepo.findAll().stream().map(i->i.getGenere()).collect(Collectors.toList());
        HashMap<String, String> topMovieFromGenre = new HashMap<>();

        // Assign genre the movie that was mostly rated 5
        genreMap.forEach(it->{topMovieFromGenre.put(it, "");});
        sortedMovieDetailList.forEach(movie->{

            // Fetch list of genre belonging to this movie
            List<String> genreList = movieUtils.findGenreList(movie);
            for(String genre: genreList) {
                if(topMovieFromGenre.get(genre).equals("")) {
                    topMovieFromGenre.put(genre, movie.getMovieTitle());
                }
            }
        });

        return topMovieFromGenre;
    }

    @GetMapping("/topMoviesByYear")
    // Method returns best movie by year
    public HashMap<String, String> getTopMovieByYear(){

        // Get list of movie objects sorted by the movies mostly rated 5 in descending order
        List<Movies> sortedMovieDetailList = movieUtils.findSortedMovieDetailList();

        HashMap<String, String> sortedYearMovieMap = new HashMap<>();

        for(Movies movie: sortedMovieDetailList){
            if(!sortedYearMovieMap.containsKey(movie.getReleaseDate().substring(movie.getReleaseDate().length()-4))){
                sortedYearMovieMap.put(movie.getReleaseDate().substring(movie.getReleaseDate().length()-4), movie.getMovieTitle());
            }
        }
        return sortedYearMovieMap;
    }

    @GetMapping("/topMoviesByYearAndGenre")
    // Method returns best movie by year and genre
    public HashMap<GenreYear, String> getTopMovieByYearAndGenre() {

        // Get list of movie objects sorted by the movies mostly rated 5 in descending order
        List<Movies> sortedMovieDetailList = movieUtils.findSortedMovieDetailList();

        HashMap<GenreYear, String> sortedGenreYearMovieMap = new HashMap<>();

        for(Movies movie: sortedMovieDetailList) {

            String year = movie.getReleaseDate().substring(movie.getReleaseDate().length() - 4);

            // Fetch list of genre belonging to this movie
            List<String> genreList = movieUtils.findGenreList(movie);

            for (String genere : genreList) {
                GenreYear gy = new GenreYear(genere, year);

                if (!sortedGenreYearMovieMap.containsKey(gy)) {
                    sortedGenreYearMovieMap.put(gy, movie.getMovieTitle());
                }
            }
        }
        return sortedGenreYearMovieMap;
    }

    @GetMapping("/mostWatchedMovie")
    // Method returns most watched movie
    public String getMostWatchedMovie(){
        return movieRepo.findById(ratingsRepo.findMostWatchedMovieId()).orElse(new Movies()).getMovieTitle();
    }

    @GetMapping("/mostWatchedGenre")
    // Method returns most watched genre
    public String getMostWatchedGenre(){
        GenreWatchCount genreCounts = ratingsRepo.findMostWatchedGenre();
        int maxGenre = 0;
        String genre = "NA";
        if(maxGenre < genreCounts.getActionCount()){
            maxGenre = genreCounts.getActionCount();
            genre = "Action";
        }

        if(maxGenre < genreCounts.getAdventureCount()){
            maxGenre = genreCounts.getAdventureCount();
            genre = "Adventure";
        }

        if(maxGenre < genreCounts.getAnimationCount()){
            maxGenre = genreCounts.getAnimationCount();
            genre = "Animation";
        }

        if(maxGenre < genreCounts.getChildrenCount()){
            maxGenre = genreCounts.getChildrenCount();
            genre = "Children";
        }

        if(maxGenre < genreCounts.getComedyCount()){
            maxGenre = genreCounts.getComedyCount();
            genre = "Comedy";
        }

        if(maxGenre < genreCounts.getCrimeCount()){
            maxGenre = genreCounts.getCrimeCount();
            genre = "Crime";
        }

        if(maxGenre < genreCounts.getDocumentaryCount()){
            maxGenre = genreCounts.getDocumentaryCount();
            genre = "Documentary";
        }

        if(maxGenre < genreCounts.getDramaCount()){
            maxGenre = genreCounts.getDramaCount();
            genre = "Drama";
        }

        if(maxGenre < genreCounts.getFantasyCount()){
            maxGenre = genreCounts.getFantasyCount();
            genre = "Fantasy";
        }

        if(maxGenre < genreCounts.getFilmNoirCount()){
            maxGenre = genreCounts.getFilmNoirCount();
            genre = "Film_Noir";
        }

        if(maxGenre < genreCounts.getHorrorCount()){
            maxGenre = genreCounts.getHorrorCount();
            genre = "Horror";
        }

        if(maxGenre < genreCounts.getMusicalCount()){
            maxGenre = genreCounts.getMusicalCount();
            genre = "Musical";
        }

        if(maxGenre < genreCounts.getMysteryCount()){
            maxGenre = genreCounts.getMysteryCount();
            genre = "Mystery";
        }

        if(maxGenre < genreCounts.getRomanceCount()){
            maxGenre = genreCounts.getRomanceCount();
            genre = "Romance";
        }

        if(maxGenre < genreCounts.getSciFiCount()){
            maxGenre = genreCounts.getSciFiCount();
            genre = "Sci_Fi";
        }

        if(maxGenre < genreCounts.getThrillerCount()){
            maxGenre = genreCounts.getThrillerCount();
            genre = "Thriller";
        }

        if(maxGenre < genreCounts.getWarCount()){
            maxGenre = genreCounts.getWarCount();
            genre = "War";
        }

        if(maxGenre < genreCounts.getWesternCount()){
            maxGenre = genreCounts.getWesternCount();
            genre = "Western";
        }
        return genre;
    }
}
