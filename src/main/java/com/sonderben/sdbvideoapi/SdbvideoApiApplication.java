package com.sonderben.sdbvideoapi;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.dto.HistoricRequest;
import com.sonderben.sdbvideoapi.dto.VideoDto;
import com.sonderben.sdbvideoapi.entity.aws.*;
import com.sonderben.sdbvideoapi.repository.aws.HistoricRepository;
import com.sonderben.sdbvideoapi.repository.aws.MyListRepository;
import com.sonderben.sdbvideoapi.repository.aws.SessionRepository;
import com.sonderben.sdbvideoapi.service.HistoricService2;
import com.sonderben.sdbvideoapi.service.MyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.validation.constraints.Pattern;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SdbvideoApiApplication {


	public static void main(String[] args) {



		//for trying
		SpringApplication.run(SdbvideoApiApplication.class, args);

		HistoricService2 service2=new HistoricService2();
		HistoricRequest request=
				 HistoricRequest.newHistoricMovieRequest(1L,14L,1L,221L);

		HistoricRequest request2=
				 HistoricRequest.newHistoricSeriesRequest(1L,1L,1L,221L,2L,3L);


		service2.save(request);
		System.err.println(service2.findAll(1L));






	}


}
