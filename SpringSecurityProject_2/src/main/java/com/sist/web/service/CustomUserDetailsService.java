package com.sist.web.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sist.web.mapper.UserMapper;
import com.sist.web.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final UserMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	    // 사용자 정보
		MemberVO user = mapper.findByUserid(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("UserName을 찾을 수 없다");
		}
		List<String> roles=mapper.findRolesByUserid(username);
		// 권한 관련된 데이터를 저장
		Set<GrantedAuthority> authorities = new HashSet<>(); // 중복없이
		for(String role:roles)
		{
			authorities.add(new SimpleGrantedAuthority(role));
		}
		
		return new User(user.getUsername(), user.getUserpwd(), user.getEnable()==0?false:true, true, true, true, authorities);
	}
	
	/*
	 *    1. user.getUsername() : 아이디 저장
	 *    2. user.getUserpwd() : 비밀번호 저장
	 *    3. user.getEnable() : 활성화 여부 저장
	 *    4. true : 계정 만료 확인
	 *    5. true : 계정 잠금 상태 확인
	 *    6. true : 비밀번호 만료 확인
	 *    7. authorities : 권한 정보 
	 */

}
