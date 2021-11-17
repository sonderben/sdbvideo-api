package com.sonderben.sdbvideoapi.controller;


import com.sonderben.sdbvideoapi.dto.MyListDto;
import com.sonderben.sdbvideoapi.entity.MyList;
import com.sonderben.sdbvideoapi.service.MyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("my_list")
public class MyListController extends BaseControllerImpl<MyList, MyListService>{
    @Autowired
    MyListService service;
    @GetMapping("search_by")
    List<MyListDto> findByProfileOrderByDateLastVisited
            (@RequestParam int idProfile,
             @RequestParam (required = false,defaultValue = "0")  int pageNumber,
             @RequestParam(required = false,defaultValue = "true") boolean simple){
        return service.findByIdProfileOrderByDateAdded(idProfile, pageNumber,simple);
    }
}
