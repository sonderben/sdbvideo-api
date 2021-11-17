package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.Historic;
import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.entity.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HistoricRepository extends BaseRepository<Historic,Long>{

    @Query(value = "SELECT * FROM historic " +
            "WHERE profile_id = ?1" +
            " ORDER BY date_last_visited DESC " +
            "LIMIT 25 OFFSET ?2",nativeQuery = true)
    List<Historic> findByProfileOrderByDateLastVisited(int idUser,int pageNumber);


}
