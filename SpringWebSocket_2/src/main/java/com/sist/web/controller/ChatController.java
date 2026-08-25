package com.sist.web.controller;

import com.sist.web.SpringWebSocket2Application;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.vo.ChatMessage;

import lombok.RequiredArgsConstructor;
/*
 *   STOMP (Simple Text Oriented Messaging Protocol)
 *     // 실시간 알림 : 회원 / 예약 / 댓글 
 *     서버 <=> 클라이언트 : SockJS
 *     메세지브로커 <=> 클라이언트 간의 통신을 도와준 역할 
 *     ----------------------------------------
 *   | 규칙을 정해서 사용 
 *     => 내부 프로토콜(약속) 
 *        => 서버 == 클라이언트만 알수 있게 만든다 
 *           --------------
 *           LOGIN => 100 , LOGOUT => 200
 *   | 동작 흐름 
 *     connect : websocket 연결 => stomp으로 요청 
 *               let socket=new SockJS('/chat-ws);
 *                                    ------------ EndPoint
 *               stompClient=Stomp.over(socket)
 *     send : 클라이언트가 특정 목적지 (topic) => 메세지 발행 (public)
 *     subscribe :  클라이언트에서 발행된 데이터를 읽기 
 *     disconnect : 연결 종료
 *     
 *     목적지 : topic , queue
 *     ---------------------
 *     
 *   | pinia에서 이용 
 *     => 처리 하는 기능 => store안에 존재 
 *     
 *   SockJS : 전화선  
 *   Stomp : 통신 담당 => 송수신 => URI 
 *           /room/1 , /room/2 ...
 *           -------   -------
 */
@Controller
@RequiredArgsConstructor
public class ChatController {
   private final SpringWebSocket2Application springWebSocket2Application;
   // 1:1 채팅 
   private final SimpMessagingTemplate messageingTemplate;
   
   @MessageMapping("/chat.send")
   @SendTo("/topic/public") // 전체 채팅 
   public ChatMessage sendMessage(ChatMessage message)
   {
	   return message;
   }
   @MessageMapping("/chat.private")
   // 알림 / 실시간 상담 
   public void privateMessage(ChatMessage message)
   {
	   System.out.println("My:"+message.getSender());
	   System.out.println("You:"+message.getReceiver());
	   System.out.println("Message:"+message.getMessage());
	   
	   messageingTemplate.convertAndSend(
	     "/queue/private/"+message.getReceiver(),
	     message
	   );
	   
   }
   @GetMapping("/chat")
   public String chat_page() {
	   return "chat";
   }
}
