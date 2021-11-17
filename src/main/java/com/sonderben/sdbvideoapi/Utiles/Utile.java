package com.sonderben.sdbvideoapi.Utiles;

import com.sonderben.sdbvideoapi.entity.*;

import java.util.Iterator;


public class Utile {
    public static String createDescriptionMovie(Movie movie){
        StringBuilder description=new StringBuilder();
        description.append(movie.getReleaseDate().getTime().getYear()+" ");

       Iterator<TitleSynopsisMovie> titleSynopsisMovieIterator= movie.getTitle().iterator();
       while (titleSynopsisMovieIterator.hasNext()){
           TitleSynopsisMovie titleSynopsisMovie= titleSynopsisMovieIterator.next();
           description.append(titleSynopsisMovie.getTitle())
                   .append(titleSynopsisMovie.getSynopsis()+" ");
       }
        Iterator<Actor> actorList= movie.getActorList().iterator();
        while (actorList.hasNext()){
            Actor actor= actorList.next();
            description.append(actor.getFullName()+" ");

        }
        //
        Iterator<Category> categoryIterator= movie.getCategoryList().iterator();
        while (categoryIterator.hasNext()){
            Category category= categoryIterator.next();
            description.append(category.getName()+" ");

        }
        //


            description.append(movie.getAccess().getName());
        System.out.println("bawwwwwwwwww: "+description);


String descriptionString=description.toString().trim();
        System.out.println("description full: "+descriptionString);
if(descriptionString.length()>250)
    descriptionString=descriptionString.substring(0,250);
        System.out.println("description : "+descriptionString);
        return descriptionString;
    }

}
