package com.sist.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
// STOMP기반의 WebSokcet 기능을 활성화 
/*
 *    new SockJS("/ws-chat") : 서버 연결 
 *         |
 *     SpringBoot WebSocket 
 *         |
 *      WebSocket 연결 
 *         |
 *      setAllowedOriginPatterns("*") 접속허용 
 *         |
 *       withSockJS()
 *        => SockJS 를 이용해서 통신이 가능하게 지원 
 *      
 */
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	// 클라이언트가 WebSocket서벙에 처음 접속할 주소 등록 
	// /ws-chat
	/*
	 *  registry.addEndPoint("/ws-chat")
	 *  => new SockJS("/ws-chat") 
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		//WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
	    // 1. 클라이언트가 서버에 접속할 URI주소 
		// origin => 모든 클라이언트가 접속이 가능하게 
		// => 실제는 지정된 도메임만 허용 
		registry.addEndpoint("/ws-chat")
		        .setAllowedOriginPatterns("*")
		        .withSockJS();
	}
    // URI => 자바 채팅 => 번호 
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// TODO Auto-generated method stub
		//WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
		// 채널 => 클라이언트가 서버에서 보낸 데이터를 읽어서 출력 
	    registry.enableSimpleBroker("/topic");
	    // 메세지를 보내는 경우(보내는 곳)
	    registry.setApplicationDestinationPrefixes("/app");
	}
   
}
