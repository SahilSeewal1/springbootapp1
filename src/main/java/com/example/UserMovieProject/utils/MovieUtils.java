package com.example.UserMovieProject.utils;

import com.example.UserMovieProject.models.tables.Movies;
import com.example.UserMovieProject.repo.MovieRepo;
import com.example.UserMovieProject.repo.RatingsRepo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class MovieUtils {

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    RatingsRepo ratingsRepo;

    public Map<Double, List<Movies>> movieRatingMapping;
    public List<Movies> findSortedMovieDetailList() {
        List<Movies> sortedMovieDetailList = new ArrayList<>();

        List<Integer> sortedMovieIdList = ratingsRepo.findItemIdSortedByRating();
        List<List<Integer>> movieList = Lists.partition(sortedMovieIdList, 950);

        for (List<Integer> movieSubList : movieList) {
            List<Movies> tempList = movieRepo.findMovieListFromItemIdList(movieSubList);
            tempList.stream().map(it -> it.getMovieId()).sorted(Comparator.comparingInt(movieSubList::indexOf));
            sortedMovieDetailList.addAll(tempList);
        }
        return sortedMovieDetailList;
    }

    public List<String> findGenreList(Movies movie) {
        List<String> genreList = new ArrayList<>();

        if (movie.getAction() == 1) {
            genreList.add("Action");
        }

        if (movie.getAdventure() == 1) {
            genreList.add("Adventure");
        }

        if (movie.getAnimation() == 1) {
            genreList.add("Animation");
        }

        if (movie.getChildren() == 1) {
            genreList.add("Children");
        }

        if (movie.getComedy() == 1) {
            genreList.add("Comedy");
        }

        if (movie.getCrime() == 1) {
            genreList.add("Crime");
        }

        if (movie.getDocumentary() == 1) {
            genreList.add("Documentary");
        }

        if (movie.getDrama() == 1) {
            genreList.add("Drama");
        }

        if (movie.getFantasy() == 1) {
            genreList.add("Fantasy");
        }

        if (movie.getFilmyNoir() == 1) {
            genreList.add("Film_Noir");
        }

        if (movie.getHorror() == 1) {
            genreList.add("Horror");
        }

        if (movie.getMusical() == 1) {
            genreList.add("Musical");
        }

        if (movie.getMystery() == 1) {
            genreList.add("Mystery");
        }

        if (movie.getRomance() == 1) {
            genreList.add("Romance");
        }

        if (movie.getSciFi() == 1) {
            genreList.add("Sci_Fi");
        }

        if (movie.getThriller() == 1) {
            genreList.add("Thriller");
        }

        if (movie.getWar() == 1) {
            genreList.add("War");
        }

        if (movie.getWestern() == 1) {
            genreList.add("Western");
        }
        return genreList;
    }

    public Map<Double, List<Movies>> findMovieRatingKey(List<Movies> moviesList) {
        Map<Double, List<Movies>> movieRatingMapping = new HashMap<>();

        for(Movies movie: moviesList) {
            double ratingKey = movie.getAnimation()*1 + movie.getAction()*2 + movie.getAdventure()*Math.pow(2, 2) +
                    movie.getAnimation()*Math.pow(2, 3) + movie.getChildren()*Math.pow(2, 4) +
                    movie.getComedy()*Math.pow(2, 5) + movie.getCrime()*Math.pow(2, 6) + movie.getDocumentary()*Math.pow(2, 7) +
                    movie.getDrama()*Math.pow(2, 8) + movie.getFantasy()*Math.pow(2, 9) + movie.getFilmyNoir()*Math.pow(2, 10) +
                    movie.getHorror()*Math.pow(2, 11) + movie.getMusical()*Math.pow(2, 12) + movie.getMystery()*Math.pow(2, 13) +
                    movie.getRomance()*Math.pow(2, 14) + movie.getSciFi()*Math.pow(2, 15) + movie.getThriller()*Math.pow(2, 16) +
                    movie.getWar()*Math.pow(2, 17) + movie.getWestern()*Math.pow(2, 18);

            if(!movieRatingMapping.containsKey(ratingKey)) {
                movieRatingMapping.put(ratingKey, new ArrayList<>());
            }
            movieRatingMapping.get(ratingKey).add(movie);
        }
        return movieRatingMapping;
    }

        public Map<Double,List<Movies>> findOtherRecommendedMovies(Map<Double, List<Movies>> movieRatingMap) {
        ArrayList<Double> movieRatingKey = new ArrayList<>();
        movieRatingKey.addAll(movieRatingMapping.keySet());

        Collections.sort(movieRatingKey);
        Map<Double,List<Movies>> newMovieRatingMap = new HashMap<>();
        for(Double key: movieRatingMap.keySet()){
            int idx = movieRatingKey.indexOf(key);
            if(idx-1>=0) {
                newMovieRatingMap.put(movieRatingKey.get(idx-1), movieRatingMapping.get(movieRatingKey.get(idx-1)));
            };
            if(idx+1<movieRatingKey.size()) {
                newMovieRatingMap.put(movieRatingKey.get(idx+1), movieRatingMapping.get(movieRatingKey.get(idx+1)));
            }
        }
        return newMovieRatingMap;
    }

    public List<Movies> findRecommendedMovieList(Map<Double, List<Movies>> movieRatingMap) {

        List<Movies> recommendedMovies = new ArrayList<Movies>();

        for(Map.Entry<Double, List<Movies>> entry :movieRatingMap.entrySet()){
            for(Movies movie: movieRatingMapping.get(entry.getKey())){
                if(!entry.getValue().contains(movie)){
                    recommendedMovies.add(movie);
                }
            }
        }
        return recommendedMovies;
    }
}
