package com.sonderben.sdbvideoapi.dto;


import lombok.Builder;
import lombok.Getter;



import java.io.Serializable;
import java.util.Calendar;


@Builder
@Getter
public class HistoricDto extends Dto implements Serializable {
    Long id;
    Long id_profile;
    Dto movie;
    Dto serie;
    int currentPlayingTime;
    Calendar dateLastVisited;
}
