package com.sonderben.sdbvideoapi.dto;


import lombok.Builder;
import lombok.Getter;



import java.io.Serializable;
import java.util.Calendar;


@Builder
@Getter
public class HistoricDto extends Dto implements Serializable {
    Long id_profile;
    Dto movie;
    int currentPlayingTime;
    Calendar dateLastVisited;
}
