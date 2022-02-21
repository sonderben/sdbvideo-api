package com.sonderben.sdbvideoapi.entity.base;

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
public class BaseVideo extends BaseEntity implements Serializable {

    
    Integer positiveVote;
    Integer negativeVote;
    Integer numView;




}
