package com.sonderben.sdbvideoapi.entity;

import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
@Entity
@Table(name = "Plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Plan extends BaseEntity implements Serializable {
    @Column(length = 50,nullable = false)
    String name;
    @Column(length = 4,nullable = false)
    @Min(1000)
    @Max(9999)
    Integer code;
    @Column(length = 1,nullable = false)
    Integer NumOfScreen;

    @Min(0)
    @Max(100)
    //@Column(nullable = false)
    Float price;

    String qualityImage;
}
