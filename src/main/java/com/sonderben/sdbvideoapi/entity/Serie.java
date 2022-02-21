package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sonderben.sdbvideoapi.entity.base.BaseVideo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SERIES")
public class Serie extends BaseVideo {



    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Reward> rewards;//****



    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Season>seasons;

    //////////////////////////////////



    ///////////tr
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
    private Set<TitleSynopsis> titleSynopses;
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


}
