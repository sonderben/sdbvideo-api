package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Calendar;
import java.util.Set;

@Getter
@Builder
@Entity
public class Video extends BaseEntity {

    //other version
/*

    private Long id;
    private Long id_video;
    private boolean isMovie;
    private Set<TitleSynopsis> title;
    private Calendar releaseDate;
    private String poster;
    private String trailer;
    private Integer duration;
    @Transient
    @JsonInclude
    private Long currentPlayingTime;
    private String description;
    private TypeAccess access;
    private int ageCategory;
    private Set<Category> categories;
    @Transient
    @JsonInclude
    private Episode episode;

    public void setCurrentPlayingTime(Long currentPlayingTime){
        this.currentPlayingTime=currentPlayingTime;
    }
*/

}
