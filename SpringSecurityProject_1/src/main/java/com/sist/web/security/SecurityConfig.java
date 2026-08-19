package com.sist.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 컨피큐레이션 => 시큐리티나 웹소켓 사용할 때 씀
@EnableWebSecurity
public class SecurityConfig {

	@Bean // 요즘은 <bean> 태그가 @Bean 어노테이션으로 바뀜
	public SecurityFilterChain filterChain(HttpSecurity http)
	throws Exception
	{
		// 1. 접근 권한
		// 2. 로그인을 어떻게 처리할지
		// 3. 로그아웃 처리
		// 4. 자동 로그인 처리
		// 이 4가지를 어떻게 처리할지가 filterChain
		
		
		// 인증 => 권한 부여
		http
		.csrf(csrf->csrf.disable()) // 위조를 방지하는 목적
		.authorizeHttpRequests(auth-> auth
				.requestMatchers("/","/login") // 경로를 가져올 때 사용
				.permitAll() // 누구나 접근이 가능함
				.requestMatchers("/user")
				.authenticated()
				.requestMatchers("/admin")
				.hasRole("ADMIN")
				.anyRequest()
				.permitAll()
				)
		// 로그인 처리
		.formLogin(form-> form
				.loginPage("/login")
				.loginProcessingUrl("/login_process") // 스프링시큐리티에서 먼저 /login시 post방식으로 넘어가면 컨트롤러가 아니가 시큐리티 인터셉터로 처리하는 방식이다
				.defaultSuccessUrl("/",true)
				.failureUrl("/login?error")
				.permitAll()
				)
		// 로그아웃 처리
		.logout(logout-> logout
				.logoutSuccessUrl("/") // 로그아웃시 홈으로(메인) 이동한다 => html에 홈으로라고 설정함
				);
		// => 로그인과 로그아웃은 자체 내에서 처리됨
		
		return http.build();
	}
}
