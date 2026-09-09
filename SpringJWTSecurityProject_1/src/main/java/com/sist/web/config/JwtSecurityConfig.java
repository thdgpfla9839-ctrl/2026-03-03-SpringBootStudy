package com.sist.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// 환경설정 파일
@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
       UserDetailsService uds,
       JwtTokenProvider provider
    )
    {
    	return new JwtAuthenticationFilter(uds, provider);
    }
    @Bean
    public SecurityFilterChain fillerChain(
       HttpSecurity http,
       JwtAuthenticationFilter filter
    )throws Exception
    {
    	http
         // 위조방지
    	.csrf(csrf-> csrf.disable())
    	 .sessionManagement(session-> 
    	    session.sessionCreationPolicy(
    	      SessionCreationPolicy.STATELESS
    	    ))
    	 .authorizeHttpRequests(auth->auth
    		    // 권한에 따라 접근범위 여부
    		   .requestMatchers("/","/login").permitAll()
    		   .requestMatchers("/admin").hasRole("ADMIN")
    		   .anyRequest().permitAll()
    	  )
    	 .addFilterBefore(filter, 
    			 UsernamePasswordAuthenticationFilter.class);
    	return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration config
    )throws Exception
    {
    	return config.getAuthenticationManager();
    }
    
    
}