package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.Profile;
import lombok.Builder;
import lombok.Getter;

import java.util.Calendar;

@Getter
@Builder
public class MyListDto extends Dto{
    Long id_profile;
    Dto movie;
    Dto serie;
    Calendar dateAdded;
}
