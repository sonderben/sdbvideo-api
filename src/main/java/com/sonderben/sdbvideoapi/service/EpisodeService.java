package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.Dto;
import com.sonderben.sdbvideoapi.dto.EpisodeDto;
import com.sonderben.sdbvideoapi.entity.Episode;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {
    @Autowired
    EpisodeRepository repository;

    public EpisodeDto findById(Long idProfile,Long idEpo){
        Episode epo=repository.findById(idEpo).orElseThrow(()->new NoDataFoundException("Episode don t exist"));
        Dto episodeDto= Converter.convert(epo);

        Long playingTimeTemp=repository.getCurrentPlayingTime(idProfile,idEpo);


        EpisodeDto episodeDtoWithCurrentPlayingTime=(EpisodeDto) episodeDto;
        episodeDtoWithCurrentPlayingTime.setCurrentPlayingTime( (playingTimeTemp==null?0:playingTimeTemp) );
        return episodeDtoWithCurrentPlayingTime;
    }


    public List<Episode> findAll(){

        return repository.findAll();
    }

    public Episode findById(Long id) {
        return repository.findById(id).orElseThrow(()->new NoDataFoundException("Episode don t exist"));
    }

    public Episode save( Episode e) {
        return repository.save(e);
    }

    public Episode delete(Long id)  {
        Episode entity=repository.findById(id).orElseThrow(()->new NoDataFoundException("Episode don t exist"));
            repository.delete(entity);
        return entity;
    }

    public Episode update(Episode entity,Long id)  {
        repository.findById(id).orElseThrow(()->new BadRequestException("Id don t exist"));
        if(entity.getId()!=id)
            throw new BadRequestException("id don t match");
        return  repository.save(entity);
    }
}
