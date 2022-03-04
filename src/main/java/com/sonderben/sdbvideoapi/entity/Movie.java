package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sonderben.sdbvideoapi.entity.base.BaseVideo;
import com.sonderben.sdbvideoapi.entity.base.Subtitle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "MOVIES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class Movie extends BaseVideo implements Serializable {

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<TitleSynopsis> titleSynopses;

    @Temporal(TemporalType.TIMESTAMP)
    Calendar availability;
    @Column(nullable = false)
    String url;
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Reward> rewards;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable (
            name = "movies_Actors",
            joinColumns = @JoinColumn(name = "movie_fk"),
            inverseJoinColumns = @JoinColumn(name = "actor_fk")
    )
    Set<Actor>actors;
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Subtitle> subtitles;



    @Transient
    @JsonInclude
    String description;


    @Transient
    @JsonInclude
    private Set<Category> categories;


    @Transient
    @JsonInclude
    private int ageCategory;
    @Transient
    @JsonInclude
    private Calendar releaseDate;
    @Transient
    @JsonInclude
    private String poster;
    @Transient
    @JsonInclude
    private String trailer;
    @Transient
    @JsonInclude
    Integer duration;

    @Transient
    @JsonInclude
    Plan plan;

    @Override
    public String toString() {
        return "Movie{" +
                "titleSynopses=" + titleSynopses +
                ", availability=" + availability +
                ", url='" + url + '\'' +
                ", rewards=" + rewards +
                ", actors=" + actors +
                ", subtitles=" + subtitles +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", ageCategory=" + ageCategory +
                ", releaseDate=" + releaseDate +
                ", poster='" + poster + '\'' +
                ", trailer='" + trailer + '\'' +
                ", duration=" + duration +
                '}';
    }
}
/*  @JsonProperty("releaseDate")
    private void unpackNested(String releaseDate){
        Utile.unpackNested(releaseDate,this.releaseDate);
    }
    @JsonProperty("availability")////pako fini dwe timestamp
    private void unpackNested2(String availability){
        Utile.unpackNested(availability,this.availability);
    }
*/