package com.sonderben.sdbvideoapi.entity;

import com.sonderben.sdbvideoapi.entity.base.BaseHistoric;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Data
public class  MyList extends BaseHistoric implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    Calendar dateAdded;
}
