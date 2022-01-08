package com.sonderben.sdbvideoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonderben.sdbvideoapi.entity.Episode;
import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.entity.Serie;
import lombok.*;

import java.util.Calendar;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricRequestDto extends Dto {
    Long id;
    Integer currentPlayingTime;
    //Calendar dateLastVisited;
    Profile profile;
    Movie movie;
    Episode episode;
    @JsonProperty("profile")
    private void unpackNested(Long profile){
       Profile profile1=new Profile();
       profile1.setId(profile);
       this.profile=profile1;
    }
}
