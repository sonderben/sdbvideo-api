package com.sonderben.sdbvideoapi.entity.base;

import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Subtitle extends BaseEntity implements Serializable {

    @Column(unique = true,length = 3)
    String language;
    @Column(unique = true)
    String subtitle;
    Calendar uploadDate;
    String author;
}
