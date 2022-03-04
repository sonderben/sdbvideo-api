package com.sonderben.sdbvideoapi.dto;


import lombok.Getter;

@Getter
public class HistoricRequest {
    private Long idProfile, idVideo, series, season, duration, currentTime;
    private boolean isMovie;

    private HistoricRequest(Long idProfile, Long idVideo, Long duration, Long currentTime) {
        this.idProfile = idProfile;
        this.idVideo = idVideo;
        this.duration = duration;
        this.currentTime = currentTime;
        this.isMovie = true;
    }

    public static HistoricRequest newHistoricMovieRequest(Long idProfile, Long idVideo, Long duration, Long currentTime) {
        return new HistoricRequest(idProfile, idVideo, duration, currentTime);
    }

    public static HistoricRequest newHistoricSeriesRequest(Long idProfile, Long idVideo, Long series, Long season, Long duration, Long currentTime) {
        return new HistoricRequest(idProfile, idVideo, series, season, duration, currentTime);
    }

    private HistoricRequest(Long idProfile, Long idVideo, Long series, Long season, Long duration, Long currentTime) {
        this.idProfile = idProfile;
        this.idVideo = idVideo;
        this.season = season;
        this.duration = duration;
        this.currentTime = currentTime;
        this.series = series;
        this.isMovie = false;
    }

    @Override
    public String toString() {
        String serie = "HistoricRequest{" +
                "idProfile=" + idProfile +
                ", idVideo=" + idVideo +
                ", series=" + series +
                ", season=" + season +
                ", duration=" + duration +
                ", currentTime=" + currentTime +
                ", isMovie=" + isMovie +
                '}';

        String movie = "HistoricRequest{" +
                "idProfile=" + idProfile +
                ", idVideo=" + idVideo +
                ", duration=" + duration +
                ", currentTime=" + currentTime +
                ", isMovie=" + isMovie +
                '}';
        return isMovie ? movie : serie;
    }
}
