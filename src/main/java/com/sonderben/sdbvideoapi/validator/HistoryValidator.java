package com.sonderben.sdbvideoapi.validator;

import com.sonderben.sdbvideoapi.dto.Dto;
import com.sonderben.sdbvideoapi.dto.HistoricRequestDto;
import com.sonderben.sdbvideoapi.exception.BadRequestException;

public class HistoryValidator implements SdbValidator {
    @Override
    public void validateInsert(Dto e) {
        if(e instanceof HistoricRequestDto){
           // System.err.println("validate");
            HistoricRequestDto a= (HistoricRequestDto) e;

            if(a.getProfile()==null)
                throw new BadRequestException("id profile can t be null");
            if(a.getCurrentPlayingTime()==null)
                throw new BadRequestException("currentPlayingTime  can t be null");
            if((a.getMovie()!=null&&a.getEpisode()!=null) || (a.getMovie()==null&&a.getEpisode()==null))
                throw new BadRequestException("between the episode and the movie, only one must be null");
        }
        else {
            throw new RuntimeException(" wrong argument");
        }
    }



    @Override
    public void validateUpdate(Dto e) {
        if(e instanceof HistoricRequestDto){
            HistoricRequestDto a= (HistoricRequestDto) e;
            if(a.getCurrentPlayingTime()==null)
                throw new BadRequestException("currentPlayingTime  can t be null");
            if(a.getProfile()==null)
                throw new BadRequestException("profile  can t be null");

        }
        else {
            throw new RuntimeException(" wrong argument");
        }
    }
}
