package com.sist.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
  private String sender; // 보낸 사람
  private String message; // 메세지 내용
  private String time; // 시간 
}
