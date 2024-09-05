package com.sonderben.sdbvideoapi.repository.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.sonderben.sdbvideoapi.entity.aws.MyList;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyListRepository {


    //@Autowired
     public  DynamoDBMapper dynamoDBMapper;

    public MyListRepository() {
        this.dynamoDBMapper = dynamoDBMapper();
    }

    public MyList save(MyList myList){
         dynamoDBMapper.save(myList);
         return myList;
    }
    public MyList find(Long idProfile,Long idVideo){
        MyList myList=dynamoDBMapper.load(MyList.class,idProfile,idVideo);

        if (myList==null)
            throw new NoDataFoundException();
        return myList;
    }

    public MyList findWithoutException(Long idProfile,Long idVideo){
        MyList myList=dynamoDBMapper.load(MyList.class,idProfile,idVideo);

        return myList;
    }


    public MyList delete(Long idProfile,Long idVideo){
        MyList myList=dynamoDBMapper.load(MyList.class, idProfile, idVideo);
        if (myList!=null)
        dynamoDBMapper.delete(myList);
        return myList;
    }

    public List<MyList> deleteAll(Long idProfile){


        MyList myList=new MyList();
        myList.setIdProfile(idProfile);
        DynamoDBQueryExpression<MyList> queryExpression = new DynamoDBQueryExpression<MyList>()
                .withHashKeyValues(myList);
        List<MyList> ddbResults = dynamoDBMapper.query(MyList.class, queryExpression);
        dynamoDBMapper.batchDelete(ddbResults);
        return ddbResults;
    }


    public List<MyList> findAll(Long idProfile){
        MyList myList=new MyList();
       myList.setIdProfile(idProfile);
        DynamoDBQueryExpression<MyList> queryExpression = new DynamoDBQueryExpression<MyList>()
                .withHashKeyValues(myList);

        List<MyList> latestReplies = dynamoDBMapper.query(MyList.class, queryExpression);
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
