package com.sist.web.security;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 로그인 시 에러처리
public class LoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String errMsg="아이디나 비밀번호가 틀립니다";
		
		// instanceof 객체(클래스) 비교할 때 사용 => 반드시 상속과정이 있고 난 후 사용이 가능함
		if(exception instanceof DisabledException)
		{
			// DisabledException => 비활성화
			// 즉, 휴먼계정이면
			errMsg = "휴먼 계정임";
		}
		else if(exception instanceof LockedException)
		{
			errMsg = "잠긴 계정임";
		}
		request.getSession().setAttribute("loginError", errMsg);
		response.sendRedirect("/login?error");
	}

}
