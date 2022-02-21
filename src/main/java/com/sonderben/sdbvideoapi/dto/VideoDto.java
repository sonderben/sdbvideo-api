package com.sonderben.sdbvideoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Calendar;
import java.util.List;
@AllArgsConstructor
@Getter
public class VideoDto {
    Long id;
    Long idVideo;
    Boolean isMovie;
    Calendar releaseDate;
    String poster;
    String trailer;
    Integer duration;
    Long currentPlayingTime;
    String description;
    int ageCategory;
    List<String> categories;
    Long idEpisode;

    public void setCurrentPlayingTime(Long currentPlayingTime) {
        this.currentPlayingTime = currentPlayingTime;
    }

}
