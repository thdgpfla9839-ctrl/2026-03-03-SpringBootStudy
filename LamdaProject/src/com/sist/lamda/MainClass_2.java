package com.sist.lamda;

import java.util.List;

// 람다식에서 많이 쓰이는 형식
// 람다 + 컬렉션 형식
/*
 *  for(String s:list)
 *  {
 *    system.out.println(s)
 *  }
 *  => list.forEach(s->system.out.println(s))
 */
public class MainClass_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         
		// 객체단위가 아닐 떄는 List.of( )를 쓰는게 좋다는데?
		List<String> colors = List.of("black","green","red","white","pink");
		for(String c:colors)
		{
			System.out.println(c);
		}
		System.out.println("================람다 이용===================");
		colors.forEach(c -> System.out.println(c));
	}

}
