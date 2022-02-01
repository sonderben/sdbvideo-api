package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends BaseRepository<Movie,Long>{
//https://pganalyze.com/blog/full-text-search-django-postgres

     @Query(value = "select * from movies " +
             "       inner join type_access on " +
             "       type_access.id=movies.access_id " +
             "       inner join movies_categories on " +
             "       movies_categories.movie_fk=  movies.id " +
             "       where movies_categories.category_fk= (select categories.id from categories" +
             "                                              where categories.code=?1) " +
             "       AND type_access.code<= ( select type_access.code from clients " +
             "                                join type_access on " +
             "                                type_access.id=clients.access_id " +
             "                                where clients.id= (select client_id from profiles where profiles.id=?2) ) " +
             "       And movies.age_category<=(select age_category from profiles where profiles.id=?2) " +
             "       LIMIT 25 OFFSET ?3",nativeQuery = true)
    List<Movie> getMovieByCategory( Long categoryCode,
                                       Long profileId,
                                       int pageNumber);

   /* @Query(value = "select * from movies " +
                        "inner join type_access on " +
                        "type_access.id=movies.access_id " +
                        "where type_access.code <= ( select type_access.code from clients " +
                        "join type_access on " +
                        "type_access.id=clients.access_id " +
                        "where clients.id= (select client_id from profiles where profiles.id=?1) ) " +
                        "AND to_tsvector('french',movies.description) " +
                        "@@ to_tsquery('french',?2) " +
                        "And movies.age_category<=(select age_category from profiles where profiles.id=?1) " +
                        "LIMIT 25 OFFSET ?3",nativeQuery = true)
    List<Movie> getMovieByDescription_( Long idProfile,
                                       String description,
                                       int pageNumber);*/




    @Query(value = " select " +
            "ts_rank( to_tsvector( (?4)\\:\\:regconfig,movies.description), to_tsquery( (?4)\\:\\:regconfig,?2) ) as rank_, " +
            "  movies.id,movies.age_category,movies.negative_vote,movies.num_view,movies.positive_vote," +
            "  movies.availability,movies.description,movies.duration,movies.poster, " +
            "  movies.release_date,movies.trailer,movies.url,movies.access_id from movies " +
            "  inner join type_access on " +
            "  type_access.id=movies.access_id " +
            "  where type_access.code <= ( select type_access.code from clients " +
            "                            join type_access on " +
            "                            type_access.id=clients.access_id  " +
            "                            where clients.id= (select client_id from profiles where profiles.id=?1) ) " +
            "                           AND to_tsvector( (?4)\\:\\:regconfig,movies.description) " +
            "                           @@ to_tsquery( (?4)\\:\\:regconfig,?2) " +
            "                        And movies.age_category<=(select age_category from profiles where profiles.id=?1) " +
            "                       order by rank_ desc LIMIT 25 OFFSET ?3 ",nativeQuery = true)
    List<Movie>getMovieByDescription( Long idProfile,
                                      String description,
                                      int pageNumber,String language);











    @Query(value = "select * from movies " +
                        "inner join type_access on " +
                        "type_access.id=movies.access_id " +
                        "where type_access.code<= ( select type_access.code from clients " +
                        "join type_access on " +
                        "type_access.id=clients.access_id " +
                        "where clients.id= (select client_id from profiles where profiles.id=?1) ) " +
                        "And movies.age_category<=(select age_category from profiles where profiles.id=?1) " +
                        "LIMIT 25 OFFSET ?2",nativeQuery = true)
    List<Movie> getAllMovies(Long idProfile, int pageNumber);
    @Query(value = "select * from movies " +
                        "inner join type_access on " +
                        "type_access.id=movies.access_id " +
                        "where type_access.code<= ( select type_access.code from clients " +
                        "join type_access on " +
                        "type_access.id=clients.access_id " +
                        "where clients.id= (select client_id from profiles where profiles.id=?2) ) " +
                        "AND movies.id=?1 " +
                        "And movies.age_category<=(select age_category from profiles where profiles.id=?2) "
                        ,nativeQuery = true)
    Movie getMovieById( Long idMovie,Long idProfile);

    @Query(value = "select * from movies " +
            "inner join type_access on " +
            "type_access.id=movies.access_id " +
            "where type_access.code<= ( select type_access.code from clients " +
            "join type_access on " +
            "type_access.id=clients.access_id " +
            "where clients.id= (select client_id from profiles where profiles.id=?1) " +
            "And movies.age_category<=(select age_category from profiles where profiles.id=?1) " +
            "AND to_tsvector('french',movies.description) " +
            "@@ to_tsquery('french',?3)" +
            "LIMIT 25 OFFSET ?2",nativeQuery = true)
    List<Movie> getAllMoviesByDescription2(int UserAgeProfile, int pageNumber,String description);

    @Query(value = "select current_playing_time from historic where profile_id= ?1 and movie_id= ?2 "
            ,nativeQuery = true)
    Long getCurrentPlayingTime( Long idProfile,Long idMovie);



}
