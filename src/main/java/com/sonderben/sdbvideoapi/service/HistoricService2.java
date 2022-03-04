package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.HistoricDto;
import com.sonderben.sdbvideoapi.dto.HistoricRequest;
import com.sonderben.sdbvideoapi.dto.VideoDto;
import com.sonderben.sdbvideoapi.entity.Video;
import com.sonderben.sdbvideoapi.entity.aws.Episode;
import com.sonderben.sdbvideoapi.entity.aws.Historic;
import com.sonderben.sdbvideoapi.entity.aws.MyList;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.VideoRepository;
import com.sonderben.sdbvideoapi.repository.aws.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HistoricService2 {

    //@Autowired
    HistoricRepository historicRepository = new HistoricRepository();
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    EpisodeService episodeService;



    public Historic save(HistoricRequest request) {
        //idProfile,idVideo,isMovie,season,epo,duration,currentTime

        Historic historic;
        if(request.isMovie())
        historic = historicRepository.findWithoutException(request.getIdProfile(), request.getIdVideo());
        else
            historic = historicRepository.findWithoutException(request.getIdProfile(), request.getSeries());



        if (historic == null) {
            historic = new Historic();
            historic.setMovie(request.isMovie());
            historic.setIdProfile(request.getIdProfile());
            //historic.setIdVideo(request.getIdVideo());

            if (!request.isMovie()) {
                historic.setEpisodes(Collections.singletonList(new Episode( request.getIdVideo(),
                        new Date().getTime(), request.getDuration(), request.getCurrentTime(), request.getSeason()) ));
                historic.setIdVideo(request.getSeries());
            } else {
                historic.setIdVideo(request.getIdVideo());
                historic.setDateLastVisited(new Date().getTime());
                historic.setDuration(request.getDuration());
                historic.setCurrentPlayingTime(request.getCurrentTime());
            }

        } else {

            List<Episode> episodes = historic.getEpisodes();
            if (episodes != null && !request.isMovie()) {
                Episode episode = new Episode(request.getIdVideo(),
                        new Date().getTime(), request.getDuration(),request.getCurrentTime(), request.getSeason());


                int index=-1;
                if ( (index = episodes.indexOf(episode)) !=-1 ) {
                    episodes.remove(index);
                }

                episodes.add(episode);
                historic.setEpisodes(episodes);

            } else {
                historic.setIdVideo(request.getIdVideo());
                historic.setDateLastVisited(new Date().getTime());
                historic.setDuration(request.getDuration());
                historic.setCurrentPlayingTime(request.getCurrentTime());
            }
        }


        return historicRepository.save(historic);
    }

    public VideoDto find(Long idProfile, Long idVideo) {
        Historic historic = historicRepository.find(idProfile, idVideo);
        //Episode episode=episodeService.findById(historic.getIdEpisode());
        VideoDto videoDto = null;
        if (historic != null) {
            Video video = videoRepository.getVideoByIdAndIdProfile(idProfile, idVideo);
            if (video != null) {
                videoDto = Converter.convert(video);
                videoDto.setCurrentPlayingTime(historic.getCurrentPlayingTime());
                return videoDto;
            } else
                throw new NoDataFoundException("entity don't found");
        }
        return null;
    }

    public boolean isInHistoric(Long idProfile, Long idVideo) {
        return historicRepository.findWithoutException(idProfile, idVideo) == null ? false : true;
    }

    public Historic delete(Long idProfile, Long idVideo) {
        return historicRepository.delete(idProfile, idVideo);
    }

    public List<Historic> deleteAll(Long idProfile) {
        return historicRepository.deleteAll(idProfile);
    }


    public List<HistoricDto> findAll(Long idProfile) {


        List<Historic> historicList = historicRepository.findAll(idProfile);
        List<HistoricDto> historicDtos = new ArrayList<>();
        Comparator<Episode> comparator = Comparator.comparing(Episode::getDateLastVisited);



        for (int i = 0; i < historicList.size(); i++) {
            Historic historic = historicList.get(i);
            List<Episode> episodeList = historic.getEpisodes();
            HistoricDto historicDto;


            if (episodeList != null) { //mean historic.isMovie==true

                Collections.sort(episodeList, comparator);
                Episode lastEpisodeWatched = (episodeList != null && episodeList.size() > 0) ? episodeList.get(episodeList.size()-1) : null;

                historicDto = new HistoricDto(historic.getIdProfile(), lastEpisodeWatched.getId(),
                        lastEpisodeWatched.getDateLastVisited(), lastEpisodeWatched.getDuration(), lastEpisodeWatched.getCurrentPlayingTime(),
                        lastEpisodeWatched.getIdSeason(), false);

                historicDtos.add(historicDto);
            }
            if(historic.isMovie()){
                historicDto = new HistoricDto(historic.getIdProfile(), historic.getIdVideo(), historic.getDateLastVisited(),
                        historic.getDuration(), historic.getCurrentPlayingTime(), null, true);
                historicDtos.add(historicDto);
            }





        }

        //that could do client side
        Comparator<HistoricDto> comparatorHistoricDto = Comparator.comparing(HistoricDto::getDateLastVisited);
        Collections.sort(historicDtos,comparatorHistoricDto);
        Collections.reverse(historicDtos);
        //

        List<Long>ids=new ArrayList<>();
        videoRepository.findAllById(ids);

        return historicDtos;
    }


}
