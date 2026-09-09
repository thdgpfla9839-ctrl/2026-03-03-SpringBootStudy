package com.sist.web;

import java.util.Arrays;
/*
 *  클래스별 역할 / 동작순서 / 소스 분석
 *  전체 동작 순서 : 로그인 시 -> 인증
 *  
 *  1) 로그인 요청 : POST 방식으로 전송 => /member/login으로 이동 => id / pwd를 넘겨줌
 *  2) AuthController
 *     => AuthenticationManager : 인증의 위임
 *     => AuthenticationManager는 UserDetailsService를 통해
 *        데이터베이스에서 사용자 검색 => 비번과 아이디 일치 확인
 *  3) JWT 발급 / 쿠키에 저장
 *     => 인증 성공 여부에 따라 => 아이디와 권한을 포함한 데이터를 JWT 토큰에 추가
 *     => JWT의 토큰을 Cookie에 저장한다
 *     => 그 다음 /home으로 이동시켜주는 역할
 *  4) JwtAuthenticationFilter가 작동
 *     => 다른 페이지를 요청할 때 브라우저는 쿠키 /JWT header를 이용해야한다
 *     => JWT header는 자바스크립트 처리를 해줘야 함
 *     => 쿠키를 읽어서 accssToken 추출
 *     => UserDetailsService의 정보를 읽어서 
 *     => SecurityContext에 해당 정보를 저장한다
 *  5) Controller 접근 완료      
 */
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}

}
