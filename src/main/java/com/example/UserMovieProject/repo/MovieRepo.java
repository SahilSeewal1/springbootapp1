package com.example.UserMovieProject.repo;

import com.example.UserMovieProject.models.tables.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movies, Integer> {

    @Query(nativeQuery = true, value = "select * from MOVIES_TABLE WHERE MOVIE_ID IN (:movieIdList)")
    public List<Movies>findMovieListFromItemIdList(@Param("movieIdList") List<Integer> movieIdList);
}
