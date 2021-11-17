package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.TitleSynopsisMovie;
import com.sonderben.sdbvideoapi.entity.base.BaseVideo;
import lombok.Builder;
import lombok.Getter;

import java.util.Calendar;
import java.util.Set;
@Getter
@Builder
public class SimpleMovieDto extends Dto {

    private Long id;
    private Set<TitleSynopsisMovie> title;
    private Calendar releaseDate;
    private String posterUrlMovie;
    private String trailerUrlMovie;
    private Integer duration;

}
