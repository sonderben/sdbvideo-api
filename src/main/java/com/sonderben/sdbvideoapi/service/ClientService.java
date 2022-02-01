package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.dto.LoginRequestDTO;
import com.sonderben.sdbvideoapi.dto.LoginResponseDTO;
import com.sonderben.sdbvideoapi.dto.ProfileDto;
import com.sonderben.sdbvideoapi.dto.SessionDto;
import com.sonderben.sdbvideoapi.entity.Client;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.entity.aws.Session;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.ForbiddenException;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.ClientRepository;
import com.sonderben.sdbvideoapi.repository.aws.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;
    @Autowired
    SessionRepository sessionRepository;


    public int getNumberOfScreenByIdClient(Long id) {
        return repository.getNumberOfScreenByIdClient(id);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Client signUp(Client client) {
        return repository.save(client);
    }

    public Client delete(Long id) {
        Client entity = repository.findById(id).orElseThrow(()->new NoDataFoundException(" Entity don't exist"));
            repository.delete(entity);
        return entity;
    }


    public Client update(Client entity, Long id) {
        Client entityFind = repository.findById(id).orElseThrow(()->new NoDataFoundException("Client with "
                + id + " don't exist"));
        if(entity.getBirthday()!=null)
        entityFind.setBirthday(entity.getBirthday());
        if(entity.getCity()!=null)
        entityFind.setCity(entity.getCity());
        if(entity.getCountry()!=null)
        entityFind.setCountry(entity.getCountry());
        if(entity.getAllProfilesCanCreateNewProfile()!=null)
        entityFind.setAllProfilesCanCreateNewProfile(entity.getAllProfilesCanCreateNewProfile());
        if(entity.getFirstName()!=null)
        entityFind.setFirstName(entity.getFirstName());
        if(entity.getLastName()!=null)
        entityFind.setLastName(entity.getLastName());
        if(entity.getDepartment()!=null)
        entityFind.setDepartment(entity.getDepartment());
        if(entity.getPostalCode()!=null)
        entityFind.setPostalCode(entity.getPostalCode());
        if(entity.getRegion()!=null)
        entityFind.setRegion(entityFind.getRegion());

            return repository.save(entityFind);

    }

    public Boolean isAllProfilesCanCreateNewProfile(Long profileId) {
        return repository.isAllProfilesCanCreateNewProfile(profileId);
    }

    public void logout(SessionDto sessionDto) {
        sessionRepository.delete(sessionDto.getEmail(), sessionDto.getDevice());
    }

    public void findSession(String email) {
        sessionRepository.find(email, "");
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        Client client = repository.findClientByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (client != null) {
            String token = Utile.createToken(client.getEmail(), "ROLE_USER", loginRequest.getDevice());
            Long expToken = Utile.validateToken(token).getExpiresAt().getTime();
            Session session = Session.builder()
                    .email(client.getEmail())
                    .idDevice(loginRequest.getDevice())
                    .lastConnection(new Date().getTime())
                    .location(loginRequest.getLocation()==null?"":loginRequest.getLocation())
                    .expToken(expToken)
                    .connection(new Date().getTime())
                    .token(token)
                    .build();

          //  sessionRepository.save(session);
            return new LoginResponseDTO(token);
        } else
            throw new NoDataFoundException("wrong password or wrong email");
    }

    public Client getClientByProfileId(Long profileId) {
        return repository.getClientByProfileId(profileId);
    }

    public List<ProfileDto> getProfileByEmailClient(String emailClient) {
        Client client = repository.findByEmail(emailClient);
        if (client == null)
            throw new NoDataFoundException("email don't exist");
        List<ProfileDto> profileDtoList = new ArrayList<>();
        List<Profile> profiles = client.getProfileList();
        for (int i = 0; i < profiles.size(); i++) {
            profileDtoList.add(Converter.convert(profiles.get(i)));
        }

        return profileDtoList;
    }



    public Client findByClientEmail(String email) {
        Client client = repository.findByEmail(email);
        if (client != null)
            return client;
        else
            throw new NoDataFoundException("email don't exist");
    }

    public List<Session> findAllSession(Long idProfile) {
        String email = repository.getEmailByProfileIdWhenIsMainProfile(idProfile);
        if (email == null)
            throw new ForbiddenException("permission denied or profile don't exist");
        return sessionRepository.findAll(email);
    }

    public void deleteAllSession(Long idProfile) {
        String email = repository.getEmailByProfileIdWhenIsMainProfile(idProfile);
        if (email == null)
            throw new ForbiddenException("permission denied or profile don't exist");
        sessionRepository.deleteAllSession(email);
    }

    public void deleteSession(Long idProfile,String device) {

        String email = repository.getEmailByProfileId(idProfile);
        if (email == null)
            throw new ForbiddenException("permission denied or profile don't exist");
        sessionRepository.delete(email, device);
    }

    public boolean tokenExistInDataBase(String email, String device) {
        if (email == null || device == null)
            return false;
        Session session = sessionRepository.find2(email, device);
        if (session == null)
            return false;
        return true;
    }
}
