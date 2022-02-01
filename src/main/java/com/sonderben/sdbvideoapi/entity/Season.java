package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SEASONS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Season extends BaseEntity {
    @Temporal(TemporalType.DATE)
    Calendar release;
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<TitleSynopsis>titleSynopseses;
    String poster;
    String trailer;
    Calendar dateAdded;
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Episode> episodes;

    @JsonProperty("release")
    private void unpackNested(String release){
        this.release=Utile.unpackNestedDate(release);
    }
    @JsonProperty("dateAdded")
    private void unpackNested_(String dateAdded){
        this.dateAdded=Utile.unpackNestedDate(dateAdded);
        //System.err.println("date added");
    }

}
