package com.sonderben.sdbvideoapi.controller;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.EpisodeDto;
import com.sonderben.sdbvideoapi.dto.SeasonResponseDto;
import com.sonderben.sdbvideoapi.entity.Season;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.EpisodeRepository;
import com.sonderben.sdbvideoapi.service.EpisodeService;
import com.sonderben.sdbvideoapi.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("season")
public class SeasonController /*extends BaseControllerImpl<Season, SeasonService> */{
    @Autowired
    protected SeasonService service;
    @Autowired
    private EpisodeRepository episodeRepository;

    @GetMapping("")
    @Transactional
    public ResponseEntity<List<Season>> getAll() {
        return  new ResponseEntity<List<Season>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/adm/{id}")
    @Transactional
    public ResponseEntity<Season> getOneByIdAdmin(@PathVariable Long id) {
        Season entity= service.findById(id);
        if(entity!=null)
            return new ResponseEntity<>(entity,HttpStatus.OK);
        else
            throw new NoDataFoundException("this Entity don't exist");
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<SeasonResponseDto> getOneById(@PathVariable Long id, @RequestParam Long idProfile) {
        Season entity= service.findById(id);
        SeasonResponseDto seasonResponseDto= Converter.convert(entity);
        Iterator<EpisodeDto>iterator=seasonResponseDto.getEpisodes().iterator();
        while (iterator.hasNext()){
            EpisodeDto dto=iterator.next();
            Long currentPlayingTime=episodeRepository.getCurrentPlayingTime(idProfile, dto.getId());
            dto.setCurrentPlayingTime(currentPlayingTime==null?0:currentPlayingTime);
        }
        if(entity!=null)
            return new ResponseEntity<>( seasonResponseDto, HttpStatus.OK);
        else
            throw new NoDataFoundException("this Entity don't exist");
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<Season> save(@RequestBody @Valid Season entity) {


        Season entitySave= service.save(entity);

        return new ResponseEntity<>(entitySave,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Season> update(@RequestBody Season entity, @PathVariable Long id) {
        Season entityUpdate= service.update(entity,id);

        if(entityUpdate!=null)
            return new ResponseEntity<>(entityUpdate,HttpStatus.OK);
        else
            throw new BadRequestException("Entity don't exist");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Season> delete(@PathVariable Long id) {
        Season entity=service.delete(id);

        if(entity!=null)
            return  new ResponseEntity<>(entity, HttpStatus.OK);
        else
            throw new BadRequestException("Entity don't exist");
    }
}
