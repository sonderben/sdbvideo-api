package com.sonderben.sdbvideoapi.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.entity.Serie;
import com.sonderben.sdbvideoapi.entity.TypeAccess;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
@Table(indexes = {@Index(name = "base_historic_idx", columnList = "profile")})
public class BaseHistoric extends BaseEntity implements Serializable {

    @OneToOne//(fetch = FetchType.LAZY)
    @JsonIgnore
    Profile profile;

    @OneToOne
    Movie movie;

    @JsonProperty(value = "profile",required = true)////
    private void unpackNested2(Long profile){
        Profile profile1=new Profile();
        profile1.setId(profile);
        this.profile=profile1;
    }




}