package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.entity.Movie;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


public abstract class BaseControllerImpl<ENTITY extends BaseEntity, S extends BaseServiceImpl<ENTITY,Long>> {
    @Autowired
    protected S service;

    @GetMapping("")
    @Transactional
    public ResponseEntity<List<ENTITY>> getAll() {
        return  new ResponseEntity<List<ENTITY>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ENTITY> getOneById(@PathVariable Long id) {
        ENTITY entity= service.findById(id);
        if(entity!=null)
            return new ResponseEntity<>(entity,HttpStatus.OK);
        else
            throw new NoDataFoundException("this Entity don't exist");
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<ENTITY> save(@RequestBody @Valid ENTITY entity) {
        /*if(entity instanceof Movie){
            Movie movie= (Movie) entity;
            if(movie.getDescription()==null)
                movie.setDescription(Utile.createDescriptionMovie(movie));
            System.out.println(movie.getDescription());
        }*/

        ENTITY entitySave= service.save(entity);

        return new ResponseEntity<>(entitySave,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ENTITY> update(@RequestBody ENTITY entity, @PathVariable Long id) {
        ENTITY entityUpdate= service.update(entity,id);

        if(entityUpdate!=null)
            return new ResponseEntity<>(entityUpdate,HttpStatus.OK);
        else
            throw new BadRequestException("Entity don't exist");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ENTITY> delete(@PathVariable Long id) {
        ENTITY entity=service.delete(id);

        if(entity!=null)
            return  new ResponseEntity<>(entity, HttpStatus.OK);
        else
            throw new BadRequestException("Entity don't exist");
    }


}
