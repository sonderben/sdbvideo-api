package com.sonderben.sdbvideoapi.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.entity.TypeAccess;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
@Table(indexes = {@Index(name = "base_historic_idx",columnList = "profile")})
public class BaseHistoric extends BaseEntity implements Serializable {

    @OneToOne//(fetch = FetchType.LAZY)
            @JsonIgnore
    Profile profile;
    @OneToOne
    Movie movie;


}