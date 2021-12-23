package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "REWARDS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reward extends BaseEntity implements Serializable {


    String name;
    @Temporal(TemporalType.DATE)
    Calendar year;
    String donor;
    String Prize;
    @JsonProperty("year")
    private void unpackNested_(String year){
        this.year= Utile.unpackNestedDate(year);
    }
}
