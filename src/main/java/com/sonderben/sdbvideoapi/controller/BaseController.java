package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public interface BaseController <ENTITY extends BaseEntity,ID extends Serializable>{
    @GetMapping("")
    public ResponseEntity<List<ENTITY> > getAll();
    @GetMapping("")
    public ResponseEntity<ENTITY>getOneById(@PathVariable ID id);
    @PutMapping("")
    public ResponseEntity<ENTITY>save(@RequestBody @Valid ENTITY entity);
    @PutMapping("/{id}")
    public ResponseEntity<ENTITY>update(@PathVariable ID id);
    @DeleteMapping("/{id}")
    public ResponseEntity<ENTITY>delete(@PathVariable ID id);
}
