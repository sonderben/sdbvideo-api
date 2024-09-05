package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.dto.Dto;
import com.sonderben.sdbvideoapi.dto.SimpleMovieDto;
import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
//spring.datasource.url=jdbc:postgresql://localhost:5432/sdbvideodb
//spring.datasource.url=jdbc:postgresql://db-instance-sdb-video.c4r9hb65ofm9.us-east-2.rds.amazonaws.com:5432/sdbvideodb

@RestController
@RequestMapping(path = "movie")
public class MovieController extends BaseControllerImpl<Movie,MovieService> {
  /*  @Autowired
    MovieService service;

    @GetMapping("/search")
    public ResponseEntity<List<Dto>> getMovieByDescription(
            @RequestParam("profile_id") Long profileId,
            @RequestParam String description,
            @RequestParam(required = false,defaultValue = "english") String language,
            @RequestParam(required = false, defaultValue = "0") int pageNumber) {
        return new ResponseEntity<List<Dto>>(service.getMovieByDescription(profileId, description, pageNumber,language), HttpStatus.OK);
    }

    //
    @GetMapping("/category")
    @Transactional
    public ResponseEntity<List<SimpleMovieDto>> getMovieByCategory(@RequestParam Long category_code,
                                                                   @RequestParam("profile_id") Long profileId,
                                                                   @RequestParam(required = false, defaultValue = "0") int pageNumber) {

        return new ResponseEntity<>(service.getMovieByCategory(category_code, profileId, pageNumber), HttpStatus.OK);
    }

    //////////////////////////
    @GetMapping("/all")
    @Transactional
    public ResponseEntity<List<Dto>> getAll(
                                            @RequestParam("profile_id") Long profileId,
                                            @RequestParam(required = false, defaultValue = "0") int pageNumber) {
        return new ResponseEntity<List<Dto>>(service.findAll( profileId, pageNumber), HttpStatus.OK);
    }
    @GetMapping("/admin")
    @Transactional
    public ResponseEntity<List<Movie>> getAllAdmin() {
        return new ResponseEntity<>(service.findAllAdmin(), HttpStatus.OK);
    }
    @GetMapping("admin/{id}")
    @Transactional
    public ResponseEntity<Movie> getOneAdmin(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Dto> getOneById(@PathVariable Long id,
                                          @RequestParam("profile_id") Long profileId,
                                          @RequestParam(required = false, defaultValue = "false") boolean simple) {
        Dto entity = service.findById(id, profileId, simple);
        //if(entity!=null)
        return new ResponseEntity<>(entity, HttpStatus.OK);
        *//*else
            throw new NoDataFoundException("this Entity don't exist");*//*
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<Movie> save(@RequestBody @Valid Movie entity) {
        entity.setAvailability(Calendar.getInstance());
        if (entity instanceof Movie) {
            Movie movie =  entity;
            if (movie.getDescription() == null)
                movie.setDescription(Utile.createDescriptionMovie(movie));
            System.out.println(movie.getDescription());
        }

        Movie entitySave = service.save(entity);

        return new ResponseEntity<>(entitySave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Movie> update(@RequestBody Movie entity, @PathVariable Long id) {
        Movie entityUpdate = service.update(entity, id);

        if (entityUpdate != null)
            return new ResponseEntity<>(entityUpdate, HttpStatus.OK);
        else
            throw new BadRequestException("Entity don't exist");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Movie> delete(@PathVariable Long id) {
        Movie entity = service.delete(id);

        if (entity != null)
            return new ResponseEntity<Movie>(entity, HttpStatus.OK);
        else
            throw new BadRequestException("Entity don't exist");
    }
*/


    @GetMapping("/video/{idVideo}")
    @Transactional
    public ResponseEntity<Dto> getOneById(@PathVariable Long idVideo,
                                          @RequestParam("profile") Long profileId) {
        return new ResponseEntity<>(service.findById(profileId, idVideo), HttpStatus.OK);
    }
}
