package com.sonderben.sdbvideoapi.entity.aws;



import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@DynamoDBTable(tableName = "Historic")
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Historic {
    private Long idProfile;
    private Long idVideo;
    private Long dateLastVisited;
    private Long Duration;
    private Long currentPlayingTime;
    private boolean isMovie;


    private List<Episode> episodes;

    @DynamoDBAttribute(attributeName = "episodes")
    public List<Episode> getEpisodes() {
        return episodes;
    }

    public Historic(Long idProfile, Long idVideo, Long dateLastVisited,
                    Long currentPlayingTime,  boolean isMovie) {
        this.idProfile = idProfile;
        this.idVideo = idVideo;
        this.dateLastVisited = dateLastVisited;
        this.currentPlayingTime = currentPlayingTime;
        this.isMovie = isMovie;

    }
@DynamoDBAttribute(attributeName = "duration")
    public Long getDuration() {
        return Duration;
    }



    @DynamoDBHashKey(attributeName ="idProfile" )
    public Long getIdProfile() {
        return idProfile;
    }

    @DynamoDBRangeKey(attributeName = "idVideo")
    public Long getIdVideo() {
        return idVideo;
    }

    @DynamoDBAttribute(attributeName = "dateLastVisited")
    public Long getDateLastVisited() {
        return dateLastVisited;
    }

    @DynamoDBAttribute(attributeName = "currentPlayingTime")
    public Long getCurrentPlayingTime() {
        return currentPlayingTime;
    }

    @DynamoDBAttribute(attributeName = "isMovie")
    public boolean isMovie() {
        return isMovie;
    }

}
