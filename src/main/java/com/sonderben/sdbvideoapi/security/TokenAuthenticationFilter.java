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
import java.util.stream.Collectors;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private ClientService clientService;
    @Autowired
    private AdministratorService administratorService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getSubjectJwtFromRequest(request);
        DecodedJWT decodedJWT = null;
        if (StringUtils.hasText(jwt)) {
            System.err.println(jwt + ": pa gen text");
            decodedJWT = Utile.validateToken(jwt);
        }

        if (decodedJWT != null) {


            try {


                String email = decodedJWT.getSubject();
                // response.setHeader("token", jwt);
                UsernamePasswordAuthenticationToken authentication;
                if (decodedJWT.getClaim("role").asString().equals("ROLE_USER")) {
                    System.err.println(jwt + ": ROLE_USER ROLE_USER");
                    Client client = clientService.findByClientEmail(email);
                    client.getAuthorities().stream().
                            map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())
                            .forEach(x -> System.err.println("client:: " + x));
                    authentication = new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
                } else {
                    Administrator adm = administratorService.findByClientEmail(email);
                    adm.getAuthorities().stream().
                            map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())
                            .forEach(x -> System.err.println("client:: " + x));
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
        } else
            System.err.println(": getSubjectJwtFromRequest");
        return null;
    }
}
