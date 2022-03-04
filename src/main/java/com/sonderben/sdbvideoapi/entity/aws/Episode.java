package com.sonderben.sdbvideoapi.entity.aws;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@DynamoDBDocument
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Episode {
    private Long id;
    private Long dateLastVisited;
    private Long Duration;
    private Long currentPlayingTime;
    private Long idSeason;

    public Episode(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode = (Episode) o;
        return id.equals(episode.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @DynamoDBHashKey
    public Long getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "idSeason")
    public Long getIdSeason() {
        return idSeason;
    }

    @DynamoDBAttribute
    public Long getDateLastVisited() {
        return dateLastVisited;
    }

    @DynamoDBAttribute
    public Long getDuration() {
        return Duration;
    }

    @DynamoDBAttribute
    public Long getCurrentPlayingTime() {
        return currentPlayingTime;
    }
}
