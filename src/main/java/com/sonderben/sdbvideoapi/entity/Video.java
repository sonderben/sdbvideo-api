package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Video extends BaseEntity {



    Long id_video;
    Boolean isMovie;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<TitleSynopsis> titleSynopses;

/*public Video(Long id, Long id_video, boolean isMovie, Calendar releaseDate, String poster, String trailer, Integer duration
            , Long currentPlayingTime, Plan access, int ageCategory, Set<Category> categories, Episode episode) {
        this.id = id;
        this.id_video = id_video;
        this.isMovie = isMovie;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.trailer = trailer;
        this.duration = duration;
        this.currentPlayingTime = currentPlayingTime;
        this.access = access;
        this.ageCategory = ageCategory;
        this.categories = categories;
        this.episode = episode;

    }*/


    @Temporal(TemporalType.DATE)
    Calendar releaseDate;
    String poster;
    String trailer;
    @Column(nullable = false)
    Integer duration;
    @Transient
    @JsonInclude
    Long currentPlayingTime;
    @Column(length = 300)
    String description;
    @OneToOne
    @NotNull(message = "access can not be null")
    Plan plan;
    int ageCategory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Videos_Categories",
            joinColumns = @JoinColumn(name = "video_fk"),
            inverseJoinColumns = @JoinColumn(name = "category_fk")
    )

    Set<Category> categories;
    @Transient
    @JsonInclude
    Episode episode;

    public void setCurrentPlayingTime(Long currentPlayingTime) {
        this.currentPlayingTime = currentPlayingTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id_video=" + id_video +
                ", isMovie=" + isMovie +
                ", releaseDate=" + releaseDate +
                ", poster='" + poster + '\'' +
                ", trailer='" + trailer + '\'' +
                ", duration=" + duration +
                ", currentPlayingTime=" + currentPlayingTime +
                ", description='" + description + '\'' +
                ", access=" + plan +
                ", ageCategory=" + ageCategory +
                ", categories=" + categories +
                ", episode=" + episode +
                '}';
    }
}
