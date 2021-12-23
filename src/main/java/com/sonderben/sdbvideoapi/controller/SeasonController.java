package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.Season;
import com.sonderben.sdbvideoapi.service.SeasonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("season")
public class SeasonController extends BaseControllerImpl<Season, SeasonService> {
}
