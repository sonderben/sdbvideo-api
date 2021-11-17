package com.sonderben.sdbvideoapi.entity;

import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "ACTORS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Actor extends BaseEntity implements Serializable {
    @Column(length = 100,nullable = false)
    String fullName;
    @Temporal(TemporalType.DATE)
    Calendar birthday;
    String nationality;
    @Column(length = 10,nullable = false)
    String sex;
    String photo;

}
