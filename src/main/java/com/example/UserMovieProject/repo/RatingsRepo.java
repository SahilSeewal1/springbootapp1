package com.example.UserMovieProject.repo;

import com.example.UserMovieProject.models.queryModels.GenreWatchCount;
import com.example.UserMovieProject.models.tables.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingsRepo extends JpaRepository<Ratings, Ratings> {

    @Query(nativeQuery = true, value = "Select ITEM_ID from RATING_TABLE where RATING = 5 group by ITEM_ID order by count(RATING) desc")
    public List<Integer> findItemIdSortedByRating();

    @Query(nativeQuery = true, value = "Select ITEM_ID from RATING_TABLE group by ITEM_ID order by count(ITEM_ID) desc fetch first 1 rows only")
    public int findMostWatchedMovieId();

    @Query(nativeQuery = true, value = "Select Sum(t1.Action) actionCount, Sum(t1.Adventure) adventureCount, Sum(t1.Animation) animationCount, Sum(t1.Children) childrenCount, Sum(t1.Comedy) comedyCount, Sum(t1.Crime) crimeCount, Sum(t1.Documentary) documentaryCount, Sum(t1.Drama) dramaCount, Sum(t1.Fantasy) fantasyCount, Sum(t1.Film_Noir) filmNoirCount, Sum(t1.Horror) horrorCount, Sum(t1.Musical) musicalCount, Sum(t1.Mystery) mysteryCount, Sum(t1.Romance) romanceCount, Sum(t1.Sci_Fi) sciFiCount, Sum(t1.Thriller) thrillerCount, Sum(t1.War) warCount, Sum(t1.Western) westernCount from MOVIES_TABLE t1 inner join RATING_TABLE t2 on t1.MOVIE_ID = t2.ITEM_ID")
    public GenreWatchCount findMostWatchedGenre();

    @Query(nativeQuery = true, value = "Select USER_ID from RATING_TABLE group by USER_ID order by count(USER_ID) desc fetch first 1 rows only")
    public int findMostUserId();

    @Query(nativeQuery = true, value = "Select distinct ITEM_ID from RATING_TABLE where USER_ID = :userId")
    public List<Integer> findMoviesBIdyUserId(@Param("userId") int userId);
}