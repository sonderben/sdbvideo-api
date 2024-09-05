package com.sonderben.sdbvideoapi.repository.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import com.sonderben.sdbvideoapi.entity.aws.Session;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SessionRepository {

    DynamoDBMapper dynamoDBMapper;

    public SessionRepository() {
        this.dynamoDBMapper = dynamoDBMapper();
    }

    public Session save(Session session){
         dynamoDBMapper.save(session);
         return session;
    }
    public Session find(String email,String idDevice){
        Session session=dynamoDBMapper.load(Session.class,email,idDevice);
        if (session==null)
            throw new NoDataFoundException();
        return session;
    }
    public Session find2(String email,String idDevice){
        Session session=dynamoDBMapper.load(Session.class,email,idDevice);

        return session;
    }

    public Session delete(String email,String idDevice){
        Session session=dynamoDBMapper.load(Session.class, email, idDevice);
        if (session==null)
            throw new NoDataFoundException();
        dynamoDBMapper.delete(session);
        return session;
    }

    public List<Session> deleteAllSession(String email){
        /*HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":v1", new AttributeValue().withS(email));
        DynamoDBQueryExpression<Session> queryExpression = new DynamoDBQueryExpression<Session>()
                .withKeyConditionExpression("documentId = :v1")
                .withExpressionAttributeValues(eav);*/
        System.currentTimeMillis();
        Session session=new Session();
        session.setEmail(email);
        DynamoDBQueryExpression<Session> queryExpression = new DynamoDBQueryExpression<Session>()
                .withHashKeyValues(session);
        List<Session> ddbResults = dynamoDBMapper.query(Session.class, queryExpression);
        dynamoDBMapper.batchDelete(ddbResults);
        return ddbResults;
    }


    public List<Session> findAll(String email){
       Session session=new Session();
       session.setEmail(email);
        DynamoDBQueryExpression<Session> queryExpression = new DynamoDBQueryExpression<Session>()
                .withHashKeyValues(session);

        List<Session> latestReplies = dynamoDBMapper.query(Session.class, queryExpression);
        return latestReplies;
    }





    public DynamoDBMapper dynamoDBMapper(){
        return  new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB(){
        ///EE.UU. Este (Ohio)us-east-2
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "dynamodb.us-east-2.amazonaws.com","us-east-2"
                        )
                ).withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(
                                "AKIAXJTGGMWYJ7L5HOM2","ZMwLMLFZkxXsaiDSf3xgXdzpvnInklgTXUCPj+/w"
                        )
                )).build();
    }
}
