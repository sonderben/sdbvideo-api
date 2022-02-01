package com.sonderben.sdbvideoapi.Utiles;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sonderben.sdbvideoapi.entity.*;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Utile {
    //@Value("${jwt.key}")
    static String KEY = "awed8kfSdDSa8!m";
    static Algorithm algorithm=Algorithm.HMAC256(KEY.getBytes());

    public static String createDescriptionMovie(Movie movie) {
        StringBuilder description = new StringBuilder();
        description.append(movie.getReleaseDate().getTime().getYear() + " ");

        Iterator<TitleSynopsis> titleSynopsisMovieIterator = movie.getTitleSynopsises().iterator();
        while (titleSynopsisMovieIterator.hasNext()) {
            TitleSynopsis titleSynopsisMovie = titleSynopsisMovieIterator.next();
            description.append(titleSynopsisMovie.getTitle())
                    .append(titleSynopsisMovie.getSynopsis() + " ");
        }
        Iterator<Actor> actorList = movie.getActors().iterator();
        while (actorList.hasNext()) {
            Actor actor = actorList.next();
            description.append(actor.getFullName() + " ");

        }
        //
        Iterator<Category> categoryIterator = movie.getCategories().iterator();
        while (categoryIterator.hasNext()) {
            Category category = categoryIterator.next();
            description.append(category.getName() + " ");

        }
        //


        description.append(movie.getAccess().getName());
        //System.out.println("bawwwwwwwwww: " + description);


        String descriptionString = description.toString().trim();
        //System.out.println("description full: " + descriptionString);
        if (descriptionString.length() > 250)
            descriptionString = descriptionString.substring(0, 250);
        //System.out.println("description : " + descriptionString);
        return descriptionString.replace("null","");
    }

    public static void unpackNested(String releaseDate, Calendar date) {
        String dateWithoutUnderscope = releaseDate.replace("-", " ");
        DateFormat df = new SimpleDateFormat("yyyy MM dd");
        Date date_ = null;
        try {
            date_ = df.parse(dateWithoutUnderscope);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (releaseDate != null) {
            date = Calendar.getInstance();
            date.setTime(date_);
        }
    }

    public static Calendar unpackNestedDate(String releaseDate) {
        String dateWithoutUnderscope = releaseDate.replace("-", " ");
        DateFormat df = new SimpleDateFormat("yyyy MM dd");
        Date date_ = null;
        Calendar date = null;
        try {
            date_ = df.parse(dateWithoutUnderscope);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (releaseDate != null) {
            date = Calendar.getInstance();
            date.setTime(date_);
        }
        return date;
    }

    public static String createToken(String user,String role,String idDevice) {




        String a= JWT.create()
                .withSubject(user)
                .withIssuer("sonderben")
                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*100000))
                .withClaim("device_id",idDevice)
                .withClaim("role",role)
                .sign(algorithm);
        System.err.println("json created: ");
        System.err.println(a);
        return a;
    }
    public static String createToken(String user,String role) {
        String a= JWT.create()
                .withSubject(user)
                .withIssuer("sonderben")
                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*100000))
                .withClaim("role",role)
                .sign(algorithm);
        System.err.println("json created: ");

        return a;
    }

    public static DecodedJWT validateToken(String token) {

        try {
            DecodedJWT a= JWT.require(algorithm).withIssuer("sonderben").build().verify(token);
            System.err.println("token: verified");
            return  a;
        } catch (JWTVerificationException e){
            System.err.println(e.getMessage());
            return null;
        }

    }
}
