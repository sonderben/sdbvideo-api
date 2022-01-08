package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.dto.LoginRequestDTO;
import com.sonderben.sdbvideoapi.dto.LoginResponseDTO;
import com.sonderben.sdbvideoapi.entity.Client;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClientService  {
    @Autowired
    ClientRepository repository;
   // @Value("${jwt.key}")
    String KEY="awed8kfSdDSa8!m";

    public int getNumberOfScreenByIdClient(Long id){
        return repository.getNumberOfScreenByIdClient(id);
    }
    public List<Client> findAll() {
        return repository.findAll();
    }
    public Client findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Client signUp(Client client){
        return repository.save(client);
    }

    public Client delete(Long id)  {
        Client entity=repository.findById(id).orElse(null);
        if(entity!=null)
            repository.delete(entity);
        return entity;
    }


    public Client update(Client entity,Long id)  {
        Client entityFind=repository.findById(id).orElse(null);
        if(entityFind!=null)
         return    repository.save(entity);
        throw new BadRequestException("Client with "+id+" don't exist");
    }

    public Boolean isAllProfilesCanCreateNewProfile(Long profileId){
        return  repository.isAllProfilesCanCreateNewProfile(profileId);
    }
    public LoginResponseDTO login(LoginRequestDTO request){

        Client client= repository.findClientByEmailAndPassword(request.getEmail(), request.getPassword());
       if(client!=null)
        return Converter.convert(client, Utile.createToken( client.getEmail(),"ROLE_USER","android") );
       else
           throw new BadRequestException("wrong password or wrong email");
    }
    public Client getClientByProfileId(Long profileId){
        return repository.getClientByProfileId(profileId);
    }


    public String getClientEmailFromToken(String jwt) {
return null;
    }
    public Client findByClientEmail(String email){
        Client client= repository.findClientByEmail(email);
       if(client!=null)
           return client;
       else
           throw new NoDataFoundException("email don't exist");
    }
    public boolean validateToken(String token) {
        return false;
    }
}
