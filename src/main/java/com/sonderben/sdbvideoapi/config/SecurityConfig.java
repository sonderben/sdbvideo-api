package com.sonderben.sdbvideoapi.config;

import com.sonderben.sdbvideoapi.security.TokenAuthenticationFilter;
import com.sonderben.sdbvideoapi.security.TokenAuthenticationFilterAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;*/

@Configuration
//@EnableWebSecurity
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
    @Bean
    public TokenAuthenticationFilter createTokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }
    @Bean
    public TokenAuthenticationFilterAdmin createTokenAuthenticationFilterAdmin(){
        return new TokenAuthenticationFilterAdmin();
    }

   // @Override
    protected void configure(/*HttpSecurity http*/) throws Exception {
       /* http
                .cors()
                .and()
                .sessionManagement()
               // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                /*.exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()*/
           //     .authorizeRequests()
           //     .antMatchers(
                 //       "/client/sign_up",
                //        "/client/login",
                //        "/admin/sign_up",
               ///         "/admin/login"
             //   ).permitAll()
             //   .antMatchers("/profile",
               //         "/profile/**",
              //          "/my_list",
              //          "/my_list/**",
             //           "/historic",
             //           "/historic/**"

              // ).hasRole("USER")
              ///  .antMatchers(HttpMethod.GET,"/category").hasRole("USER")
               // .antMatchers(HttpMethod.GET,"/movie").hasRole("USER")
               // .antMatchers(HttpMethod.GET,"/movie/**").hasRole("USER")
               /// .antMatchers(HttpMethod.GET,"/serie").hasRole("USER")
              //  .antMatchers(HttpMethod.GET,"/serie/**").hasRole("USER")
               // .anyRequest()
               // .hasRole("ADMIN")
                //.authenticated()
       // ;*/
       // http.addFilterBefore(createTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
      //  http.addFilterBefore(createTokenAuthenticationFilterAdmin(),UsernamePasswordAuthenticationFilter.class);
    }

}
