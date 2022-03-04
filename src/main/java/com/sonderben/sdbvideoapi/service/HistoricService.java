package com.sonderben.sdbvideoapi.service;

//@Service
public class HistoricService  {
/*    @Autowired
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

            Long idSerie=historicDto.getEpisode()==null?null:historicDto.getEpisode().getId();
            Long idMovie=historicDto.getMovie()==null?null:historicDto.getMovie().getId();

            if ( !repository.alreadyExist(historicDto.getProfile().getId(), idSerie, idMovie) ) {
                newHistoric.setCurrentPlayingTime(historicDto.getCurrentPlayingTime());
                newHistoric.setDateLastVisited(Calendar.getInstance());
                newHistoric.setMovie(historicDto.getMovie());
               // newHistoric.setSerie(historicDto.getSerie());
                newHistoric.setEpisode(historicDto.getEpisode());
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
            newHistoric.setEpisode(historicDto.getEpisode());
            newHistoric.setProfile(historicDto.getProfile());
            return repository.save(newHistoric);
        }

    }*/

}
