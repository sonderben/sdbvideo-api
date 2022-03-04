package com.sonderben.sdbvideoapi.validator;

import com.sonderben.sdbvideoapi.dto.Dto;
//import com.sonderben.sdbvideoapi.dto.HistoricRequestDto;
import com.sonderben.sdbvideoapi.dto.MyListRequestDto;
import com.sonderben.sdbvideoapi.exception.BadRequestException;

public class MyListValidator implements SdbValidator {
    @Override
    public void validateInsert(Dto e) {
        if(e instanceof MyListRequestDto){

            MyListRequestDto a= (MyListRequestDto) e;

            if(a.getProfile()==null)
                throw new BadRequestException("id profile can t be null");
            if((a.getMovie()!=null&&a.getSerie()!=null) || (a.getMovie()==null&&a.getSerie()==null))
                throw new BadRequestException("between the series and the movie, only one must be null");
        }
        else {
            throw new RuntimeException(" wrong argument");
        }
    }



    @Override
    public void validateUpdate(Dto e) {
    }
}
