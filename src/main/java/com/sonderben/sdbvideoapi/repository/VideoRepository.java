package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.entity.Video;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VideoRepository extends BaseRepository<Video,Long>{
//https://pganalyze.com/blog/full-text-search-django-postgres

     @Query(value = "select * from video" +
             " inner join plan on" +
             " plan.id=video.plan_ID" +
             " INNER JOIN videos_categories ON" +
             " videos_categories.video_fk=video.id" +
             " WHERE videos_categories.category_fk=( SELECT categories.id FROM categories WHERE categories.code=?1 )" +
             " AND plan.code<= ( SELECT plan.code FROM clients" +
             "                  INNER JOIN plan on" +
             "                  plan.id=clients.plan_id" +
             "                  WHERE clients.id=( SELECT client_id FROM profiles WHERE profiles.id= ?2 ) )" +
             " AND video.age_category<= (SELECT age_category FROM profiles WHERE profiles.id= ?2)" +
             " LIMIT 25 OFFSET ?3",nativeQuery = true)
     List<Video> getVideoByCategory(Long categoryCode,
                                    Long profileId,
                                    int pageNumber);






    @Query(value = " Select ts_rank( to_tsvector( ?4\\:\\:regconfig,video.description), to_tsquery( ?4\\:\\:regconfig,?2) ) as rank_, " +
            "              video.id,video.id_video, video.age_category,video.description,video.duration,video.poster, " +
            "              video.release_date,video.is_movie,video.trailer,video.plan_id from video " +
            "              inner join plan on " +
            "              plan.id=video.plan_id " +
            "              where plan.code <= ( select plan.code from clients " +
            "                                        join plan on " +
            "                                        plan.id=clients.plan_id  " +
            "                                        where clients.id= (select client_id from profiles where profiles.id=?1) ) " +
            "               AND to_tsvector( ?4\\:\\:regconfig,video.description) @@ to_tsquery( ?4\\:\\:regconfig,?2) " +
            "               And video.age_category<=(select age_category from profiles where profiles.id=?1) " +
            "               order by rank_ desc LIMIT 25 OFFSET ?3",nativeQuery = true)
    List<Video>getVideoByDescription( Long idProfile,
                                      String description,
                                      int pageNumber,String language);



    @Query(value = "SELECT video.id from video where video.id_video=?1 AND video.is_movie is ?2",nativeQuery = true)
    Long findIdMovieByIdVideo(Long idVideo,boolean isMovie);









   /* @Query(value = "select * from movies " +
                        "inner join type_access on " +
                        "type_access.id=movies.access_id " +
                        "where type_access.code<= ( select type_access.code from clients " +
                        "join type_access on " +
                        "type_access.id=clients.access_id " +
                        "where clients.id= (select client_id from profiles where profiles.id=?1) ) " +
                        "And movies.age_category<=(select age_category from profiles where profiles.id=?1) " +
                        "LIMIT 25 OFFSET ?2",nativeQuery = true)
    List<Movie> getAllMovies(Long idProfile, int pageNumber);*/
   /* @Query(value = "select * from movies " +
                        "inner join type_access on " +
                        "type_access.id=movies.access_id " +
                        "where type_access.code<= ( select type_access.code from clients " +
                        "join type_access on " +
                        "type_access.id=clients.access_id " +
                        "where clients.id= (select client_id from profiles where profiles.id=?2) ) " +
                        "AND movies.id=?1 " +
                        "And movies.age_category<=(select age_category from profiles where profiles.id=?2) "
                        ,nativeQuery = true)
    Movie getMovieById( Long idMovie,Long idProfile);*/

   /* @Query(value = "select * from movies " +
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
    Long getCurrentPlayingTime( Long idProfile,Long idMovie);*/



}
