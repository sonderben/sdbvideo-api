package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.entity.base.BaseVideo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "MOVIES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@NamedEntityGraph(name = "simple_movie_graph",attributeNodes = {
        @NamedAttributeNode("titleSynopsises"),
        @NamedAttributeNode("releaseDate")
})
public class Movie extends BaseVideo implements Serializable {

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<TitleSynopsis> titleSynopsises;

    @Temporal(TemporalType.TIMESTAMP)
    Calendar availability;
    @Column(nullable = false)
    String url;
    @Column(length = 200)
    String description;
    @Temporal(TemporalType.DATE)
    Calendar releaseDate;
    String poster;
    String trailer;
    @Column(nullable = false)
    Integer duration;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Reward> rewards;

  /*  @JsonProperty("releaseDate")
    private void unpackNested(String releaseDate){
        Utile.unpackNested(releaseDate,this.releaseDate);
    }
    @JsonProperty("availability")////pako fini dwe timestamp
    private void unpackNested2(String availability){
        Utile.unpackNested(availability,this.availability);
    }
*/
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable (
            name = "movies_Actors",
            joinColumns = @JoinColumn(name = "movie_fk"),
            inverseJoinColumns = @JoinColumn(name = "actor_fk")
    )
    Set<Actor>actors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (
            name = "Movies_Categories",
            joinColumns = @JoinColumn(name = "movie_fk"),
            inverseJoinColumns = @JoinColumn(name = "category_fk")
    )
    Set<Category> categories;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<MovieSubtitle> movieSubtitles;
}
