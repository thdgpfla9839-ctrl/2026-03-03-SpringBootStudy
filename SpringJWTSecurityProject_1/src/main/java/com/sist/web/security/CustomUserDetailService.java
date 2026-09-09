package com.sist.web.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 사용자 조회가 되는 부분
@Service
public class CustomUserDetailService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// 지금은 임시로 처리했지만 실제 이 부분이 DB처리가 되는 부분 마이바티스 방식에서 JDBC
		if(username.equals("admin"))
		{
			return User.builder()
					.username("admin")
					.password("{noop}1234")
					.roles("ADMIN")
					.build();
		} // {noop} :암호화 하지 않겠다는 의미 => Spring5부터는 반드시 암호화를 해줘야함
		 // 보통 암호화를 시킬 때는 BCryptPasswordEncoder
		 // encode() : 암호화 / math( ) : 복호화
		 // 암호화는 같은 비밀번호가 있는 경우에는 => 패턴 여러개를 갖고 있어서 다르다라는데 정확히 무슨 의미인지 다시 정리
		  return User.builder()
				.username("user")
				.password("{noop}1234")
				.roles("USER")
				.build();
	}

}
