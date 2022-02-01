package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EPISODES")
public class Episode extends BaseEntity {
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<TitleSynopsis>titleSynopses;
    String skipIntro;//[12,34]
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<EpisodeSubtitle> subtitles;
    @Pattern(regexp = "((http|https)://)(www.)?" +
            "[a-zA-Z0-9@:%._\\+~#?&///=]" +
            "{2,256}\\.[a-z]" +
            "{2,6}\\b([-a-zA-Z0-9@:%" +
            "._\\+~#?&//=]*)",message = "bad url")
    String url;
    Integer duration;
    Integer endFilm;//Without script
    @Pattern(regexp = "((http|https)://)(www.)?" +
            "[a-zA-Z0-9@:%._\\+~#?&///=]" +
            "{2,256}\\.[a-z]" +
            "{2,6}\\b([-a-zA-Z0-9@:%" +
            "._\\+~#?&//=]*)",message = "bad poster photo")
    String poster;

    @JsonIgnore
    @OneToOne(mappedBy = "episode",cascade = CascadeType.REMOVE)
    Historic historic;
}
