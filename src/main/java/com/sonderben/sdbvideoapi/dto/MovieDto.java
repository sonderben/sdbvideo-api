package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Builder
@Getter
public class MovieDto extends Dto {
    Long id;
    String access;
    Set<TitleSynopsis> titlesSynopsis;
    float average;
    Calendar availability;
    String urlMovie;
    Calendar releaseDate;
    String posterUrlMovie;
    String trailerUrlMovie;
    Integer duration;
    Set<Reward> rewardList;
    Set<Actor> actorList;
    Set<Category> categoryList;
    Set<MovieSubtitle> movieSubtitleList;
    Long currentPlayingTime;

    public void setCurrentPlayingTime(Long currentPlayingTime){
        this.currentPlayingTime=currentPlayingTime;
    }


}
