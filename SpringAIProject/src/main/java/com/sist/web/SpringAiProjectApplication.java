package com.sist.web;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiProjectApplication.class, args);
	}

	// ai 채팅 들어가면 보이는 입력창? 
	// 프롬프트 배우는 거래
	@Bean
	public CommandLineRunner runner(ChatModel model)
	{
		System.out.println("초기화가 되는 과정 => ChatModel이 자동생성"+model);
		return args-> {
			// String response=model.call("마포 여행지 알려줘");
			// System.out.println("[결과]"+response);
			System.out.println("-".repeat(100));
			String response=model.call("홍대 점심 메뉴");
			System.out.println("[결과]"+response);
		};
	}
}
