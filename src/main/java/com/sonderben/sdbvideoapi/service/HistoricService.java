package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.HistoricDto;
import com.sonderben.sdbvideoapi.dto.HistoricRequestDto;
import com.sonderben.sdbvideoapi.entity.Historic;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.repository.HistoricRepository;
import com.sonderben.sdbvideoapi.validator.HistoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class HistoricService extends BaseServiceImpl<Historic, Long> {
    @Autowired
    HistoricRepository repository;

    public List<HistoricDto> findByProfileOrderByDateLastVisited(int idUser, int pageNumber, boolean simple) {
        List<HistoricDto> historicDaoList = new ArrayList<>();
        List<Historic> historicList = repository.findByProfileOrderByDateLastVisited(idUser, pageNumber);
        for (int i = 0; i < historicList.size(); i++) {
            historicDaoList.add(Converter.convert(historicList.get(i), simple));
        }
        return historicDaoList;
    }

    public Boolean isAlreadyExist(Long idProfile, Long idSerie, Long isMovie) {
        return repository.alreadyExist(idProfile, idSerie, isMovie);
    }

    public Historic updateByHistoricDto(HistoricRequestDto historicDto) {

        Historic historic = null;
        Historic newHistoric = new Historic();
        Long id = historicDto.getId();
        if (id != null)
            historic = repository.findById(id).orElse(null);

        if (historic == null) {/////post
            new HistoryValidator().validateInsert(historicDto);
            //System.out.println("post historic");

            Long idSerie=historicDto.getSerie()==null?null:historicDto.getSerie().getId();
            Long idMovie=historicDto.getMovie()==null?null:historicDto.getMovie().getId();

            if ( !repository.alreadyExist(historicDto.getProfile().getId(), idSerie, idMovie) ) {
                newHistoric.setCurrentPlayingTime(historicDto.getCurrentPlayingTime());
                newHistoric.setDateLastVisited(Calendar.getInstance());
                newHistoric.setMovie(historicDto.getMovie());
                newHistoric.setSerie(historicDto.getSerie());
                newHistoric.setProfile(historicDto.getProfile());
                return repository.save(newHistoric);
            } else
                throw new BadRequestException("row already exist, historic already exist");

        } else {/////put
            new HistoryValidator().validateUpdate(historicDto);
            newHistoric.setId(historicDto.getId());
            newHistoric.setCurrentPlayingTime(historicDto.getCurrentPlayingTime());
            newHistoric.setDateLastVisited(Calendar.getInstance());
            newHistoric.setMovie(historic.getMovie());
            newHistoric.setSerie(historic.getSerie());
            newHistoric.setProfile(historicDto.getProfile());
            return repository.save(newHistoric);
        }

    }

}
