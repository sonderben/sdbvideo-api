package com.sonderben.sdbvideoapi.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.entity.Administrator;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.service.AdministratorService;
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

public class TokenAuthenticationFilterAdmin extends OncePerRequestFilter {
    @Autowired
    private AdministratorService administratorService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=getSubjectJwtFromRequest(request);
        DecodedJWT decodedJWT=null;

        try {

            boolean validateToken=decodedJWT==null?false:true;
            if (StringUtils.hasText(jwt) && /*administratorService.*/validateToken) {
                String email = decodedJWT.getSubject();
                Administrator admin = administratorService.findByClientEmail(email);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(admin,
                        null, admin.getAuthorities());
                admin.getAuthorities().stream().
                        map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                        .forEach(x-> System.err.println("admin:: "+x));
               response.setHeader("token",jwt);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else {throw new BadRequestException("jwt don't found");}
        }catch (Exception e){throw e;}
        filterChain.doFilter(request,response);
    }
    private String getSubjectJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        System.err.println(bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        throw new BadRequestException("jwt don't found");
    }
}
