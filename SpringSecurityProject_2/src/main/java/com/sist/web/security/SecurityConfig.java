package com.sist.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.sist.web.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	// 먼저 url별 권한 부여
	// formLogin 처리
	// logout 처리
	// rememer-me 처리
	// 비밀번호 암호화
	// 로그인 성공과 실패여부
	// 이런 식으로 보통 처리가 된다
	
	private final CustomUserDetailsService userDetailsService;
	// 재정의해주기 => 권한에 따라 접근 여부 확인 / 로그인 / 로그아웃 / 자동 로그인 / 소셜로그인(네이버, 구글, 카카오)
	// csrf.disable( ) => 공격자가 인증된 브라우저에서 저장된 쿠키나 세션 정보를 활용해서 다른 요청을 전송하는 위조를 방지하기 위함
	// authorizeHttpRequests => 인증이나 인가에 필요한 URL 지정할 때
	// URL 처리 방식 
	// 1. anyRequest() => permitAll()      /     denyAll( ): 접근 거부 => 403     
	// 2. requestMatchers => 지정된 url에 권한 부여
	// authenticated() => 인증이 된 사람
	// hasRole(한개), hasAnyRoles("","","") => 권한 여러개 작성 시
	
	// => SecurityFilterChain 안에는 위 모든 주석이 다 포함돼 있음
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		// url별 권한 부여
		http.csrf(csrf-> csrf.disable())
				.authorizeHttpRequests(auth-> auth
						.requestMatchers("/","/join","/login")
						.permitAll()
						.requestMatchers("/user")
						.authenticated()
						.requestMatchers("/admin")
						.hasRole("ADMIN")
						.anyRequest()
						.permitAll() // 게스트 포함 
						)
		// 로그인 처리
				.formLogin(form-> form
						.loginPage("/login")
						.loginProcessingUrl("/login_process")
						.defaultSuccessUrl("/",true)
						.failureHandler(loginFailHandler())
						)
		// 로그아웃 처리
				.logout(logout-> logout
						.logoutSuccessUrl("/")
						);
		return http.build();
	}
	// 비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationFailureHandler loginFailHandler()
	{
		return new LoginFailHandler();
	}
}
