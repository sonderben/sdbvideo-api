package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Table(name = "TITLES_SYNOPSIS")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TitleSynopsis extends BaseEntity implements Serializable {
    @Column(length = 50)
    String title;
    @Column(length = 3) //iso 639-2
    String language;
    @Column(length = 400)
    String synopsis;

    @Override
    public String toString() {
        return "TitleSynopsis{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", Synopsis='" + synopsis + '\'' +
                '}';
    }
}
