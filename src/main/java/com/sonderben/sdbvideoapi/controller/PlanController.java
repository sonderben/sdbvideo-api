package com.sonderben.sdbvideoapi.controller;



import com.sonderben.sdbvideoapi.entity.Plan;
import com.sonderben.sdbvideoapi.service.PlanService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="access")
public class PlanController extends BaseControllerImpl<Plan, PlanService> {
}
