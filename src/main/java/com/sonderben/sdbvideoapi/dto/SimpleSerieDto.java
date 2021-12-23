package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.Category;
import com.sonderben.sdbvideoapi.entity.Reward;
import com.sonderben.sdbvideoapi.entity.TitleSynopsis;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class SimpleSerieDto extends Dto{
    private Set<TitleSynopsis> titleSynopsises;
    Integer positiveVote;
    Integer negativeVote;
    Integer ageCategory;
    Integer numView;
    Set<Reward> rewards;
    Set<Category> categories;
    List<Long> idSeasons;
}
