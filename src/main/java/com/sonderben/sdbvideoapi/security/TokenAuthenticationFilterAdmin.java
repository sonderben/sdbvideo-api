package com.sonderben.sdbvideoapi.security;

import com.sonderben.sdbvideoapi.entity.Administrator;
import com.sonderben.sdbvideoapi.service.AdministratorService;
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

public class TokenAuthenticationFilterAdmin /*extends OncePerRequestFilter */{
   /* @Autowired
    private AdministratorService administratorService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=getSubjectJwtFromRequest(request);
        try {
            if (StringUtils.hasText(jwt) && administratorService.validateToken(jwt)) {
                String email = administratorService.getClientEmailFromToken(jwt);
                Administrator admin = administratorService.findByClientEmail(email);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(admin,
                        null, admin.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){}
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
