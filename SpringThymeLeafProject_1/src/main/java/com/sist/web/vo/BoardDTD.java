package com.sist.web.vo;

// 최근에는 public record BoardDTD  이런 읽기 전용 방식으로 만든대
public interface BoardDTD {

	public int getNo();
	public String getName();
	public String getSubject();
	public String getDbday();
	public int getHit();
}
