package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.*;
import com.sonderben.sdbvideoapi.entity.base.Subtitle;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Builder
@Getter
public class MovieDto extends Dto {
    Long id;
    Set<TitleSynopsis> titleSynopses;
    Calendar availability;
    String url;
    Set<Reward> rewards;
    Set<Actor>actors;
    Set<Subtitle> subtitles;


}
