package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.HistoricDto;
import com.sonderben.sdbvideoapi.entity.Historic;
import com.sonderben.sdbvideoapi.repository.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricService extends BaseServiceImpl<Historic,Long>{
    @Autowired
    HistoricRepository repository;

    public List<HistoricDto> findByProfileOrderByDateLastVisited(int idUser, int pageNumber, boolean simple){
        List<HistoricDto> historicDaoList=new ArrayList<>();
        List<Historic> historicList=repository.findByProfileOrderByDateLastVisited(idUser, pageNumber);
        for (int i = 0; i <historicList.size() ; i++) {
            historicDaoList.add(Converter.convert(historicList.get(i),simple));
        }
        return historicDaoList;
    }
}
