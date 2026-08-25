package com.sist.web.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KafkaController {

	private final KafkaTemplate<String, String> kafkaTemplate;
	
	@GetMapping("/send")
	public String send()
	{
		KafkaTemplate.send("test-topic","Hello Kafka");
		return "Kafka에서 메시지 전송 완료";
	}
}
