package com.sist.lamda;

public class MainClass {
	public static void main(String[] arg) {
		
		// 쓰레드 작업
	/*	Runnable r = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("쓰레드 실행");
			}
		}; */
		
		// 요즘 스타일 => 메소드 1개이다 보니 가능함 => 저 화살표가 -> 의미하는 게 'run ( )'  /  화살표 다음이 구현부, 여러줄이면 구현부에 { } 이거 추가
		Runnable r=()-> System.out.println("쓰레드 실행");
		new Thread(r).start();
	}
}
