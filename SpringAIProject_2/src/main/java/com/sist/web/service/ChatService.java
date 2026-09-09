package com.sist.web.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

// 채팅 ui 만들어서 ai 프롬프트 만듦

@Service

public class ChatService {

	private final ChatClient chatClient;
	public ChatService(ChatClient.Builder chatClientBuilder)
	{
		this.chatClient=chatClientBuilder.build();
	}
	
	public Flux<String> streamChat(String userMsg)
	{
		String systemPromt = "한글로 답변하세요";
		Flux<String> f = chatClient.prompt()
				   .system(systemPromt)
				   .user(userMsg)
				   .stream()
				   .content()
				   .doOnNext(System.out::println);
		System.out.println(f);
		return f;
		
	}
}
