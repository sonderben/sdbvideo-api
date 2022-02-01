package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.ProfileDto;
import com.sonderben.sdbvideoapi.entity.Client;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.ForbiddenException;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.ClientRepository;
import com.sonderben.sdbvideoapi.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository repository;
    @Autowired
    ClientRepository clientRepository;

    public int getNumberOfProfileByIdClient(Long id) {
        return repository.getNumberOfProfileByIdClient(id);
    }

    public List<ProfileDto> findAll() {
        List<ProfileDto> profileDtoList = new ArrayList<>();
        List<Profile> profileList = repository.findAll();
        for (int i = 0; i < profileList.size(); i++) {
            profileDtoList.add(Converter.convert(profileList.get(i)));
        }
        return profileDtoList;
    }

    public Profile findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ProfileDto autoCreate(Profile profile) {
        ProfileDto entitySave = Converter.convert(repository.save(profile));
        return entitySave;
    }

    public ProfileDto create(Profile profileCreated, Long profileId) {
        profileCreated.setIsMainProfile(false);
        Client client = clientRepository.getClientByProfileId(profileId);
        if (client == null)
            throw new BadRequestException("Profile don't exist");
        Long clientId = client.getId();
        Integer numberOfScreenByIdClient = clientRepository.getNumberOfScreenByIdClient(clientId);
        Integer numberOfProfile = repository.getNumberOfProfileByIdClient(clientId);
        Profile profile = repository.findById(profileId).orElseThrow(()->new NoDataFoundException("profile id don't exist"));
        Boolean isMainProfile;
        isMainProfile = profile.getIsMainProfile();

        if (isMainProfile) {
            if (numberOfProfile < numberOfScreenByIdClient) {
                profileCreated.setClient(client);
                ProfileDto entitySave = Converter.convert(repository.save(profileCreated));
                return entitySave; //new ResponseEntity<>(entitySave, HttpStatus.OK);
            } else {
                throw new ForbiddenException("You already have the maximum number of possible Profile ("
                        + numberOfScreenByIdClient + ")");
            }
        } else {
            if (client.getAllProfilesCanCreateNewProfile()) {
                if (numberOfProfile < numberOfScreenByIdClient) {
                    profileCreated.setClient(client);
                    ProfileDto entitySave = Converter.convert(repository.save(profileCreated));
                    return entitySave;
                } else {
                    throw new ForbiddenException("You already have the maximum number of possible Profile ("
                            + numberOfScreenByIdClient + ")");
                }
            } else {
                throw new ForbiddenException("you don t have the permission to create a new profile");
            }
        }

    }

    public Profile delete(Long id) {
        Profile entity = repository.findById(id).orElseThrow(()->new NoDataFoundException("entity don't exist"));
        repository.delete(entity);
        return entity;
    }


    public Profile update(Profile entity, Long id) {
        Profile entityFind = repository.findById(id).orElseThrow(() -> new NoDataFoundException("profile with " + id + " don't exist"));

        entity.setClient(entityFind.getClient());
        entity.setIsMainProfile(entityFind.getIsMainProfile());
      if(entityFind.getIsMainProfile()){
            //main profile edit main profile
            if(entity.getId().equals(id)){
                if (entity.getDefaultLanguage() == null)
                    entity.setDefaultLanguage(entityFind.getDefaultLanguage());
                if (entity.getUrlImg() == null)
                    entity.setUrlImg(entityFind.getUrlImg());
                if (entity.getPin() == null)
                    entity.setPin(entityFind.getPin());
                if (entity.getName() == null)
                    entity.setName(entityFind.getName());
                if (entity.getCategoryList() == null)
                    entity.setCategoryList(entityFind.getCategoryList());
                if (entity.getAgeCategory() == null)
                    entity.setAgeCategory(entityFind.getAgeCategory());
            }
            //main profile edit other profile
            else {
                if (entity.getAgeCategory() == null)
                    entity.setAgeCategory(entityFind.getAgeCategory());
            }
        }

        else{
            //profile edit his own profile
            if(entity.getId().equals(id)){
                if (entity.getDefaultLanguage() == null)
                    entity.setDefaultLanguage(entityFind.getDefaultLanguage());
                if (entity.getUrlImg() == null)
                    entity.setUrlImg(entityFind.getUrlImg());
                if (entity.getPin() == null)
                    entity.setPin(entityFind.getPin());
                if (entity.getName() == null)
                    entity.setName(entityFind.getName());
                if (entity.getCategoryList() == null)
                    entity.setCategoryList(entityFind.getCategoryList());
                if (entity.getAgeCategory() == null)
                    entity.setAgeCategory(entityFind.getAgeCategory());
            }
        }


        return repository.save(entity);


    }

}
