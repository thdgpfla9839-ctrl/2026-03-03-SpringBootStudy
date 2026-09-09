package com.sist.web.jwt;

import java.io.IOException;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sist.web.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
 *   1. 사용자 정보 저장 => UserDetailsService
 *   2. 토큰 생성 => Provider
 *   3. 통합 => filter
 *   4. 권한 => URL 접근 => Config
 *   5. 실제 사용자로부터 요청 => Controller
 */

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final CustomUserDetailsService userDetailsService;
	private final JwtAuthenticationProvider provider;
	
	public JwtAuthenticationFilter
	(
			CustomUserDetailsService userDetailsService,
			JwtAuthenticationProvider provider
			
    )
	{
		this.userDetailsService = userDetailsService;
		this.provider = provider;
	}
	
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 토큰 생성
		String token = null;
		// header 확인 => 프론트에서 전송
		// 인증토큰 {Authorization: Bearer ejsljljslglkslkn} 이런식으로 JSON이라서 
		// ejsljljslglkslkn 이게 토큰 => 사용자 정보가 저장돼 있음
		String header = request.getHeader("Authorization");
		if(header!=null && header.startsWith("Bearer "))
		{
			token = header.substring(7);
		}
		
		// 쿠키 확인
		if(token==null && request.getCookies()!=null)
		{
			for(Cookie cookie : request.getCookies())
			{
				if("accessToken".equals(cookie.getName()))
				{
					token=cookie.getValue();
					break;
				}
			}
		}
		
		// JWT 검증
		if(token!=null && provider.validate(token))
		{
			String username = provider.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken
					(user,
							// 인증 정보 => credential(자격정보)
							null,
							user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		// DB에서 사용자 조회
		// Security 인증 객체
		// Security context 저장
		
		// 다음 Filter / Controller 실행
		filterChain.doFilter(request, response);
	}

}
