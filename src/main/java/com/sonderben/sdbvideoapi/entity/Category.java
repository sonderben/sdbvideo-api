package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CATEGORIES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity implements Serializable {
    @Column(name ="name_", length = 50,unique = true,nullable = false)
    String name;
    @Column(unique = true,nullable = false)
    Integer code;
    /*@Column(nullable = false)
    Short weight;*/



}
