package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.dto.LoginRequestDTO;
import com.sonderben.sdbvideoapi.dto.LoginResponseDTO;
import com.sonderben.sdbvideoapi.entity.Client;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.ClientRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClientService  {
    @Autowired
    ClientRepository repository;
    @Value("${jwt.key}")
    String KEY;

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
        return  null;
    }

    public Boolean isAllProfilesCanCreateNewProfile(Long profileId){
        return  repository.isAllProfilesCanCreateNewProfile(profileId);
    }
    public LoginResponseDTO login(LoginRequestDTO request){
        System.out.println("email: "+request.getEmail()+"\npassword: "+request.getPassword());
        Client client= repository.findClientByEmailAndPassword(request.getEmail(), request.getPassword());
       if(client!=null)
        return Converter.convert(client,createToken(client));
       else
           throw new BadRequestException("wrong password or wrong email");
    }
    public Client getClientByProfileId(Long profileId){
        return repository.getClientByProfileId(profileId);
    }

    public String createToken(Client requestDTO){
        Date init=new Date();
        Date end=new Date(init.getTime()+1_000*60*60*24);
        return Jwts.builder()
                .setSubject(requestDTO.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(end)
                .signWith(SignatureAlgorithm.HS512,KEY)
                .compact();
    }
    public String getClientEmailFromToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception e) {
            //log.error(e.getMessage(), e);
            //throw new ValidateServiceException("Invalid Token");
        }
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
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            return true;
        }catch (UnsupportedJwtException e) {
            //log.error("JWT in a particular format/configuration that does not match the format expected");
        }catch (MalformedJwtException e) {
            //log.error(" JWT was not correctly constructed and should be rejected");
        }/*catch (SignatureException4 e) {
            //log.error("Signature or verifying an existing signature of a JWT failed");
        }*/catch (ExpiredJwtException e) {
            //log.error("JWT was accepted after it expired and must be rejected");
        }
        return false;
    }
}
