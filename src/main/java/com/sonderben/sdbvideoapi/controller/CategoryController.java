package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.Category;
import com.sonderben.sdbvideoapi.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(path ="category")
public class CategoryController extends BaseControllerImpl<Category, CategoryService>{
}
