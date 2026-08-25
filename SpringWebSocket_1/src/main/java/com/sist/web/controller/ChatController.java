package com.sist.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.vo.ChatMessage;

@Controller
public class ChatController {
  @GetMapping("/chat")
  public String chat_page()
  {
	  return "chat";
  }
  @MessageMapping("/chat.send") // 클라이언트 전송 URI 
  @SendTo("/topic/public") // 전체 => public / 귀속말 => private
  public ChatMessage sendMessage(ChatMessage message)
  {
	  message.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
      return message;
  }
}
