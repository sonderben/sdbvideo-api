package com.sonderben.sdbvideoapi;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.validation.constraints.Pattern;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

@SpringBootApplication
public class SdbvideoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdbvideoApiApplication.class, args);


		//String to=Utile.createToken("q@gmail.com","android");
		//Utile.validateToken(to);


	}

}
