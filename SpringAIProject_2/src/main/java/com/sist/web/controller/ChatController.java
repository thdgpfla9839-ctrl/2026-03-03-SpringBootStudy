package com.sist.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.web.service.ChatService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class ChatController {

	private final ChatService cService;
	
	
	@GetMapping("/chat")
	public String chatform()
	{
		return "/chat";
	}
	
	// MediaType을 안 주면 sync / 사용하면 stream방식
	// sync : 결과값을 한번에 모아서 처리
	// stream : 타자 형식으로 한줄씩 출력해 나가는 방식
	// 1. jsp는 stream을 사용하면 sync가 됨 => stream 사용이 안 됨
	@GetMapping(value = "/chat/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseBody
	public Flux<String> chat_stream(@RequestParam("message") String message)
	{
		return cService.streamChat(message);
	}
}
