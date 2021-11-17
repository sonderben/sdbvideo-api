package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Builder
@Getter
public class MovieDto extends Dto{
    Long id;
   /* Integer positiveVote;
    Integer negativeVote;
    Integer ageCategory;
    Integer numView;*/
    String access;
    Set<TitleSynopsisMovie> titlesSynopsis;
    float average;
    Calendar availability;
    String urlMovie;
    Calendar releaseDate;
    String posterUrlMovie;
    String trailerUrlMovie;
    Integer duration;
    Set<RewardMovie>rewardList;
    Set<Actor>actorList;
    Set<Category>categoryList;
    Set<MovieSubtitle> movieSubtitleList;
    /*



     */
}
