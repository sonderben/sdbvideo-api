package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.Historic;
import com.sonderben.sdbvideoapi.entity.MyList;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyListRepository extends BaseRepository<MyList,Long> {
    @Query(value = "SELECT * FROM my_list " +
            "WHERE profile_id = ?1" +
            " ORDER BY date_added DESC " +
            "LIMIT 25 OFFSET ?2",nativeQuery = true)
    List<MyList> findByIdProfileOrderByDateAdded(int idUser, int pageNumber);
}
