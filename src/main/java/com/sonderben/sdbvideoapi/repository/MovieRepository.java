package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends BaseRepository<Movie,Long>{


     @Query(value = "select * from movies " +
                         "inner join type_access on " +
                         "type_access.id=movies.access_id " +
                         "inner join movies_categories on " +
                         "movies_categories.category_fk=  (select categories.id from movies_categories " +
                                                    "inner join categories on " +
                                                    "categories.id=movies_categories.category_fk " +
                                                    "where categories.code=?1) /**/ " +
                         "where type_access.code<= ( select type_access.code from users " +
                                                   "join type_access on " +
                                                   "type_access.id=users.access_id " +
                                                   "where users.id= (select user_id from profiles where profiles.id=?2) ) " +
                         "And movies.age_category<=(select age_category from profiles where profiles.id=?2) " +
                         "LIMIT 25 OFFSET ?3",nativeQuery = true)
    List<Movie> getMovieByCategory( Long categoryId,
                                       Long profileId,
                                       int pageNumber);

    @Query(value = "select * from movies " +
                        "inner join type_access on " +
                        "type_access.id=movies.access_id " +
                        "where type_access.code<= ( select type_access.code from clients " +
                        "join type_access on " +
                        "type_access.id=clients.access_id " +
                        "where clients.id= (select client_id from profiles where profiles.id=?1) ) " +
                        "AND to_tsvector('french',movies.description) " +
                        "@@ to_tsquery('french',?2) " +
                        "And movies.age_category<=(select age_category from profiles where profiles.id=?1) " +
                        "LIMIT 25 OFFSET ?3",nativeQuery = true)
    List<Movie> getMovieByDescription( Long idProfile,
                                       String description,
                                       int pageNumber);

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
