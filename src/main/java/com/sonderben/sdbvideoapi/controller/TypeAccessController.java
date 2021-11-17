package com.sonderben.sdbvideoapi.controller;



import com.sonderben.sdbvideoapi.entity.TypeAccess;
import com.sonderben.sdbvideoapi.service.TypeAccessService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="access")
public class TypeAccessController extends BaseControllerImpl<TypeAccess, TypeAccessService> {
}
