package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.Reward;
import com.sonderben.sdbvideoapi.service.RewardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "reward")
public class RewardController extends BaseControllerImpl<Reward, RewardService> {
}
