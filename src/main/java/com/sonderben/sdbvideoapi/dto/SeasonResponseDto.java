package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.Episode;
import com.sonderben.sdbvideoapi.entity.TitleSynopsis;
import lombok.Builder;
import lombok.Getter;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
@Builder
@Getter
public class SeasonResponseDto extends Dto{
    Long id;
    Calendar release;
    Set<TitleSynopsis> titleSynopseses;
    String poster;
    String trailer;
    Calendar dateAdded;
    Set<EpisodeDto> episodes;


}
