package com.sonderben.sdbvideoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.entity.Serie;
import lombok.*;



@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyListRequestDto extends Dto{
    Long id;
    Profile profile;
    Movie movie;
    Serie serie;
    @JsonProperty("profile")
    private void unpackNested(Long profile){
        Profile profile1=new Profile();
        profile1.setId(profile);
        this.profile=profile1;
    }
}
