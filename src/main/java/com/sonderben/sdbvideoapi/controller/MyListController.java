package com.sonderben.sdbvideoapi.controller;


import com.sonderben.sdbvideoapi.dto.MyListDto;
import com.sonderben.sdbvideoapi.dto.MyListRequestDto;
import com.sonderben.sdbvideoapi.entity.MyList;
import com.sonderben.sdbvideoapi.service.MyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("my_list")
public class MyListController /*extends BaseControllerImpl<MyList, MyListService>*/{
 /*   @Autowired
    MyListService service;
    @GetMapping("search_by")
    @Transactional
    ResponseEntity<List<MyListDto>> findByProfileOrderByDateLastVisited
            (@RequestParam int idProfile,
             @RequestParam (required = false,defaultValue = "0")  int pageNumber,
             @RequestParam(required = false,defaultValue = "true") boolean simple){
     //   return new ResponseEntity<>(service.findByIdProfileOrderByDateAdded(idProfile, pageNumber,true), HttpStatus.CREATED);
    }

    @PostMapping("")
    @Transactional
    ResponseEntity<MyList> save(@RequestBody MyListRequestDto dto){
      //  return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }
    @GetMapping("")
    @Transactional
    ResponseEntity<List<MyList>> getAllAdm(){
       // return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MyList> delete(@PathVariable Long id){
      //  return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }*/
}
