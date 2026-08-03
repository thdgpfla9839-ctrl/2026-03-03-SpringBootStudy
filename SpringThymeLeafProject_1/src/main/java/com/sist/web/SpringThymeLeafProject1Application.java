package com.sist.web;
// 이게 실행파일 - Spring boot app 실행 - 콘솔에 에러가 없으면 브라우저에 들어가서 http://localhost/board/list 검색 후 접속
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringThymeLeafProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringThymeLeafProject1Application.class, args);
	}

}
