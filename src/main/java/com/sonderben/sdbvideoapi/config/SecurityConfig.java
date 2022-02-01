package com.sonderben.sdbvideoapi.config;

import com.sonderben.sdbvideoapi.security.TokenAuthenticationFilter;
import com.sonderben.sdbvideoapi.security.TokenAuthenticationFilterAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public TokenAuthenticationFilter createTokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .exceptionHandling()
                //.authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/*/sign_up",
                        "/*/login",
                        "/*/login/",
                        "/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.woff2"
                ).permitAll()
                .antMatchers(HttpMethod.GET,"/access","/access","/image").permitAll()
                .antMatchers(HttpMethod.GET,"/client/profiles").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.GET,"/client/logout").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/client/logout/").hasRole("USER")
                .mvcMatchers(HttpMethod.GET,"/client/sessions**").hasRole("USER")
                .mvcMatchers(HttpMethod.GET,"/client/sessions/**").hasRole("USER")

                .mvcMatchers(HttpMethod.DELETE,"/client/sessions**").hasRole("USER")
                .mvcMatchers(HttpMethod.DELETE,"/client/sessions/**").hasRole("USER")

                .mvcMatchers(HttpMethod.DELETE,"/client/session**").hasRole("USER")
                .mvcMatchers(HttpMethod.DELETE,"/client/session/**").hasRole("USER")

                .antMatchers(HttpMethod.GET,"/category").hasAnyRole("USER","ADMIN")
                .antMatchers("/episode","season").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/movie/all/**","/movie/search/**","/movie/category/**",
                        "/season/**","/episode/**").hasAnyRole("USER","ADMIN")
                .mvcMatchers(HttpMethod.GET,"/serie*").hasAnyRole("USER","ADMIN")
                .mvcMatchers(HttpMethod.GET,"/serie/category/**","/serie/search/**").hasAnyRole("USER","ADMIN")

                .antMatchers(HttpMethod.PUT,"/client/*").hasRole("USER")
                .antMatchers("/profile/").hasRole("ADMIN")
                .antMatchers("/profile/*").hasRole("USER")

                .mvcMatchers(HttpMethod.POST,"/historic").hasRole("USER")
                .mvcMatchers(HttpMethod.PUT,"/historic").hasRole("USER")
                .mvcMatchers(HttpMethod.DELETE,"/historic/*").hasRole("USER")
                .mvcMatchers(HttpMethod.GET,"/historic/search_by/**").hasRole("USER")

                .mvcMatchers(HttpMethod.POST,"/my_list").hasRole("USER")
                .mvcMatchers(HttpMethod.PUT,"/my_list").hasRole("USER")
                .mvcMatchers(HttpMethod.DELETE,"/my_list/*").hasRole("USER")
                .mvcMatchers(HttpMethod.GET,"/my_list/search_by/**").hasRole("USER")
                .anyRequest().hasRole("ADMIN");
                //.authenticated();

        http.addFilterBefore(createTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
