package com.sist.web.manager;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
// 카프카를 지정하는 역할
// 서버와 서버 사이에서 대량의 이벤트나 메시지를 안정적으로 전달하는 분산 스트리밍 => 카프카
// 예) 사용자1 주문 - 주문 서버 - 주문 완료 이벤트 발생 - 카프카에 의해 작업 수행 - 1. 재고 서비스 2. 결재 서비스  3. 알림 서비스 
/*
 *  producer - 메시지 전송 - Topic - Partition - Consumer
 *  
 *  Producer => 카프카에 메시지를 넣어주는 애플리케이션
 *  Consumer => 카프카로부터 메시지를 읽어서 처리
 */
@Component
public class KafkaManager {

	@KafkaListener(topics = "test-topic",
			       groupId = "test-group")
	public void receive(String message)
	{
		System.out.println("=============================");
		System.out.println("카프카 메시지 수신:"+message);
		System.out.println("=============================");
	}
}
