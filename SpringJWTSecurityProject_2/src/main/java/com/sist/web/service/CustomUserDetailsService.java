package com.sist.web.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;

// 사용자 정보가 저장되는 위치
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final MemberService mService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// 멤버테이블 조회
		MemberVO member = mService.findByUserId(username);
		
		// 사용자가 없는 경우
		if(member==null)
		{
			// 예외처리 발생
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다:"+username);
		}
		// 휴먼 계정인 경우
		if(member.getEnable()!=1)
		{
			throw new UsernameNotFoundException("비활성화 계정입니다");
		}
		// Authority 권한 읽기
		List<AuthorityVO> authorityList = mService.getAuthorityData(username);
		
		// 디비에 있는 권한을  => Springsecurity로 변환
		List<SimpleGrantedAuthority> authorities = authorityList.stream()
				.map(a-> new SimpleGrantedAuthority(a.getAuthority()))
				.toList();
		
		// 생성된 데이터를 UserDetails에 저장
		return User.builder().username(member.getUserid())
				.password(member.getUserpwd())
				.authorities(authorities)
				.build();
	}

}
