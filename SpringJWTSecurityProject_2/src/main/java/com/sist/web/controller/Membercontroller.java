package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// router 과정 => 화면만 이동함
// 모든 처리는 @RestController에서
public class Membercontroller {

	@GetMapping("/")
	public String home()
	{
		return "home";
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
