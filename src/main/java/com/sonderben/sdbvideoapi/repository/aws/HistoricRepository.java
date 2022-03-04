package com.sonderben.sdbvideoapi.repository.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.sonderben.sdbvideoapi.entity.aws.Historic;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoricRepository {


    //@Autowired
     public  DynamoDBMapper dynamoDBMapper;

    public HistoricRepository() {
        this.dynamoDBMapper = dynamoDBMapper();
    }

    public Historic save(Historic historic){
         dynamoDBMapper.save(historic);
         return historic;
    }
    public Historic find(Long idProfile,Long idVideo){
        Historic historic=dynamoDBMapper.load(Historic.class,idProfile,idVideo);


        /*
        QuerySpec querySpec = new QuerySpec();
        querySpec.withKeyConditionExpression("PRIMARYKEY = :key")
                .withValueMap(new ValueMap()
                        .withString(":key", primaryKeyValue));
        querySpec.withScanIndexForward(true);
        querySpec.withMaxResultSize(1);
        */

        if (historic==null)
            throw new NoDataFoundException();
        return historic;
    }

    public Historic findWithoutException(Long idProfile,Long idVideo){
        Historic historic=dynamoDBMapper.load(Historic.class,idProfile,idVideo);

        return historic;
    }


    public Historic delete(Long idProfile,Long idVideo){
        Historic myList=dynamoDBMapper.load(Historic.class, idProfile, idVideo);
        if (myList!=null)
        dynamoDBMapper.delete(myList);
        return myList;
    }

    public List<Historic> deleteAll(Long idProfile){


        Historic historic=new Historic();
        historic.setIdProfile(idProfile);
        DynamoDBQueryExpression<Historic> queryExpression = new DynamoDBQueryExpression<Historic>()
                .withHashKeyValues(historic);
        List<Historic> ddbResults = dynamoDBMapper.query(Historic.class, queryExpression);
        dynamoDBMapper.batchDelete(ddbResults);
        return ddbResults;
    }


    public List<Historic> findAll(Long idProfile){
        Historic historic=new Historic();
        historic.setIdProfile(idProfile);
        DynamoDBQueryExpression<Historic> queryExpression = new DynamoDBQueryExpression<Historic>()
                .withHashKeyValues(historic);

        List<Historic> latestReplies = dynamoDBMapper.query(Historic.class, queryExpression);
        return latestReplies;
    }





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
