package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.*;
import lombok.*;


import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SerieDto extends Dto{
    Long id;
    private Set<TitleSynopsis> titleSynopsis;
    Set<Reward> rewards;
    Set<Category> categories;
    List<Long> idSeasons;
    float average;
    Integer ageCategory;
    String poster;
    String trailer;


}
