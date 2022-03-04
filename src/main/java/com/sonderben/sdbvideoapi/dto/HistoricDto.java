package com.sonderben.sdbvideoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.entity.Serie;
import com.sonderben.sdbvideoapi.entity.aws.Episode;
import lombok.*;

import java.util.Calendar;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricDto   {
    private Long idProfile;
    private Long idVideo;
    private Long dateLastVisited;
    private Long Duration;
    private Long currentPlayingTime;
    private Long idSeason;
    private boolean isMovie;

    @Override
    public String toString() {
        return "HistoricDto{" +
                "idProfile=" + idProfile +
                ", idVideo=" + idVideo +
                ", dateLastVisited=" + dateLastVisited +
                ", Duration=" + Duration +
                ", currentPlayingTime=" + currentPlayingTime +
                ", idSeason=" + idSeason +
                ", isMovie=" + isMovie +
                '}';
    }
}
