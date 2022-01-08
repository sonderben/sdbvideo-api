package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.dto.EpisodeDto;
import com.sonderben.sdbvideoapi.entity.Episode;
import com.sonderben.sdbvideoapi.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("episode")
public class EpisodeController{
    @Autowired
    EpisodeService repository;

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<EpisodeDto> findById( @PathVariable Long id,@RequestParam Long idProfile){
        return new ResponseEntity<>(repository.findById(idProfile,id), HttpStatus.OK);
    }

    @GetMapping("")
    @Transactional
    public ResponseEntity<List<Episode>> findAll(){
        return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
    }
    @GetMapping("/adm/{id}")
    @Transactional
    public ResponseEntity<Episode> findById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findById(id),HttpStatus.OK);
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<Episode> save(@RequestBody Episode e) {
        return new ResponseEntity<>(repository.save(e),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Episode> delete(@PathVariable Long id)  {
        return new ResponseEntity<>(repository.delete(id),HttpStatus.OK);
    }

    @PutMapping("{/id}")
    @Transactional
    public ResponseEntity<Episode> update(@RequestBody Episode entity,@PathVariable Long id)  {
       return new ResponseEntity<>(repository.update(entity, id),HttpStatus.OK);
    }
}
