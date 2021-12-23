package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.dto.HistoricDto;
import com.sonderben.sdbvideoapi.dto.HistoricRequestDto;
import com.sonderben.sdbvideoapi.entity.Historic;
import com.sonderben.sdbvideoapi.service.HistoricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("historic")
public class HistoricController /*extends BaseControllerImpl<Historic, HistoricService>*/{

    @Autowired
    HistoricService service;
    @GetMapping("/search_by")
    @Transactional
    List<HistoricDto> findByProfileOrderByDateLastVisited
            (@RequestParam int idProfile,
             @RequestParam (required = false,defaultValue = "0")  int pageNumber,
             @RequestParam(required = false,defaultValue = "true") boolean simple){
        return service.findByProfileOrderByDateLastVisited(idProfile, pageNumber,simple);
    }
    @GetMapping("")
    @Transactional
    public ResponseEntity <List<Historic>> getAllAdmin(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity <Historic> getOnePerAdmin(Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity <Historic> deleteOnePerAdmin(@PathVariable Long id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
    @PutMapping("")
    @Transactional
    public ResponseEntity<Historic> update(@RequestBody HistoricRequestDto entity) {
            return new ResponseEntity<>(service.updateByHistoricDto(entity), HttpStatus.OK);
    }
    @PostMapping("")
    @Transactional
    public ResponseEntity<Historic> save(@RequestBody HistoricRequestDto entity) {
        return new ResponseEntity<>(service.updateByHistoricDto(entity), HttpStatus.OK);
    }

    
}
