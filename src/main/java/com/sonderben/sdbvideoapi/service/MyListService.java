package com.sonderben.sdbvideoapi.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.sonderben.sdbvideoapi.entity.Video;
import com.sonderben.sdbvideoapi.entity.aws.MyList;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.VideoRepository;
import com.sonderben.sdbvideoapi.repository.aws.MyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MyListService {

    @Autowired
    MyListRepository myListRepository;
    @Autowired
    VideoRepository videoRepository;
    public MyList save(MyList  myList){
        myList.setDateAdded(new Date().getTime());

        return myListRepository.save(myList);
    }
    public Video find(Long idProfile, Long idVideo){
        MyList myList= myListRepository.find(idProfile,idVideo);
       if(myList!=null){
           Video video=videoRepository.getVideoByIdAndIdProfile(idProfile,idVideo);
           if(video!=null)
           return video;
           else
               throw new NoDataFoundException("entity don't found");
       }
       return null;
    }

    public boolean isInMyList(Long idProfile, Long idVideo){
        return myListRepository.findWithoutException(idProfile,idVideo)==null?false:true;
    }

    public MyList delete(Long idProfile,Long idVideo){
        return myListRepository.delete(idProfile,idVideo);
    }

    public List<MyList> deleteAll(Long idProfile){
        return myListRepository.deleteAll(idProfile);
    }


    public List<Video> findAll(Long idProfile){

        List<Video>videoList=new ArrayList<>();
        List<MyList> myList= myListRepository.findAll(idProfile);
        if(myList!=null){
            for (int i = 0; i < myList.size(); i++) {
                Video video=videoRepository.getVideoByIdAndIdProfile(idProfile,myList.get(i).getIdVideo());
                if (video!=null)
                    videoList.add(video);
            }
            return videoList;
        }
        return null;
    }





}
