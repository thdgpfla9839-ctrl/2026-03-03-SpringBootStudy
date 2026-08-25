package com.sist.web.service;
/*
 *  인증처리 순서)
 *  1. 사용자가 로그인 요청 => username(ID),password(PWD) => 이 데이터를 받을 때 formlogin에서 값을 받는다
 *  2. UsernamePasswordAuthenticationFilter 요청을 인터셉트
 *  3. UsernamePasswordAuthenticationToken => 인증용 객체 생성
 *  4. UsernamePasswordAuthenticationToken => AuthenticationManager에 전송
 *  5. AuthenticationProvider를 찾아감
 *  6. UserDetailsService를 통해서 DB에서 사용자 정보를 가져온다(사용자 정보 조회)
 *  7. UserDetails안에서 사용자 정보를 가지고 id와 비번을 확인을 통해 데이터 조회
 *  8. 인증에 성공하면 SecurityContext에 저장 
 *  
 *  
 *  => 기억해야할 3가지
 *  1) UserDetails
 *  2) UserDetailsService
 *  3) PasswordEncoder
 *  
 */
public class Testclass {

}
