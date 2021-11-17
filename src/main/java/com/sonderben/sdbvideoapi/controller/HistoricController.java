package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.dto.HistoricDto;
import com.sonderben.sdbvideoapi.entity.Historic;
import com.sonderben.sdbvideoapi.service.HistoricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("historic")
public class HistoricController extends BaseControllerImpl<Historic, HistoricService>{

    @Autowired
    HistoricService service;
    @GetMapping("search_by")
    List<HistoricDto> findByProfileOrderByDateLastVisited
            (@RequestParam int idProfile,
             @RequestParam (required = false,defaultValue = "0")  int pageNumber,
             @RequestParam(required = false,defaultValue = "true") boolean simple){
        return service.findByProfileOrderByDateLastVisited(idProfile, pageNumber,simple);
    }
}
