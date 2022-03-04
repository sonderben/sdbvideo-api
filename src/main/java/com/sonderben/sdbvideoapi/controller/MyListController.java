package com.sonderben.sdbvideoapi.controller;


import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.VideoDto;
import com.sonderben.sdbvideoapi.entity.aws.MyList;
import com.sonderben.sdbvideoapi.service.MyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("my_lists")

public class MyListController {
    @Autowired
    MyListService myListService;

    @PostMapping("")
    public MyList save(@RequestBody MyList myList){
        return myListService.save( myList);
    }

    @PutMapping("")
    public MyList update(@RequestBody MyList myList){
        return myListService.save(myList);
    }

    @GetMapping("/{id_profile}/{id_video}")
    public ResponseEntity<VideoDto> find(@PathVariable(name = "id_profile") Long idProfile,
                                         @PathVariable(name = "id_video")Long idVideo){

         return new ResponseEntity<>(Converter.convert( myListService.find(idProfile,idVideo) ), HttpStatus.OK);
    }

    @GetMapping("/exist/{id_profile}/{id_video}")
    public ResponseEntity<Boolean> isInMyList(@PathVariable(name = "id_profile") Long idProfile,
                                         @PathVariable(name = "id_video")Long idVideo){

        return new ResponseEntity<>( myListService.isInMyList( idProfile, idVideo ), HttpStatus.OK);
    }


    @DeleteMapping("/{id_profile}/{id_video}")
    public MyList delete(@PathVariable(name = "id_profile") Long idProfile,
                         @PathVariable(name = "id_video")Long idVideo){
         return myListService.delete(idProfile,idVideo);
    }

    @DeleteMapping("/{id_profile}")
    public List<MyList> deleteAll(@PathVariable(name = "id_profile")Long idProfile){
        return myListService.deleteAll(idProfile);
    }


    @GetMapping("/{id_profile}")
    public ResponseEntity<List<VideoDto>> findAll(@PathVariable(name = "id_profile" ) Long idProfile){
          return new ResponseEntity<>( Converter.convert( myListService.findAll(idProfile) ), HttpStatus.OK);
    }
}
