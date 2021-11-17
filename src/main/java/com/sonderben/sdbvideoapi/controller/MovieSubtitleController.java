package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.MovieSubtitle;
import com.sonderben.sdbvideoapi.service.MovieSubtitleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "movie_subtitle")
public class MovieSubtitleController extends BaseControllerImpl<MovieSubtitle, MovieSubtitleService> {
}
