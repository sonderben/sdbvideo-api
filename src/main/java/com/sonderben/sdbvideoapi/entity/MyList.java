package com.sonderben.sdbvideoapi.entity;

import com.sonderben.sdbvideoapi.entity.base.BaseHistoric;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
public class  MyList extends BaseHistoric implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    Calendar dateAdded;
    @OneToOne
    Serie serie;
}
