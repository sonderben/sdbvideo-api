package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Table(name = "TITLES_SYNOPSIS_MOVIES")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TitleSynopsisMovie extends BaseEntity implements Serializable {
    @Column(length = 50)
    String title;
    @Column(length = 3) //iso 639-2
    String language;
    String Synopsis;



}
