package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.MyListDto;
import com.sonderben.sdbvideoapi.entity.MyList;
import com.sonderben.sdbvideoapi.repository.MyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyListService extends BaseServiceImpl<MyList,Long>{
    @Autowired
    MyListRepository repository;

    public List<MyListDto> findByIdProfileOrderByDateAdded(int idUser, int pageNumber, boolean simple){
        List<MyListDto> historicDaoList=new ArrayList<>();
        List<MyList> historicList=repository.findByIdProfileOrderByDateAdded(idUser, pageNumber);
        for (int i = 0; i <historicList.size() ; i++) {
            historicDaoList.add(Converter.convert(historicList.get(i),simple));
        }
        return historicDaoList;
    }
}
