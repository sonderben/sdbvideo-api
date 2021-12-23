package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.Utiles.Converter;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.dto.LoginAdministratorRequestDTO;
import com.sonderben.sdbvideoapi.dto.LoginResponseAdministratorDTO;
import com.sonderben.sdbvideoapi.entity.Administrator;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.AdministratorRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    AdministratorRepository repository;
    //@Value("${jwt.key}")
    String KEY="awed8kfSdDSa8!m";


    public List<Administrator> findAll() {
        return repository.findAll();
    }

    public Administrator findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Administrator signUp(Administrator client) {
        return repository.save(client);
    }

    public Administrator delete(Long id) {
        Administrator entity = repository.findById(id).orElse(null);
        if (entity != null)
            repository.delete(entity);
        return entity;
    }


    public Administrator update(Administrator entity, Long id) {
        Administrator entityFind = repository.findById(id).orElse(null);
        if (entityFind != null)
            return repository.save(entity);
        throw new BadRequestException("Administrator con admin "+id+" don't exist") ;
    }


    public LoginResponseAdministratorDTO login(LoginAdministratorRequestDTO request) {

        Administrator client = repository.findAdministratorByEmailAndPassword(request.getEmail(), request.getPassword());
        if (client != null)
            return Converter.convert(client, Utile.createToken(client.getEmail()));
        else
            throw new BadRequestException("wrong password or wrong email");
    }


    /*public String createToken(Administrator requestDTO) {
        Date init = new Date();
        Date end = new Date(init.getTime() + 1_000 * 60 * 60 * 24);
        String jwt= Jwts.builder()
                .setSubject(requestDTO.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(end)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
        System.err.println(jwt);
        return jwt;
    }*/

    public String getClientEmailFromToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception e) {
            //log.error(e.getMessage(), e);
            //throw new ValidateServiceException("Invalid Token");
        }
        return null;
    }

    public Administrator findByClientEmail(String email) {
        Administrator client = repository.findAdministratorByEmail(email);
        if (client != null)
            return client;
        else
            throw new NoDataFoundException("email don't exist");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException e) {
            //log.error("JWT in a particular format/configuration that does not match the format expected");
        } catch (MalformedJwtException e) {
            //log.error(" JWT was not correctly constructed and should be rejected");
        }/*catch (SignatureException4 e) {
            //log.error("Signature or verifying an existing signature of a JWT failed");
        }*/ catch (ExpiredJwtException e) {
            //log.error("JWT was accepted after it expired and must be rejected");
        }
        return true;
    }
}
