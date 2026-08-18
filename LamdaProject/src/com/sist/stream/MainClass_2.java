package com.sist.stream;
import java.util.*;
public class MainClass_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> names = new ArrayList<String>();
		names.add("송");
		names.add("김");
		names.add("이");
		names.add("박");
		names.add("유");
		 
		for(String name:names)
		{
			System.out.println(name);
		}
		System.out.println();
		// for문 내용이 이렇게 한줄로 출력이 가능하다
		names.forEach(System.out::println);
		
		System.out.println();
		// for문 내용이 이렇게 한줄로 출력이 가능하다 => 이 경우는 값을 받아오는 경우
		names.forEach(name->System.out.println(name));
		
		System.out.println("=======================================");
		List<String> colors = List.of("bllue","white","pink","red","orange");
		colors.stream().filter(c->c.startsWith("b")).map(String::toUpperCase).forEach(System.out::println);
		
		
		System.out.println();
		System.out.println("=======================================================");
		List<String> cList = colors.stream().filter(c->c.startsWith("b")).map(String::toUpperCase).toList();
		cList.forEach(System.out::println);
		
		
	}

}
