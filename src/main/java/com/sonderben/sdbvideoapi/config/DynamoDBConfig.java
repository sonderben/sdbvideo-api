package com.sonderben.sdbvideoapi.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {// DynamoDBConfig

    @Bean
    public DynamoDBMapper dynamoDBMapper(){
        return  new DynamoDBMapper(buildAmazonDynamoDB());
    }


    private AmazonDynamoDB buildAmazonDynamoDB(){
        ///EE.UU. Este (Ohio)***REMOVED***
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "***REMOVED***","***REMOVED***"
                        )
                ).withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(
                                "***REMOVED***","***REMOVED***"
                        )
                )).build();
    }




}
