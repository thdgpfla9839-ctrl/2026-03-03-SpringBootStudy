package com.sist.lamda;
// filter => 조건검색할 때
// map => 새로운 데이터를 생성할 때 => 예) 소문자를 대문자로 바꿔라
import java.util.*;
public class MainClass_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> list = List.of("java","oracle","html","jsp","spring");
		// 데이터 읽어오기
		list.stream().sorted((a,b)-> a.length()-b.length()).forEach(System.out::println);
		
		// 주로 사용하는 정렬 => compare(String, String)
		// 음수면 앞이 >  ,  양수면 < 뒤에가 크다 ,  =
		
		System.out.println("=================================Filter===================================");
		List<Integer> nList = List.of(1,2,3,4,5,6,7,8);
		/*for(int n:nList)
		{
			if(n%2==0)
			{
				System.out.println(n);
			}
		} */
		
		// 위 for문을 줄여서 이렇게 한줄로 출력할 수 있음
	    nList.stream().filter(n->n%2==0).forEach(System.out::println);
	    
	    System.out.println("====================================================================");
	    // 문자열 길이 반환 => 문자열 개수 구하기
	    // 실제 데이터가 아니라 변경된 데이터 출력할 때 => map
	    list.stream().map(w->w.length()).forEach(System.out::println);
	    
	    System.out.println("=================================Map===================================");
        List<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>();
        Map<String, Object> maps = new HashMap<String,Object>();
        maps.put("홍길동","ADMIN");
        maps.put("심청이","USER");
        maps.put("강감찬","USER");
        maps.put("이순신","MANAGER");
        maps.put("박문수","USER");
	    
        menuList.add(maps);
        menuList.stream().filter(m-> m.get("심청이").toString().contains("USER")).forEach(m-> System.out.println(m.get("심청이")));
        
        // 람다식 사용 시
        /*
         *  처리문장을 한줄로 처리 => 게임이나 보안에서
         *  조건 / 변환
         *  복잡한 소스가 있는 경우
         *  디버깅이 중요한 코드
         *  
         *  참고) 권한과 보안에 따라 메뉴가 다르게 생성된다
         *  List<Member> admins = user.stream().filter(u-> "ADMIN".equals(u.getRole()).toList()
         *  
         *  특정 필드 추출
         *  List<String> names = users.stream().map(Member::getName).toList()
         *  
         * 중복제거
         * List<String> roles = users.stream().map(Member::getRole).distinct().toList()
         * 
         * => 기본적으로 보안을 할 때는 member => userid, userpwd, username , enable이 반드시 포함돼 있어야 한다
         * => 계정을 만들 때는 useris, role을 참조해야 한다 => ROLE_ADMIN, ROLE_MANAGER 이렇게 작성해야한다
         * 
         * 
         * int sum = user.stream().map(Member::getAge).reduce(0,Integer::sum)
         * 
         * double avg = user.stream().mapToInt(Member::getAge).average().orElse(0)
         *  
         *  findFirst()
         *  groupingBy
         */
	}

}
