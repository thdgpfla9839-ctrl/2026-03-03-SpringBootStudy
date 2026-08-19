package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 화면이동만
public class MemberController {

	@GetMapping("/")
	public String home()
	{
		return "home"; // 만들어 놓은 .html 파일 호출하는 중
	}
	
	@GetMapping("/user")
	public String user()
	{
		return "user"; 
	}
	
	@GetMapping("/admin")
	public String admin()
	{
		return "admin";
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
}
