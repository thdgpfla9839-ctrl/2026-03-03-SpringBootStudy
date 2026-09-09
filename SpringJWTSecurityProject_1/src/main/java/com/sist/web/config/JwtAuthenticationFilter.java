package com.sist.web.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


// 전체 동작하는 순서 : 
/*
 *  => 로그인 요청(Post방식으로 넘어옴 -> /member/login)
 *  => AuthController(AuthenticationManager.authenticate()를 거쳐서 인증 여부 확인을 함)
 *  => CustomUserDetailService(사용자 검색)
 *  => UserDetails(이 안에 저장을 함 => 인증 성공의 과정을 거침 )
 *  => Authentication(인증 성공이 넘어옴)
 *  => AuthenticationProvider(createtoken())
 *  => JWT 발급 (발급받은 토큰은 기간이 있음 => Cookie에 저장됨)
 *  => 브라우저
 *  => JwtAuthenticationFilter(Authentication 헤더 확인 / 토큰 추출 / JWT 검증 / username 추출 / UserDetails 조회 / SecurityContext 정보 저장)
 *  => Controller에 접근하게 만든다
 */
// JWT 개념 : JsonWebToken의 약자
/*
 *  => 해당 토큰은 xxxxx.yyyy.zzzzz 이런식으로 만들어짐
 *  => xx~ 이 부분은 Header
 *  => yy~ 이 부분은 Payload 실제 정보가 저장되는 부분
 *  => zz~ 이 부분은 signature 위변조 방지/ 암호화 서명 
 *  => 개발자가 만드는 게 아니라 자동으로 만들어줌
 *  => 토큰이 만들어지는 과정
 */
// Spring Security 동작 과정
/*
 * => session 방식
 * => 로그인 처리 시 아이디/비밀번호 확인
 * => Session이 생성
 * => JSESSIONID 쿠키 저장
 * => 다음 요청
 * => Session 확인
 * => 로그인 사용자 확인(서버에서 로그인 상태를 가지고 있다)
 */
// JWT 동작 과정
/*
 * => 로그인
 * => id/pw 확인
 * => JWT 생성
 * => 클라이언트가 JWT 저장
 * => 다음 요청
 * => Authorization (Bearer JWT)
 * => 서버가 JWT 검증
 * => 로그인 사용자 확인(Session 없이 Cookie만 이용해서 저장한다)
 */
// 각 클래스의 역할
// AuthController : 로그인 요청 시 처리(인증 => JWT 토큰 생성 / 쿠키 발급)
// JwtTokenProvider : JWT 토큰을 직접 생성 후 위조여부 확인(validate()) => 사용자의 아이디와 권한 추출, 데이터베이스와 연동이 될 수 있다
// JwtAuthenticationFilter : Jwt 토큰을 찾아서 유효한지 검사 => 스프링 시큐리티 로그인 상태 등록
// JwtSecurityConfig : 페이지 규칙
// Controller -> ThymeLeaf로 어떻게 넘어가는지



// JwtAuthenticationFilter => 사용자 요청 시마다 JWT 토큰을 검사
public class JwtAuthenticationFilter 
extends OncePerRequestFilter // OncePerRequestFilter 한번은 무조건 수행
{
	// 사용자 정보를 데이터베이스를 연동해서 데이터 추출
   private final UserDetailsService userDetailsService;
   // JWT 토큰을 만들어서 검증하는 역할 수행
   private final JwtTokenProvider provider;
   public JwtAuthenticationFilter(
		   UserDetailsService userDetailsService,
		   JwtTokenProvider provider   
   )
   {
	   this.userDetailsService=userDetailsService;
	   this.provider=provider;
   }
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		// token : JWt 토큰을 저장할 변수
		String token = null;

		// 1. Authorization Header 확인
		String header = request.getHeader("Authorization");
        System.out.println("header="+header);
		if (header != null && header.startsWith("Bearer ")) {

		    token = header.substring(7);
		}

		// 2. Header가 없으면 Cookie 확인
		if (token == null && request.getCookies() != null) {

		    for (Cookie cookie : request.getCookies()) {

		        if ("accessToken".equals(cookie.getName())) {

		            token = cookie.getValue();
		            System.out.println("token="+token);
		            break;
		        }
		    }
		}

		// 3. JWT가 존재하면 인증작업을 거침
		// => 인증을 거친다(유효기간을 확인 및 사용자 추출)
		if (token != null && provider.validate(token)) {

		    String username =
		            provider.getUsername(token);

		    UserDetails user =
		            userDetailsService
		                    .loadUserByUsername(username);

		    UsernamePasswordAuthenticationToken auth =
		            new UsernamePasswordAuthenticationToken(
		                    username,
		                    null,
		                    user.getAuthorities()
		            );

		    SecurityContextHolder
		            .getContext()
		            .setAuthentication(auth);
		}

		// 정상적으로 컨트롤러가 다음을 수행할 수 있게 처리해줌
		filterChain.doFilter(request, response);
	}

}