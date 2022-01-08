package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.Episode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends BaseRepository<Episode,Long>{
    @Query(value = "select current_playing_time from historic where profile_id= ?1 and episode_id= ?2 "
            ,nativeQuery = true)
    Long getCurrentPlayingTime( Long idProfile,Long idEpo);
}
