package com.sonderben.sdbvideoapi.controller;


import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.ClientRequestDto;
import com.sonderben.sdbvideoapi.dto.ClientResponseDto;
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

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping(path = "client")
public class ClientController  {
    @Autowired
    ClientService service;
    @Autowired()
    ProfileService profileService;
    @PostMapping("/sign_up")
    @Transactional
    public ResponseEntity<ClientResponseDto> signUp(@Valid @RequestBody ClientRequestDto entity) {

        Client entitySave= service.signUp(Converter.convert(entity));
        Profile profile=Profile.builder()
                .isMainProfile(true)
                .ageCategory(18)
                .client(entitySave)
                .name("Main profile")
                .pin("1234")
                .defaultLanguage("fr")
                .urlImg("url.com").build();

        profileService.autoCreate(profile);
        return new ResponseEntity<>(Converter.convert(entitySave), HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    @Transactional
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Client entitySave= service.findById(id);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }
    @GetMapping("")
    @Transactional
    public ResponseEntity<List<Client>> getAll() {
        List<Client> entitySave= service.findAll();
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Client> getAll(@PathVariable Long id) {
        Client entitySave= service.delete(id);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @GetMapping("/login")
    @Transactional
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO loginResponseDTO= service.login(request);
        return new ResponseEntity<>(loginResponseDTO,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Client>update(@RequestBody Client client,@PathVariable Long id){

        return new ResponseEntity<>(service.update(client,id),HttpStatus.OK);
    }
}
