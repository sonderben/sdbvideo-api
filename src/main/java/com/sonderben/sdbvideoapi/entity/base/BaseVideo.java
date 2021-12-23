package com.sonderben.sdbvideoapi.entity.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonderben.sdbvideoapi.Utiles.Utile;
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
public class BaseVideo extends BaseEntity implements Serializable {

    
    Integer positiveVote;
    Integer negativeVote;
    Integer ageCategory;
    Integer numView;
    @OneToOne//(cascade = CascadeType.ALL)
            @NotNull(message = "access can not be null")
    TypeAccess access;



}
