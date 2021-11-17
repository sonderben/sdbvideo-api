package com.sonderben.sdbvideoapi.Utiles;

import com.sonderben.sdbvideoapi.dto.*;
import com.sonderben.sdbvideoapi.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static HistoricDto convert(Historic historic,boolean simple){
        if(historic!=null) {
            HistoricDto historicDao = HistoricDto.builder()
                    .id_profile(historic.getProfile().getId())
                    .movie(convert(historic.getMovie(),simple))
                    .currentPlayingTime(historic.getCurrentPlayingTime())
                    .dateLastVisited(historic.getDateLastVisited())
                    .build();
            return historicDao;
        }
         else
             return null;
    }

    public static MyListDto convert(MyList myList, boolean simple){
        if(myList!=null) {
            MyListDto myListDto = MyListDto.builder()
                    .id_profile(myList.getProfile().getId())
                    .movie(convert(myList.getMovie(),simple))
                    .dateAdded(myList.getDateAdded())
                    .build();
            return myListDto;
        }
        else
            return null;
    }

    public static Dto convert(Movie movie, boolean simple){
        if(movie!=null) {
            Dto movieDao=null;
            if(!simple){
                 movieDao = MovieDto.builder()
                        .movieSubtitleList(movie.getMovieSubtitle())
                        .access(movie.getAccess().getName())
                        .actorList(movie.getActorList())
                        .availability(movie.getAvailability())
                        .average(calculateAverageMovie(movie))
                        .categoryList(movie.getCategoryList())
                        .id(movie.getId())
                        .duration(movie.getDuration())
                        .posterUrlMovie(movie.getUrlMovie())
                        .releaseDate(movie.getReleaseDate())
                        .rewardList(movie.getRewardList())
                        .titlesSynopsis(movie.getTitle())
                        .trailerUrlMovie(movie.getTrailerUrlMovie())
                        .urlMovie(movie.getUrlMovie())
                        .build();
            }else
                movieDao= SimpleMovieDto.builder()
                        .id(movie.getId())
                        .duration(movie.getDuration())
                        .posterUrlMovie(movie.getPosterUrlMovie())
                        .releaseDate(movie.getReleaseDate())
                        .title(movie.getTitle())
                        .trailerUrlMovie(movie.getTrailerUrlMovie())
                        .build();

            return movieDao;
        }
        else
            return null;
    }
    public static Float calculateAverageMovie(Movie movie){
        int maxAverage=10;
        int posVote= movie.getPositiveVote()==null?0: movie.getPositiveVote();
        int negVote= movie.getNegativeVote()==null?0:movie.getNegativeVote();
        int total=posVote+negVote;
        Float average= (total < 0) ? 0F : (float)((posVote * maxAverage * 1.0) / total);
        average=(float)Math.round(average*10)/10;

        return average;
    }

    public static ProfileDto convert(Profile profile){
        List<Long>cat=new ArrayList<>();
        if(profile.getCategoryList()!=null)
        for (int i = 0; i < profile.getCategoryList().size(); i++) {
            cat.add(profile.getCategoryList().get(i).getId());
        }
        ProfileDto profileDto=ProfileDto.builder()
                .ageCategory(profile.getAgeCategory())
                .id(profile.getId())
                .isMainProfile(profile.getIsMainProfile())
                .name(profile.getName())
                .pin(profile.getPin())
                .defaultLanguage(profile.getName())
                .urlImg(profile.getUrlImg())
                //.userId(profile.getUser().getId())
                .categoryList(cat)
                .build();
        return profileDto;
    }
    public static LoginResponseDTO convert(Client client,String token){
        List<Profile>profileList=client.getProfileList();
        List<ProfileDto>profileDtoList=new ArrayList<>();
        for (int i = 0; i < profileList.size(); i++) {
            profileDtoList.add(convert(profileList.get(i)));
        }
        ClientDTO userDTO=ClientDTO.builder()
                .email(client.getEmail())
                .id(client.getId())
                .profileList(profileDtoList)
                .build();
        LoginResponseDTO loginResponseDTO=LoginResponseDTO.builder()
                .user(userDTO)
                .token(token)
                .build();
        return loginResponseDTO;
    }
    public static LoginResponseAdministratorDTO convert(Administrator client,String token){
        administratorDTO administratorDTO=com.sonderben.sdbvideoapi.dto.administratorDTO.builder()
                .fullName(client.getFullName())
                .email(client.getEmail())
                .id(client.getId())
                .build();

        LoginResponseAdministratorDTO loginResponseAdministratorDTO=
                LoginResponseAdministratorDTO.builder()
                        .user(administratorDTO)
                        .token(token)
                        .build();
        return loginResponseAdministratorDTO;
    }
    public static administratorDTO convert(Administrator client){
        administratorDTO administratorDTO=com.sonderben.sdbvideoapi.dto.administratorDTO.builder()
                .fullName(client.getFullName())
                .email(client.getEmail())
                .id(client.getId())
                .build();


        return administratorDTO;
    }
}
