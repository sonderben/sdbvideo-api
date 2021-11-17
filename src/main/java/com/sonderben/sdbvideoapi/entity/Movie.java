package com.sonderben.sdbvideoapi.entity;

import com.sonderben.sdbvideoapi.entity.base.BaseVideo;
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

@NamedEntityGraph(name = "simple_movie_graph",attributeNodes = {
        @NamedAttributeNode("title"),
        @NamedAttributeNode("releaseDate")
})
public class Movie extends BaseVideo implements Serializable {

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<TitleSynopsisMovie> title;

    @Temporal(TemporalType.TIMESTAMP)
    Calendar availability;
    //String synopsis;
    @Column(nullable = false)
    String urlMovie;
    @Column(length = 200)
    String description;
    @Temporal(TemporalType.DATE)
    Calendar releaseDate;
    String posterUrlMovie;
    String trailerUrlMovie;
    @Column(nullable = false)
    Integer duration;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<RewardMovie> rewardList;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (
            name = "movies_Actors",
            joinColumns = @JoinColumn(name = "movie_fk"),
            inverseJoinColumns = @JoinColumn(name = "actor_fk")
    )
    Set<Actor>actorList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (
            name = "Movies_Categories",
            joinColumns = @JoinColumn(name = "movie_fk"),
            inverseJoinColumns = @JoinColumn(name = "category_fk")
    )
    Set<Category> categoryList;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<MovieSubtitle> movieSubtitle;
}
