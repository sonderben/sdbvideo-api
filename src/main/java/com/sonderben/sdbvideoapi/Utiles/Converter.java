package com.sonderben.sdbvideoapi.Utiles;

import com.sonderben.sdbvideoapi.dto.*;
import com.sonderben.sdbvideoapi.entity.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class Converter {
   /* public static HistoricDto convert(Historic historic,boolean simple){
        if(historic!=null) {
            HistoricDto historicDao = HistoricDto.builder()
                    .id(historic.getId())
                    .id_profile(historic.getProfile().getId())
                    .movie(convert(historic.getMovie()))
                    .episode(convert(historic.getEpisode()))
                    .currentPlayingTime(historic.getCurrentPlayingTime())
                    .dateLastVisited(historic.getDateLastVisited())
                    .build();
            return historicDao;
        }
         else
             return null;
    }*/

   /*public static MyListDto convert(MyList myList, boolean simple){
        if(myList!=null) {
            MyListDto myListDto = MyListDto.builder()
                    .id_profile(myList.getProfile().getId())
                    .movie(convert(myList.getMovie()))
                    .dateAdded(myList.getDateAdded())
                    .serie(convert(myList.getSerie()))
                    .build();
            return myListDto;
        }
        else
            return null;
    }*/

    /* public static SimpleMovieDto convert(Movie movie){

         if(movie!=null) {
             SimpleMovieDto movieDao;

                 movieDao= SimpleMovieDto.builder()
                         .id(movie.getId())
                         .duration(movie.getDuration())
                         .posterUrlMovie(movie.getPoster())
                         .releaseDate(movie.getReleaseDate())
                         .title(movie.getTitleSynopsises())
                         .trailerUrlMovie(movie.getTrailer())
                         .build();

             return movieDao;
         }
         else
             return null;
     }*/
    public static Float calculateAverage(Movie movie) {
        int maxAverage = 10;
        int posVote = movie.getPositiveVote() == null ? 0 : movie.getPositiveVote();
        int negVote = movie.getNegativeVote() == null ? 0 : movie.getNegativeVote();
        int total = posVote + negVote;
        Float average = (total < 0) ? 0F : (float) ((posVote * maxAverage * 1.0) / total);
        average = (float) Math.round(average * 10) / 10;

        return average;
    }

    public static Float calculateAverage(Serie movie) {
        int maxAverage = 10;
        int posVote = movie.getPositiveVote() == null ? 0 : movie.getPositiveVote();
        int negVote = movie.getNegativeVote() == null ? 0 : movie.getNegativeVote();
        int total = posVote + negVote;
        Float average = (total < 0) ? 0F : (float) ((posVote * maxAverage * 1.0) / total);
        average = (float) Math.round(average * 10) / 10;

        return average;
    }

    public static ProfileDto convert(Profile profile) {
        List<Long> cat = new ArrayList<>();


        if (profile.getCategoryList() != null) {
            Iterator<Category> categoryIterator = profile.getCategoryList().iterator();
            while (categoryIterator.hasNext()) {
                cat.add(categoryIterator.next().getId());
            }
        }

        ProfileDto profileDto = ProfileDto.builder()
                .ageCategory(profile.getAgeCategory())
                .id(profile.getId())
                .isMainProfile(profile.getIsMainProfile())
                .name(profile.getName())
                .pin(profile.getPin())
                .defaultLanguage(profile.getDefaultLanguage())
                .urlImg(profile.getUrlImg())
                .categoryList(cat)
                .build();
        return profileDto;
    }

    public static LoginResponseAdministratorDTO convert(Administrator client, String token) {
        administratorDTO administratorDTO = com.sonderben.sdbvideoapi.dto.administratorDTO.builder()
                .fullName(client.getFullName())
                .email(client.getEmail())
                .id(client.getId())
                .build();

        LoginResponseAdministratorDTO loginResponseAdministratorDTO =
                LoginResponseAdministratorDTO.builder()
                        .user(administratorDTO)
                        .token(token)
                        .build();
        return loginResponseAdministratorDTO;
    }

    public static administratorDTO convert(Administrator client) {
        administratorDTO administratorDTO = com.sonderben.sdbvideoapi.dto.administratorDTO.builder()
                .fullName(client.getFullName())
                .email(client.getEmail())
                .id(client.getId())
                .build();


        return administratorDTO;
    }

    public static ClientResponseDto convert(Client client) {
        return ClientResponseDto.builder()
                .allProfilesCanCreateNewProfile(client.getAllProfilesCanCreateNewProfile())
                .birthday(client.getBirthday())
                .city(client.getCity())
                .dateClientCreate(client.getDateClientCreate())
                .country(client.getCountry())
                .id(client.getId())
                .department(client.getDepartment())
                .access(client.getPlan())
                .firstName(client.getFirstName())
                .LastName(client.getLastName())
                .password(client.getPassword())
                .postalCode(client.getPostalCode())
                .region(client.getRegion())
                .telephone(client.getTelephone())
                .email(client.getEmail())
                //.mainProfile(client.getProfileList().get(0))
                .build();
    }

    public static Client convert(ClientRequestDto dto) {
       /* Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Timestamp.from(Instant.now()).getTime());
       // entity.setDateClientCreate(calendar);


       // DateFormat df = new SimpleDateFormat("dd MM yyyy");
        DateFormat df = new SimpleDateFormat("yyyy MM dd");
        Date date = null;
        try {
            date = df.parse(dto.getBirthday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);*/
        return Client.builder()
                .birthday(dto.getBirthday())
                .dateClientCreate(Calendar.getInstance())
                .plan(dto.getAccess())
                .allProfilesCanCreateNewProfile(dto.getAllProfilesCanCreateNewProfile())
                .city(dto.getCity())
                .sex(dto.getSex())
                .country(dto.getCountry())
                .department(dto.getDepartment())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .LastName(dto.getLastName())
                .password(dto.getPassword())
                .postalCode(dto.getPostalCode())
                .profileList(null)
                .region(dto.getRegion())
                .telephone(dto.getTelephone())
                .build();
    }

    /*public static List<SimpleSerieDto> convert(List<Serie> serie){
        List<SimpleSerieDto>serieDtos=new ArrayList<>();
        List<Long>idSeasons;

        for (int i=0;i<serie.size();i++){
            Set<Season>seasons=serie.get(i).getSeasons();
            idSeasons=new ArrayList<>();
            for (int a=0;a<seasons.size();a++){
                //seasons.ite
                //idSeasons.add(seasons.get(a).getId());
                Iterator<Season>seasonIterator=serie.get(i).getSeasons().iterator();
                while (seasonIterator.hasNext()) {
                    Season season = seasonIterator.next();
                    idSeasons.add(season.getId());
                }
            }













            SimpleSerieDto a= SimpleSerieDto.builder()
                    .idSeasons(idSeasons)
                    .categories(serie.get(i).getCategories())
                    .ageCategory(serie.get(i).getAgeCategory())
                    .negativeVote(serie.get(i).getNegativeVote())
                    .numView(serie.get(i).getNumView())
                    .positiveVote(serie.get(i).getPositiveVote())
                    .rewards(serie.get(i).getRewards())
                    .titleSynopsises(serie.get(i).getTitleSynopsises())
                    .build();
            serieDtos.add(a);
        }

        return serieDtos;
    }

    public static Dto convert(Serie serie){


        if(serie!=null) {
            Set<Season> seasons=serie.getSeasons();
            List<Long>idSeasons=new ArrayList<>();
            String trailer=null;
            String poster=null;
            System.err.println("size: "+seasons.size());
          //  for (int i=0;i< seasons.size();i++) {
                Iterator<Season>seasonIterator= seasons.iterator();int i=0;
                while (seasonIterator.hasNext()){
                    Season season=seasonIterator.next();
                    idSeasons.add(season.getId());
                    if(i==0){
                        trailer=season.getTrailer();
                        poster=season.getPoster();
                    }
                    i+=1;
                }


           // }



            Dto movieDao;

                movieDao = SerieDto.builder()
                        .idSeasons(idSeasons)
                        .id(serie.getId())
                        .ageCategory(serie.getAgeCategory())
                        .trailer(trailer)
                        .poster(poster)
                        .average(calculateAverage(serie))
                        .titleSynopsis(serie.getTitleSynopsises())
                        .categories(serie.getCategories())
                        .rewards(serie.getRewards())
                        .build();



            return movieDao;
        }
        else
            return null;
    }*/
    public static Dto convert(Episode epo) {
        return EpisodeDto.builder()
                .duration(epo.getDuration())
                .id(epo.getId())
                .subtitles(epo.getSubtitles())
                .titleSynopses(epo.getTitleSynopses())
                .url(epo.getUrl())
                .poster(epo.getPoster())
                .build();
    }

    public static Set<EpisodeDto> convertToEpisodeDto(Season season) {
        Set<Episode> episodeSet = season.getEpisodes();
        Set<EpisodeDto> episodeDtoSet = new HashSet<>();
        Iterator<Episode> episodeIterator = episodeSet.iterator();
        while (episodeIterator.hasNext()) {
            //EpisodeDto episode=( (EpisodeDto) convert( episodeIterator.next() ) );//episodeIterator.next();
            episodeDtoSet.add(((EpisodeDto) convert(episodeIterator.next())));
        }
        return episodeDtoSet;
    }

    public static SeasonResponseDto convert(Season entity) {
        return SeasonResponseDto.builder()
                .episodes(convertToEpisodeDto(entity))
                .dateAdded(entity.getDateAdded())
                .id(entity.getId())
                .release(entity.getRelease())
                .titleSynopseses(entity.getTitleSynopseses())
                .poster(entity.getPoster())
                .trailer(entity.getTrailer())
                .build();

    }

    public static List<VideoDto> convert(List<Video> video) {
        List<VideoDto> videoDtos = new ArrayList<>();
        video.forEach(v -> {
            videoDtos.add(
                    new VideoDto(v.getId(), v.getId_video(), v.getIsMovie(), v.getReleaseDate(), v.getPoster(), v.getTrailer(),
                            v.getDuration(), v.getCurrentPlayingTime(), v.getDescription(), v.getAgeCategory(),
                            getNamesCategories(v.getCategories()) ,getIdEpisode(v.getEpisode())));
        });
        return videoDtos;
    }

    public static VideoDto convert(Video v) {
        //v.getS
        return new VideoDto(v.getId(), v.getId_video(), v.getIsMovie(), v.getReleaseDate(), v.getPoster(), v.getTrailer(),
                v.getDuration(), v.getCurrentPlayingTime(), v.getDescription(), v.getAgeCategory(),
                getNamesCategories(v.getCategories()), getIdEpisode(v.getEpisode()));

    }

    private static List<String> getNamesCategories(Set<Category> categories) {
        List<String> categoriesNames = new ArrayList<>();
        Iterator<Category> categoryIterator = categories.iterator();
        while (categoryIterator.hasNext()) {
            categoriesNames.add(categoryIterator.next().getName());
        }
        return categoriesNames;
    }

    public static MovieDto convert(Movie movie) {
        return MovieDto.builder()
                .availability(movie.getAvailability())
                .id(movie.getId())
                .actors(movie.getActors())
                .rewards(movie.getRewards())
                .titleSynopses(movie.getTitleSynopses())
                .subtitles(movie.getSubtitles())
                .url(movie.getUrl())
                .build();
    }

    private static Long getIdEpisode(Episode episode) {
        return episode == null ? null : episode.getId();
    }
}
