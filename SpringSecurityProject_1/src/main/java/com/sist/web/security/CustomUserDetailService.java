package com.sist.web.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service // 이 부분이 나중에 마이바티스와 연결이 됨
public class CustomUserDetailService implements UserDetailsService{

	/*
	 *   ID : <input type = text name ="username">
	 *   PW : <input type = password name ="password">
	 *   
	 *   로그인 화면
	 *   POST 방식 => username=admin / password=1234
	 *               /login =>
	 *    |
	 *    Spring Security
	 *    |
	 *    Authentication :인증
	 *    |-> Success 시 => 성공 => home.html로 이동
	 *    |-> Fail 시 => /login?error
	 *                  LoginFailHandler => 아이디나 비밀번호가 틀립니다 
	 *                  
	 *   => formLogin에서 로그인 처리 후 인증이 되면 해당 접속자의 정보를 읽는다
	 *                   => 그 정보는 아이디와 비밀번호, enable, roles를 읽는다
	 *   => logout 시 => session 해제가 된다     
	 *               => 매개변수 principal(세션형식의 클래스, 세션기반의 클래스)에서 session 정보를 가져올 수 있다    
	 *               
	 *   만약에 Controller에서 세션이 필요하디면
	 *   public String chat(cHttpSesion session, Principal p)
	 *   {
	 *           현재 마이바티스로 작성하고 있으니까 dao.으로 데이터를 가져온다
	 *          UserVO vo = dao.infoData(p.usrname)
	 *          session.setAttribute("vo",vo)
	 *   }                                             
	 * 
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(username.equals("admin"))
		{
			return User.builder().username("admin")
					.password("{noop}1234") // {noop} => 암호화 없이 처리됨
					.roles("ADMIN")
					.build();
		}
		return User.builder().username("user")
				.password("{noop}1234")
				.roles("USER")
				.build();
	}

}
