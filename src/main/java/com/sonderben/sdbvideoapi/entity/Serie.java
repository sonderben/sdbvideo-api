package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sonderben.sdbvideoapi.entity.base.BaseVideo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private Set<TitleSynopsis> titleSynopsises;//***
    @Column(length = 200)
    String description;
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Reward> rewards;//****

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (
            name = "Series_Categories",
            joinColumns = @JoinColumn(name = "serie_fk"),
            inverseJoinColumns = @JoinColumn(name = "category_fk")
    )
    Set<Category> categories;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Season>seasons;

    //////////////////////////////////
    @JsonIgnore
    @OneToOne(mappedBy = "serie",cascade = CascadeType.REMOVE)
    MyList myList;

    @JsonIgnore
    @OneToOne(mappedBy = "movie",cascade = CascadeType.REMOVE)
    Historic historic;




}
