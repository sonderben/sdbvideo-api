package com.sonderben.sdbvideoapi.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.entity.Administrator;
import com.sonderben.sdbvideoapi.entity.Client;
import com.sonderben.sdbvideoapi.service.AdministratorService;
import com.sonderben.sdbvideoapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private ClientService clientService;
    @Autowired
    private AdministratorService administratorService;
    private Long DAY_IN_MILLISECOND= Long.valueOf(24*3_600*1_000);
    private Long CURRENT_DATE_IN_MILLISECOND=new Date().getTime();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = getSubjectJwtFromRequest(request);
        DecodedJWT decodedJWT = null;
        String temEmmail=null;
        String temDevice=null;
        String role=null;
        if (StringUtils.hasText(jwt)) {
            decodedJWT = Utile.validateToken(jwt);
            if(decodedJWT!=null){
                temEmmail=decodedJWT.getSubject();
                temDevice=decodedJWT.getClaim("device_id").asString();
                role=decodedJWT.getClaim("role").asString();
            }
        }


        /*
        if jwt don' t expire and jwt exist in database or user is a admin
         */
        if (decodedJWT != null/* && ( clientService.tokenExistInDataBase(temEmmail,temDevice) || role.equals("ROLE_ADMIN") )*/  ) {

            try {


                String email = decodedJWT.getSubject();


                UsernamePasswordAuthenticationToken authentication;
                if (decodedJWT.getClaim("role").asString().equals("ROLE_USER")) {
                    Client client = clientService.findByClientEmail(email);
                    /*client.getAuthorities().stream().
                            map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())
                            .forEach(x -> System.err.println("client:: " + x));*/
                    authentication = new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
                    /*if(diffBetweenDate(decodedJWT)==2){
                        String refreshToken=Utile.createToken(temEmmail,role,temDevice);
                        response.setHeader("refreshToken",refreshToken);
                    }*/
                } else {
                    Administrator adm = administratorService.findByClientEmail(email);
                    /*adm.getAuthorities().stream().
                            map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())
                            .forEach(x -> System.err.println("client:: " + x));*/
                    authentication = new UsernamePasswordAuthenticationToken(adm, null, adm.getAuthorities());
                }

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);


            } catch (Exception e) {
                System.err.println(e);
            }

        }
        filterChain.doFilter(request, response);
    }

    private String getSubjectJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    public int diffBetweenDate(DecodedJWT decodedJWT){
        Date d1 =new Date(System.currentTimeMillis());
        System.out.println("current: "+d1.getTime());
        Date expiresAt= decodedJWT.getExpiresAt();
        long diff = expiresAt.getTime() - d1.getTime();
        System.err.println( TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) );
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
