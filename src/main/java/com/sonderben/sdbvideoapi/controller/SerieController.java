package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.dto.Dto;
import com.sonderben.sdbvideoapi.dto.SimpleSerieDto;
import com.sonderben.sdbvideoapi.entity.Serie;
import com.sonderben.sdbvideoapi.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("serie")
public class SerieController  {

    @Autowired
    SerieService service;
    @GetMapping("/search")
    @Transactional
    public ResponseEntity<List<SimpleSerieDto>> geSerieByDescription(
            @RequestParam Long profileId,
            @RequestParam String description,
            @RequestParam(required = false,defaultValue = "0") int pageNumber){
        return   new ResponseEntity<>(service.getSeriesByDescription(profileId,description,pageNumber), HttpStatus.OK);
    }
    @GetMapping("/category")
    @Transactional
    public ResponseEntity<List<SimpleSerieDto>> getSerieByCategory(
            @RequestParam Long profileId,
            @RequestParam String description,
            @RequestParam(required = false,defaultValue = "0") int pageNumber){
        return   new ResponseEntity<>(service.getSeriesByDescription(profileId,description,pageNumber), HttpStatus.OK);
    }
    @GetMapping("/admin")
    @Transactional
    public ResponseEntity<List<Serie>> findAllAdm(){
        return   new ResponseEntity<>(service.findAllAdm(), HttpStatus.OK);
    }
    @GetMapping("/admin/{id}")
    @Transactional
    public ResponseEntity<Serie> findOneAdm(@PathVariable Long id){
        return   new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
    @GetMapping("")
    @Transactional
    public ResponseEntity<List<Dto>> findAllClient(
            @RequestParam Long profileId,
            @RequestParam(required = false,defaultValue = "0") int pageNumber){
        return   new ResponseEntity<>(service.findAllClient(profileId,pageNumber), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Serie>updateSerie(@RequestBody Serie serie,@PathVariable Long id){
        Serie serie1=service.update(serie,id);
        if(serie1!=null)
        return new ResponseEntity<>(serie1,HttpStatus.OK);
        else
            throw new RuntimeException("Error at insert serie");
    }
    @PostMapping("")
    @Transactional
    public ResponseEntity<Serie>postSerie(@RequestBody Serie serie){
        Serie serie1=service.post(serie);
        return new ResponseEntity<>(serie1,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Serie>deleteSerie(Long id){
        Serie serie=service.delete(id);
        return new ResponseEntity<>(serie,HttpStatus.OK);
    }

}
