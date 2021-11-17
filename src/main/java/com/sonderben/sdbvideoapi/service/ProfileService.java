package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.ProfileDto;
import com.sonderben.sdbvideoapi.entity.Client;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.ForbiddenException;
import com.sonderben.sdbvideoapi.repository.ClientRepository;
import com.sonderben.sdbvideoapi.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService  {
    @Autowired
    ProfileRepository repository;
    @Autowired
    ClientRepository clientRepository;
    public int getNumberOfProfileByIdClient(Long id){
        return repository.getNumberOfProfileByIdClient(id);
    }

    public List<ProfileDto> findAll() {
        List<ProfileDto> profileDtoList=new ArrayList<>();
        List<Profile> profileList=repository.findAll();
        for (int i = 0; i < profileList.size(); i++) {
            profileDtoList.add(Converter.convert(profileList.get(i)));
        }
        return profileDtoList;
    }
    public Profile findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ProfileDto autoCreate(Profile profile){
        ProfileDto entitySave= Converter.convert(repository.save(profile));
        return entitySave;
    }
    public ProfileDto create(Profile profileCreated,Long profileId){
        Client client= clientRepository.getClientByProfileId(profileId);
        if(client==null)
            throw new BadRequestException("Profile don't exist");
        Long clientId=client.getId();
        Integer numberOfScreenByIdClient = clientRepository.getNumberOfScreenByIdClient(clientId);
        Integer numberOfProfile= repository.getNumberOfProfileByIdClient(clientId);
        Profile profile=repository.findById(profileId).orElseThrow();
        Boolean isMainProfile;
        isMainProfile=profile.getIsMainProfile();

        if(isMainProfile){
            if(numberOfProfile<numberOfScreenByIdClient){
                ProfileDto entitySave= Converter.convert(repository.save(profileCreated));
                return entitySave; //new ResponseEntity<>(entitySave, HttpStatus.OK);
            }
            else{
                throw new ForbiddenException("You already have the maximum number of possible Profile ("
                        +numberOfScreenByIdClient+")");
            }
        }
        else {
            if(client.getAllProfilesCanCreateNewProfile()){
                if(numberOfProfile<numberOfScreenByIdClient){
                    ProfileDto entitySave= Converter.convert(repository.save(profileCreated));
                    return entitySave;
                }
                else{
                    throw new ForbiddenException("You already have the maximum number of possible Profile ("
                            +numberOfScreenByIdClient+")");
                }
            }
            else{
                throw new ForbiddenException("you don t have the permission to create a new profile");
            }
        }

    }

    public Profile delete(Long id)  {
        Profile entity=repository.findById(id).orElse(null);
        if(entity!=null)
            repository.delete(entity);
        return entity;
    }


    public Profile update(Profile entity,Long id)  {
        Profile entityFind=repository.findById(id).orElse(null);
        if(entityFind!=null)
            return    repository.save(entity);
        return  null;
    }
}
