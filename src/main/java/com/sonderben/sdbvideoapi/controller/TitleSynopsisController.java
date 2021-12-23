package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.TitleSynopsis;
import com.sonderben.sdbvideoapi.service.TitleSynopsisService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "title_synopsis")
public class TitleSynopsisController extends BaseControllerImpl<TitleSynopsis, TitleSynopsisService> {
}
