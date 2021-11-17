package com.sonderben.sdbvideoapi.entity;

import com.sonderben.sdbvideoapi.entity.base.Subtitle;


import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "MOVIES_SUBTITLES")
public class MovieSubtitle extends Subtitle implements Serializable {

}
