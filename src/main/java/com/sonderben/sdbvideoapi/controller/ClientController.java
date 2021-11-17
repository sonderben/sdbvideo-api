package com.sonderben.sdbvideoapi.controller;


import com.sonderben.sdbvideoapi.dto.LoginRequestDTO;
import com.sonderben.sdbvideoapi.dto.LoginResponseDTO;
import com.sonderben.sdbvideoapi.entity.Client;
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
//@RequestMapping(path = "user")
public class ClientController  {
    @Autowired
    ClientService service;
    @Autowired()
    ProfileService profileService;
    @PostMapping("client/sign_up")
    @Transactional
    public ResponseEntity<Client> signUp(@RequestBody Client entity) {
        Client entitySave= service.signUp(entity);
        Profile profile=Profile.builder()
                .isMainProfile(true).ageCategory(18).client(entitySave).name("profile name")
                        .pin("pind")
                .defaultLanguage("fr")
                                .urlImg("url.com").build();
        profileService.autoCreate(profile);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @GetMapping ("client/{id}")
    @Transactional
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Client entitySave= service.findById(id);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }
    @GetMapping("client")
    @Transactional
    public ResponseEntity<List<Client>> getAll() {
        List<Client> entitySave= service.findAll();
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @DeleteMapping("client/{id}")
    @Transactional
    public ResponseEntity<Client> getAll(@PathVariable Long id) {
        Client entitySave= service.delete(id);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @GetMapping("client/login")
    @Transactional
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO loginResponseDTO= service.login(request);
        return new ResponseEntity<>(loginResponseDTO,HttpStatus.OK);
    }
}
