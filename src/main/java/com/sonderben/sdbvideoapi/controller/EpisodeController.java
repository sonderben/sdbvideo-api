package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.Episode;
import com.sonderben.sdbvideoapi.service.EpisodeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("episode")
public class EpisodeController extends BaseControllerImpl<Episode, EpisodeService>{
}
