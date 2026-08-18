package com.sist.stream;
import java.util.*;
import java.util.stream.Collectors;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        EmpDAO dao = new EmpDAO();
        List<EmpVO> list = dao.empAllData();
        
        // 전체목록 출력 => forEach 이용
        list.stream().forEach(vo->System.out.println(vo.getEmpno()+" "
                                                     +vo.getEname()+" "
        		                                     +vo.getSal()+" "+vo.getJob()+" "
                                                     +vo.getHiredate().toString()));
       
        System.out.println();
        System.out.println("============================= filter 이용하는 방법 =======================================");
        list.stream().filter(vo-> vo.getSal()>=3000).forEach(vo->System.out.println(vo.getEmpno()+" "
                                                             +vo.getEname()+" "+vo.getSal()+" "
        		                                             +vo.getJob()+" "+vo.getHiredate().toString()));
        System.out.println();
        System.out.println("============================= 사번이 짝수인 사람 =======================================");
        list.stream().filter(vo->vo.getEmpno()%2==0).forEach(vo->System.out.println(vo.getEmpno()+" "
												                +vo.getEname()+" "+vo.getSal()+" "
												                +vo.getJob()+" "+vo.getHiredate().toString()));
        // if(vo.getEmpno()%2==0) 이거랑 같은 거야
        
        System.out.println();
        System.out.println("=============================== sort와 compare => ASC 정렬 ==========================");
        list.stream().sorted(Comparator.comparing(EmpVO::getSal)).forEach(vo->System.out.println(vo.getEmpno()+" "
																		                +vo.getEname()+" "+vo.getSal()+" "
					
																		                +vo.getJob()+" "+vo.getHiredate().toString()));
        System.out.println();
        System.out.println("=============================== sort와 compare => DESC 정렬 ==========================");
        list.stream().sorted(Comparator.comparing(EmpVO::getSal).reversed()).forEach(vo->System.out.println(vo.getEmpno()+" "
													        		+vo.getEname()+" "+vo.getSal()+" "
													        		+vo.getJob()+" "+vo.getHiredate().toString()));

        
        System.out.println();
        System.out.println("================================================= 중복제거 => distinct ================================");
        List<String> kList = List.of("java","spring","html","css","javascript","vuejs");
        kList.stream().distinct().forEach(System.out::println);
        
        System.out.println();
        System.out.println("================================================= 통계 => reduce ================================");
        int total = list.stream().map(EmpVO::getSal).reduce(0, Integer::sum);
        System.out.println(total);
        
        double avg =  list.stream().mapToInt(EmpVO::getSal).average().orElse(0);
        System.out.println(avg);
        
	}

}
