package com.sonderben.sdbvideoapi.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class ProfileDto extends Dto{

    private Long id;
    private String name;
    //private Long userId;
    private Boolean isMainProfile;
    private String urlImg;
    private String pin;
    private String defaultLanguage;
    private int ageCategory;
    private List<Long> categoryList;
}
