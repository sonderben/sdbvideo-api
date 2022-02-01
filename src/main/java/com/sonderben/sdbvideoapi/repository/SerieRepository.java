package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.entity.Serie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends BaseRepository<Serie,Long> {

    @Query(value = "select * from series " +
            "inner join type_access on " +
            "type_access.id=series.access_id " +
            "where type_access.code<= ( select type_access.code from clients " +
            "join type_access on " +
            "type_access.id=clients.access_id " +
            "where clients.id= (select client_id from profiles where profiles.id=?2) ) " +
            "AND to_tsvector('french',series.description) " +
            "@@ to_tsquery('french',?1) " +
            "And series.age_category<=(select age_category from profiles where profiles.id=?2) " +
            "LIMIT 25 OFFSET ?3",nativeQuery = true)
    List<Serie> getSerieByDescription(
                                      String description,Long idProfile,
                                      int pageNumber);

    @Query(value = "select * from series " +
            " inner join type_access on " +
            " type_access.id=series.access_id" +
            " inner join series_categories on" +
            " series_categories.category_fk=  (select categories.id from series_categories" +
            "                            inner join categories on" +
            "                            categories.id=series_categories.category_fk" +
            "                            where categories.code=?1) " +
            " where type_access.code<= ( select type_access.code from clients" +
            "                           join type_access on" +
            "                           type_access.id=clients.access_id" +
            "                           where clients.id= (select client_id from profiles where profiles.id=4) ) " +
            " And series.age_category<=(select age_category from profiles where profiles.id=?2)" +
            " LIMIT 25 OFFSET ?3",nativeQuery = true)
    List<Serie> getSerieByCategory( int categoryCode,
                                    Long profileId,
                                    int pageNumber);

    @Query(value = "select * from series " +
            "inner join type_access on " +
            "type_access.id=series.access_id " +
            "where type_access.code<= ( select type_access.code from clients " +
            "join type_access on " +
            "type_access.id=clients.access_id " +
            "where clients.id= (select client_id from profiles where profiles.id=?1) ) " +
            "And series.age_category<=(select age_category from profiles where profiles.id=?1) " +
            "LIMIT 25 OFFSET ?2",nativeQuery = true)
    List<Serie> getAllSeries(Long idProfile, int pageNumber);

}
