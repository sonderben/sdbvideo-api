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

@DynamoDBTable(tableName = "Session")
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String email;
    private String idDevice;
    private String token;
    private Long connection;
    private Long expToken;
    private Long lastConnection;
    private String location;


    @DynamoDBRangeKey(attributeName ="id_device" )
    public String getIdDevice() {
        return idDevice;
    }
    @DynamoDBAttribute(attributeName = "token")
    public String getToken() {
        return token;
    }
    @DynamoDBAttribute(attributeName = "connection")
    public Long getConnection() {
        return connection;
    }
    @DynamoDBAttribute(attributeName = "exp_token")
    public Long getExpToken() {
        return expToken;
    }
    @DynamoDBAttribute(attributeName = "last_connection")
    public Long getLastConnection() {
        return lastConnection;
    }

    @DynamoDBAttribute(attributeName = "location")
    public String  getLocation() {
        return location;
    }



    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }



}
