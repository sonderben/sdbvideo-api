package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.TitleSynopsisMovie;
import com.sonderben.sdbvideoapi.service.TitleSynopsisMovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "title_synopsis_movie")
public class TitleSynopsisMovieController extends BaseControllerImpl<TitleSynopsisMovie, TitleSynopsisMovieService> {
}
