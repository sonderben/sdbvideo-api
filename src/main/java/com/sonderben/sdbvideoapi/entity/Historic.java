package com.sonderben.sdbvideoapi.entity;

import com.sonderben.sdbvideoapi.entity.base.BaseHistoric;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Data
@Entity
public class Historic extends BaseHistoric implements Serializable {
    @OneToOne
    Episode episode;
    @Column(nullable = false)
    Integer currentPlayingTime;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Calendar dateLastVisited;



}
