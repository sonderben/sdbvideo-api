package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.base.Subtitle;
import com.sonderben.sdbvideoapi.service.SubtitleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "movie_subtitle")
public class SubtitleController extends BaseControllerImpl<Subtitle, SubtitleService> {
}
