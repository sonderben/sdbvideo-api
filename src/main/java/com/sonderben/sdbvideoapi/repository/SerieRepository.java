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
            "where clients.id= (select client_id from profiles where profiles.id=?1) ) " +
            "AND to_tsvector('french',series.description) " +
            "@@ to_tsquery('french',?2) " +
            "And series.age_category<=(select age_category from profiles where profiles.id=?1) " +
            "LIMIT 25 OFFSET ?3",nativeQuery = true)
    List<Serie> getSerieByDescription(Long idProfile,
                                      String description,
                                      int pageNumber);

    @Query(value = "select * from Series " +
            "inner join type_access on " +
            "type_access.id=Series.access_id " +
            "inner join Series_categories on " +
            "Series_categories.category_fk=  (select categories.id from Series_categories " +
            "inner join categories on " +
            "categories.id=Series_categories.category_fk " +
            "where categories.code=?1) /**/ " +
            "where type_access.code<= ( select type_access.code from Series " +
            "join type_access on " +
            "type_access.id=Series.access_id " +
            "where Series.id= (select Series_id from profiles where profiles.id=?2) ) " +
            "And Series.age_category<=(select age_category from profiles where profiles.id=?2) " +
            "LIMIT 25 OFFSET ?3",nativeQuery = true)
    List<Serie> getSerieByCategory( Long categoryId,
                                    Long profileId,
                                    int pageNumber);

    @Query(value = "select * from series " +
            "inner join type_access on " +
            "type_access.id=series.access_id " +
            "where type_access.code<= ( select type_access.code from clients " +
            "join type_access on " +
            "type_access.id=clients.access_id " +
            "where clients.id= (select client_id from profiles where profiles.id=?1) ) " +
            "And movies.age_category<=(select age_category from profiles where profiles.id=?1) " +
            "LIMIT 25 OFFSET ?2",nativeQuery = true)
    List<Serie> getAllSeries(Long idProfile, int pageNumber);
}
