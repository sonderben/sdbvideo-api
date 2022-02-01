package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.ProfileDto;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.service.ClientService;
import com.sonderben.sdbvideoapi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    ProfileService service;

    @Autowired
    ClientService clientService;

    @PostMapping("profile/{profileId}")
    @Transactional
    public ResponseEntity<ProfileDto> save(@PathVariable Long profileId, @RequestBody Profile entity) {
        return new ResponseEntity<ProfileDto>(service.create(entity, profileId), HttpStatus.CREATED);
    }

    @GetMapping("profile/{id}")
    @Transactional
    public ResponseEntity<Profile> getById(@PathVariable Long id) {
        Profile entitySave = service.findById(id);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @GetMapping("profile")
    @Transactional
    public ResponseEntity<List<ProfileDto>> getAll() {
        List<ProfileDto> entitySave = service.findAll();
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @DeleteMapping("profile/{id}")
    @Transactional
    public ResponseEntity<Profile> delete(@PathVariable Long id) {
        Profile entitySave = service.delete(id);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @PutMapping("profile/{id}")
    public ResponseEntity<ProfileDto> update(@RequestBody Profile entity, @PathVariable Long id) {
        return new ResponseEntity<>(Converter.convert(service.update(entity, id)), HttpStatus.OK);
    }

/*
    @PostMapping("profile")
    @Transactional
    public ResponseEntity<ProfileDto>createProfile(Profile profile){
        return new ResponseEntity<ProfileDto>(service.autoCreate(profile),HttpStatus.CREATED);
    }*/
}
