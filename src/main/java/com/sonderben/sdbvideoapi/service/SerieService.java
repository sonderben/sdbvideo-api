package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.Dto;
import com.sonderben.sdbvideoapi.dto.SimpleSerieDto;
import com.sonderben.sdbvideoapi.entity.Serie;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SerieService /*extends BaseServiceImpl<Serie,Long>*/{

    @Autowired
    SerieRepository serieRepository;
    public List<SimpleSerieDto> getSeriesByDescription(Long idProfile,String description,int pageNumber){
        List<Serie>series=serieRepository.getSerieByDescription( idProfile, description, pageNumber);
       List<SimpleSerieDto>serieDtos= Converter.convert(series);
        return Converter.convert(series);
    }

    public List<SimpleSerieDto> getSeriesByCategory(Long categoryId,Long idProfile,int pageNumber){
        List<Serie>series=serieRepository.getSerieByCategory(categoryId, idProfile, pageNumber);
        return Converter.convert(series);
    }

    public List<Serie> findAllAdm(){
        return serieRepository.findAll();
    }
    public List<Dto> findAllClient( Long profileId, int pageNumber){
        List<Dto> movieDaoList=new ArrayList<>();
        List<Serie> movies=serieRepository.getAllSeries(profileId,pageNumber);
        for (int i = 0; i < movies.size(); i++) {
            movieDaoList.add(Converter.convert(movies.get(i)));
        }
        return movieDaoList;
    }
    public Serie findById(Long id){
        return serieRepository.findById(id).orElseThrow(()->new NoDataFoundException("No serie found with this id "+id));
    }
    public Serie delete(Long id){
        Serie serie=serieRepository.findById(id).orElse(null);
        if (serie!=null) {
            serieRepository.delete(serie);
            return serie;
        }
        throw new NoDataFoundException("serie don't exist");
    }

    public Serie post(Serie entity)  {
        Serie serie=serieRepository.save(entity);
        return serie;
    }

    public Serie update(Serie entity,Long id)  {
        Serie entityFind=serieRepository.findById(id).orElseThrow(()->new BadRequestException("Id don t exist"));
        Serie update=serieRepository.save( entityFind);

        serieRepository.save(entity);
        return  update;
    }
}
