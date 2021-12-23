package com.sonderben.sdbvideoapi.controller;


import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.LoginAdministratorRequestDTO;
import com.sonderben.sdbvideoapi.dto.LoginResponseAdministratorDTO;
import com.sonderben.sdbvideoapi.dto.administratorDTO;
import com.sonderben.sdbvideoapi.entity.Administrator;
import com.sonderben.sdbvideoapi.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "admin")
public class AdministratorController {
    @Autowired
    AdministratorService service;

    @PostMapping("/sign_up")
    @Transactional
    public ResponseEntity<administratorDTO> signUp(@RequestBody Administrator entity) {
        Administrator entitySave= service.signUp(entity);
        return new ResponseEntity<>(Converter.convert(entitySave), HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    @Transactional
    public ResponseEntity<Administrator> getById(@PathVariable Long id) {
        Administrator entitySave= service.findById(id);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }
    @GetMapping("")
    @Transactional
    public ResponseEntity<List<Administrator>> getAll() {
        List<Administrator> entitySave= service.findAll();
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Administrator> getAll(@PathVariable Long id) {
        Administrator entitySave= service.delete(id);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @GetMapping("/login")
    @Transactional
    public ResponseEntity<LoginResponseAdministratorDTO> login(@RequestBody LoginAdministratorRequestDTO request){
        LoginResponseAdministratorDTO loginResponseDTO= service.login(request);
        return new ResponseEntity<>(loginResponseDTO,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Administrator> update(@PathVariable Long id,@RequestBody Administrator request){
        Administrator administrator= service.update(request, id);
        return new ResponseEntity<>(administrator,HttpStatus.OK);
    }
}
