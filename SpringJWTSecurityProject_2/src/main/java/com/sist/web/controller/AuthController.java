package com.sist.web.controller;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.jwt.JwtAuthenticationProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager manager;
	private final JwtAuthenticationProvider provider;
	
	@RequestMapping("/member/login")
	// 와일드 카드인 부분 => 리턴형이 Object
	public ResponseEntity<?> login(@RequestParam(value = "username",required = false) String username,
			@RequestParam(value = "password",required = false) String password)
	{
		// 아이디와 비밀번호의 인증
		Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password)
				);
		// 인증된 사용자 정보 가져오기
		UserDetails user = (UserDetails)auth.getPrincipal();
		// 권한을 부여
		String role = user.getAuthorities()
				.iterator()
				.next()
				.getAuthority();
		// JWT 생성
		String token = provider.createToken(user.getUsername(), role);
		// 쿠키 생성
		ResponseCookie cookie = ResponseCookie.from("accessToken", token)
				.httpOnly(true)
				.secure(false)
				.path("/")
				.maxAge(3600)
				.build();
		// 홈으로 이동
		return ResponseEntity.status(HttpStatus.FOUND).header(
				HttpHeaders.SET_COOKIE,
				cookie.toString()
				)
				.header(HttpHeaders.LOCATION,
						"/home")
				.build();
	}
}
