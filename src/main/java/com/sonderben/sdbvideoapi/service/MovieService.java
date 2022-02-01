package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.Dto;
import com.sonderben.sdbvideoapi.dto.MovieDto;
import com.sonderben.sdbvideoapi.dto.SimpleMovieDto;
import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
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

    public List<SimpleMovieDto> getMovieByCategory( Long categoryId, Long profileId, int pageNumber){
        List<Movie>movieList=repository.getMovieByCategory(categoryId,profileId,pageNumber);
        List<SimpleMovieDto> movieDaoList=new ArrayList<>();

        for (int i = 0; i < movieList.size(); i++) {
            System.err.println("url: "+movieList.get(i).getPoster());
            SimpleMovieDto a= Converter.convert(movieList.get(i));
           a.setCurrentPlayingTime(repository.getCurrentPlayingTime(profileId,movieList.get(i).getId()));
            movieDaoList.add(a);
        }
        return movieDaoList;
    }

    public List<Dto>getMovieByDescription(Long idProfile,String description,int pageNumber,String language){
        List<Movie>movieList=repository.getMovieByDescription(idProfile,description,pageNumber,language);
        List<Dto> movieDaoList=new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            setCurrentPlayingTime( idProfile, movieList.get(i),movieDaoList,true);
        }
        return movieDaoList;
    }
    ////////////
    public void   setCurrentPlayingTime(Long idProfile,Movie movie,List<Dto> movieDaoList,boolean simple){
        //List<Dto> movieDaoList=new ArrayList<>();
        Dto dto=Converter.convert(movie);
        SimpleMovieDto simpleMovieDto=null;
        MovieDto movieDto=null;
        Long currentPlayingTime=repository.getCurrentPlayingTime(idProfile, movie.getId());

        if(dto instanceof SimpleMovieDto)
            simpleMovieDto=(SimpleMovieDto) dto;
        else
            movieDto=(MovieDto) dto;
        if(simpleMovieDto!=null) {
            simpleMovieDto.setCurrentPlayingTime( currentPlayingTime==null?0L:currentPlayingTime );
            movieDaoList.add(simpleMovieDto);
        }
        else {
            movieDto.setCurrentPlayingTime( currentPlayingTime==null?0L:currentPlayingTime );
            movieDaoList.add(movieDto);
        }

    }

    public List<Dto> findAll(/*boolean simple,*/Long profileId, int pageNumber){
        List<Dto> movieDaoList=new ArrayList<>();
        List<Movie> movieList=repository.getAllMovies(profileId,pageNumber);

        for (int i = 0; i < movieList.size(); i++) {
            setCurrentPlayingTime( profileId, movieList.get(i),movieDaoList,true);
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
        SimpleMovieDto simpleMovieDto=null;
        MovieDto movieDto=null;
        Dto a=Converter.convert(repository.getMovieById(idMovie,idProfile));
        if(a instanceof SimpleMovieDto) {
            simpleMovieDto = (SimpleMovieDto) a;
            simpleMovieDto.setCurrentPlayingTime(repository.getCurrentPlayingTime(idProfile, idMovie));
            return simpleMovieDto;
        }else {
            movieDto=(MovieDto) a;
            movieDto.setCurrentPlayingTime(repository.getCurrentPlayingTime(idProfile, idMovie));
            return movieDto;
        }
    }


    public Movie save( Movie e) {
        return repository.save(e);
    }


    public Movie delete(Long id)  {
        Movie entity=repository.findById(id).orElseThrow(()->new BadRequestException("don't exist movie with this id: "+id));
            repository.delete(entity);
            return entity;

    }


    public Movie update(Movie entity,Long id)  {
        repository.findById(id).orElseThrow(()->new NoDataFoundException("don't exist movie with this id: "+id));
        if(entity.getId()!=id)
            throw new BadRequestException("id passed in parameter: "+id+" does not match egual with id of the movie: "+entity.getId());
        return repository.save(entity);
    }
}
