package com.sonderben.sdbvideoapi.entity;


import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PROFILES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Profile extends BaseEntity implements Serializable {
    @Column(length = 20)
    String name;

    @ManyToOne
            @JoinColumn(nullable = false)
    Client client;
    @Column(updatable = false)
    Boolean isMainProfile;
    String urlImg;
    @Column(length = 4)
    String pin;
    @Column(length = 3) //iso 639-2
    String defaultLanguage;
    int ageCategory;

    @ManyToMany
    @JoinTable (
            name = "Profiles_Categories",
            joinColumns = @JoinColumn(name = "profiles_fk"),
            inverseJoinColumns = @JoinColumn(name = "category_fk")
    )
    List<Category> categoryList;
}
