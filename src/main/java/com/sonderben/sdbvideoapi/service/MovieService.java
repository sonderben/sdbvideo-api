package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.Dto;
import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService /*extends BaseServiceImpl<Movie,Long>*/{
    @Autowired
    MovieRepository repository;

    public List<Dto> getMovieByCategory( Long categoryId, Long profileId, int pageNumber){
        List<Movie>movieList=repository.getMovieByCategory(categoryId,profileId,pageNumber);
        List<Dto> movieDaoList=new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            movieDaoList.add(Converter.convert(movieList.get(i),true));
        }
        return movieDaoList;
    }

    public List<Dto>getMovieByDescription(Long idProfile,String description,int pageNumber){
        List<Movie>movieList=repository.getMovieByDescription(idProfile,description,pageNumber);
        List<Dto> movieDaoList=new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            movieDaoList.add(Converter.convert(movieList.get(i),true));
        }
        return movieDaoList;
    }
    ////////////

    public List<Dto> findAll(boolean simple,Long profileId, int pageNumber){
        List<Dto> movieDaoList=new ArrayList<>();
        List<Movie> movies=repository.getAllMovies(profileId,pageNumber);
        for (int i = 0; i < movies.size(); i++) {
            movieDaoList.add(Converter.convert(movies.get(i),simple));
        }
        return movieDaoList;
    }
    public List<Movie> findAllAdmin(){
        return repository.findAll();
    }
    public Movie findById(Long id){
        return repository.findById(id).orElseThrow(()->new NoDataFoundException("no movie found with this id: "+id));
    }


    public Dto findById(Long idMovie,Long idProfile,boolean simple) {

        return Converter.convert(repository.getMovieById(idMovie,idProfile),simple);
    }


    public Movie save( Movie e) {
        return repository.save(e);
    }


    public Movie delete(Long id)  {
        Movie entity=repository.findById(id).orElse(null);
        if(entity!=null)
            repository.delete(entity);
        return entity;
    }


    public Movie update(Movie entity,Long id)  {
        repository.findById(id).orElseThrow(()->new NoDataFoundException("don't exist movie with this id: "+id));
            return repository.save(entity);
    }
}
