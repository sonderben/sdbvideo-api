package com.sonderben.sdbvideoapi.entity.aws;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import lombok.AllArgsConstructor;

@DynamoDBDocument
@AllArgsConstructor
public class Device {
    private String idDevice;
    private String token;
    private Long connection;
    private Long expToken;
    private Long lastConnection;

    @DynamoDBHashKey(attributeName = "id_device")
    public String getIdDevice() {
        return idDevice;
    }
    @DynamoDBHashKey(attributeName = "token")
    public String getToken() {
        return token;
    }
    @DynamoDBHashKey(attributeName = "connection")
    public Long getConnection() {
        return connection;
    }
    @DynamoDBHashKey(attributeName = "exp_token")
    public Long getExpToken() {
        return expToken;
    }
    @DynamoDBHashKey(attributeName = "last_connection")
    public Long getLastConnection() {
        return lastConnection;
    }
}
