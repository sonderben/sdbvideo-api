package com.sonderben.sdbvideoapi.entity.aws;



import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;


@DynamoDBTable(tableName = "My_LIST")
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyList {


    private Long idProfile;
    private Long idVideo;
    private Long dateAdded;

    @DynamoDBHashKey(attributeName ="idProfile" )
    public Long getIdProfile() {
        return idProfile;
    }

    @DynamoDBRangeKey(attributeName = "idVideo")
    public Long getIdVideo() {
        return idVideo;
    }

    @DynamoDBAttribute(attributeName = "dateAdded")
    public Long getDateAdded() {
        return dateAdded;
    }
}
