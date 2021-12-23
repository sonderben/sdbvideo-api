package com.sonderben.sdbvideoapi.entity;

import com.sonderben.sdbvideoapi.entity.base.Subtitle;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "Episode_SUBTITLES")
public class EpisodeSubtitle extends Subtitle implements Serializable {

}
