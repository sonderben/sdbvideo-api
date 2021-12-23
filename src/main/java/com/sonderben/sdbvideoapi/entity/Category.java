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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORIES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity implements Serializable {
    @Column(name ="name_", length = 50,unique = true,nullable = false)
    @NotBlank(message = "Bad name, can not be blank")
    @NotNull(message = "Bad name, can not be null")
    String name;
    @Column(unique = true,nullable = false)
    @Min(value = 1000,message = "code can not be less than 1000")
    @Max(value = 9999,message = "code can not be more than 1000")
    Integer code;
    /*@Column(nullable = false)
    Short weight;*/



}
