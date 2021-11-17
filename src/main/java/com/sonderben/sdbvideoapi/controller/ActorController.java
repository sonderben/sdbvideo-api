package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.Actor;
import com.sonderben.sdbvideoapi.service.ActorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "actor")
public class ActorController extends BaseControllerImpl<Actor, ActorService> {
}
