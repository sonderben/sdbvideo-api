package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.RewardMovie;
import com.sonderben.sdbvideoapi.service.RewardMovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "reward_movie")
public class RewardMovieController extends BaseControllerImpl<RewardMovie, RewardMovieService> {
}
