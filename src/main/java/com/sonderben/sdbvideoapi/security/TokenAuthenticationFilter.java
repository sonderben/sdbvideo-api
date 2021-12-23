package com.sonderben.sdbvideoapi.security;

import com.sonderben.sdbvideoapi.entity.Client;
import com.sonderben.sdbvideoapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter/* extends OncePerRequestFilter*/ {
  /*  @Autowired
    private ClientService clientService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=getSubjectJwtFromRequest(request);
        try {
            if (StringUtils.hasText(jwt) && clientService.validateToken(jwt)) {
                String email = clientService.getClientEmailFromToken(jwt);
                Client client = clientService.findByClientEmail(email);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(client,
                        null, client.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.err.println(getClass()+": m anndan if");
            }
            System.err.println(getClass()+": m la deyo");
        }catch (Exception e){
            System.err.println(e);
        }
        filterChain.doFilter(request,response);
    }
    private String getSubjectJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }*/
}
