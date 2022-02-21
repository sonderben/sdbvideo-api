package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.MyListDto;
import com.sonderben.sdbvideoapi.dto.MyListRequestDto;
import com.sonderben.sdbvideoapi.entity.MyList;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.repository.MyListRepository;
import com.sonderben.sdbvideoapi.validator.MyListValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//@Service
public class MyListService {
    /*@Autowired
    MyListRepository repository;

    public List<MyListDto> findByIdProfileOrderByDateAdded(int idUser, int pageNumber, boolean simple){
        List<MyListDto> historicDaoList=new ArrayList<>();
        List<MyList> historicList=repository.findByIdProfileOrderByDateAdded(idUser, pageNumber);
        for (int i = 0; i <historicList.size() ; i++) {
            historicDaoList.add(Converter.convert(historicList.get(i),simple));
        }
        return historicDaoList;
    }

    public MyList save(MyListRequestDto dto){
        new MyListValidator().validateInsert(dto);
        Long idSerie=dto.getSerie()==null?null:dto.getSerie().getId();
        Long idMovie=dto.getMovie()==null?null:dto.getMovie().getId();
        if( repository.alreadyExist(dto.getProfile().getId(),idSerie,idMovie) )
            throw new BadRequestException("row already exist, List already added ");

        MyList myList1=new MyList();
        myList1.setDateAdded(Calendar.getInstance());
        myList1.setMovie(dto.getMovie());
        myList1.setSerie(dto.getSerie());
        myList1.setProfile(dto.getProfile());


        return repository.save(myList1);

    }
    */
}
