package com.sonderben.sdbvideoapi.controller;


import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.*;
import com.sonderben.sdbvideoapi.entity.Client;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.entity.aws.Device;
import com.sonderben.sdbvideoapi.entity.aws.Session;
import com.sonderben.sdbvideoapi.exception.ForbiddenException;
import com.sonderben.sdbvideoapi.repository.aws.SessionRepository;
import com.sonderben.sdbvideoapi.service.ClientService;
import com.sonderben.sdbvideoapi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping(path = "client")
public class ClientController {
    @Autowired
    ClientService service;
    @Autowired()
    ProfileService profileService;

    @PostMapping("/sign_up")
    @Transactional
    public ResponseEntity<ClientResponseDto> signUp(@Valid @RequestBody ClientRequestDto entity) {

        Client entitySave = service.signUp(Converter.convert(entity));
        Profile profile = Profile.builder()
                .isMainProfile(true)
                .ageCategory(18)
                .client(entitySave)
                .name("Main profile")
                .pin("1234")
                .defaultLanguage("fr")
                //https://ejemploht.s3.us-east-2.amazonaws.com/img-profile/ersenb.png
                .urlImg("https://sdbvideo.s3.amazonaws.com/profile+pictures/panchb.jpg").build();

        entitySave.setProfileList(Collections.singletonList(profile));
        profileService.autoCreate(profile);
        return new ResponseEntity<>(Converter.convert(entitySave), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Client entitySave = service.findById(id);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @GetMapping("")
    @Transactional
    public ResponseEntity<List<Client>> getAll() {
        List<Client> entitySave = service.findAll();
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        Client entitySave = service.delete(id);
        return new ResponseEntity<>(entitySave, HttpStatus.OK);
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO login) {
        LoginResponseDTO loginResponseDTO = service.login(login);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@Valid @RequestBody SessionDto sessionDto) {
        service.logout(sessionDto);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/profiles")
    @Transactional
    public ResponseEntity<List<ProfileDto>> getProfileByEmailClient(@RequestParam String email) {
        return new ResponseEntity<>(service.getProfileByEmailClient(email), HttpStatus.OK);
    }

    @GetMapping("/sessions")
    public List<Session> findAllSession(@RequestParam(name = "profile_id") Long idProfile) {
        return service.findAllSession(idProfile);
    }

    @DeleteMapping("/sessions")
    public void deleteAllSession(@RequestParam(name = "profile_id") Long idProfile) {
        service.deleteAllSession(idProfile);
    }

    @DeleteMapping("/session")
    public void deleteSession(
            @RequestParam(name = "profile_id") Long idProfile,
            @RequestParam(name = "device") String device) {
        System.err.println("delete one");
        service.deleteSession(idProfile, device);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Client> update(@RequestBody Client client, @PathVariable Long id) {

        return new ResponseEntity<>(service.update(client, id), HttpStatus.OK);
    }
}
