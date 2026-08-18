package com.sist.lamda;
// 함수형 인터페이스
// 람다식은 함수형 인터페이스만 사용이 가능하다
// 반드시 추상메소드 1개만 존재해야한다
// @FuntionalInterface 반드시 설정
// 이 인터페이스는 람다용이라는 표시를 해줘야 한다
@FunctionalInterface
interface Calc{
	int sum(int a, int b);
}
public class MainClass_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // 일반 자바형식
		/*Calc c = new Calc() {
			
			@Override
			public int sum(int a, int b) {
				// TODO Auto-generated method stub
				return a+b;
			}
		};*/
		
		// 람다는 앞에 함수명이 없다
		Calc c=(a,b)->a+b;
		System.out.println(c.sum(10, 20));
	}

}
