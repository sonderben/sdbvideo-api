package com.sonderben.sdbvideoapi.Utiles;

import com.sonderben.sdbvideoapi.entity.*;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


public class Utile {
    //@Value("${jwt.key}")
    static String KEY = "awed8kfSdDSa8!m";

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

    public static String createToken(String user) {
        Date init = new Date();
        Date end = new Date(init.getTime() + 1_000 * 60 * 60);
        String jwt = Jwts.builder()
                .setSubject(user)
                .setIssuedAt(init)
                .setExpiration(end)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
        System.err.println(jwt);
        validateToken(jwt);
        return jwt;
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            System.err.println("everything is ok");
            return true;
        } catch (UnsupportedJwtException e) {
            System.err.println(e);
        } catch (MalformedJwtException e) {
            System.err.println(e);
        } catch (SignatureException e) {
            System.err.println(e);
        } catch (ExpiredJwtException e) {
            System.err.println(e);
        }
        return false;
    }
}
