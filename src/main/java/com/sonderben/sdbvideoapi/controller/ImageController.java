package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.Image;
import com.sonderben.sdbvideoapi.service.ImageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "image")
public class ImageController extends BaseControllerImpl<Image, ImageService>{
}
