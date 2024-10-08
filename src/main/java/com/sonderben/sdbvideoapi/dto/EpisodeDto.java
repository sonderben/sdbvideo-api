package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.TitleSynopsis;
import com.sonderben.sdbvideoapi.entity.base.Subtitle;
import lombok.Builder;
import lombok.Getter;



import java.util.Set;
@Builder
@Getter
public class EpisodeDto extends Dto {
    Long id;
    Set<TitleSynopsis> titleSynopses;
    Set<Subtitle> subtitles;
    String url;
    Integer duration;
    String poster;

    public void setCurrentPlayingTime(Long currentPlayingTime) {
        this.currentPlayingTime = currentPlayingTime;
    }

    Long currentPlayingTime;

}
