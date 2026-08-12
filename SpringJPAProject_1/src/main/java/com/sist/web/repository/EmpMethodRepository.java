package com.sist.web.repository;
// 쿼리 메소드 규칙
// 이 파일에서 메서드 이름만 잘 지으면 JPA가 알아서 SQL을 만들어준다는 것을 EmpController에서 실행하면 콘솔에서 직접 만들어준 sql 문장을 볼 수 있다
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.Emp;
import java.util.*;
public interface EmpMethodRepository extends JpaRepository<Emp, Integer>
{
    // findBy => WHERE 문장 작성할 때 사용
	// 1. 상세보기 => empno 검색
	// WHERE empno=?
	Emp findByEmpno(int empno);// 맨 앞에 pubilc 안 써도 자동으로 붙어있다고 인식함
	                           // 그리고 인터페이스는 모든 변수가 public만 사용한다
	// 2. 이름(ename) 검색
	// WHERE ename =? ==> equals  한글자만 쓰면 안 되고 실제이름 전체를 넣어야한다
	List<Emp> findByEname(String ename);
	
	// 3. LIKE 문장 =>A%  %A%  A%
	// WHERE ename LIKE '?%' => 여기는 인덱스가 적용됨 => 빠른 속도
	List<Emp> findByEnameStartsWith(String ename); // A%
	// WHERE ename LIKE '%?'
	List<Emp> findByEnameEndsWith(String ename); // %A
	// WHERE ename LIKE '%?%'
	List<Emp> findByEnameContains(String ename); // %A%
	
	// 4. 비교연산자
	// ~ 이상인지 / ~ 이하인지
	// 이상일 때 (ThanEqual이게 포함의 의미)
	// WHERE sal>=?      
	List<Emp> findBySalGreaterThanEqual(int sal);
	
	// 이하일 때
	// WHERE sal<=?      
	List<Emp> findBySalLessThanEqual(int sal);
	
	// Between => ? ? 매개변수가 2개
	// WHERE sal BETWEEN ?(min) AND ?(max)
	List<Emp> findBySalBetween(int min,int max);
	
	// AND => job과 sal을 이용해서 
	// WHERE job=? AND Sal>?
	List<Emp> findByJobAndSalGreaterThan(String job, int sal);
	
	// OR
	// WHERE job =? OR ename=?
	List<Emp> findByJobOrEname(String job, String ename);
	
	// 부서명으로 emp 검색
	List<Emp> findByDeptDname(String dname); // 어디서 먼저 가져올지
	List<Emp> findByDeptLoc(String loc); 
	
	// 부서명 LIKE
	List<Emp> findByDeptDnameContains(String dname);
	
	// 정렬
	// ORDER BY sal DESC
	List<Emp> findByOrderBySalDesc();
        
	// Top-N(가져오는 개수)
	// WHERE rownum<=3 ORDER BY sal DESC
	List<Emp> findTop3ByOrderBySalDesc();
	
	// 중복 없이 처리 => 중복 제거
	List<Emp> findDistinctByJob(String job);
	
	// Not NULL / NULL
	// WHERE comm ISNULL
	List<Emp> findByCommIsNull();
	// WHERE comm ISNOTNULL
	List<Emp> findByCommIsNotNull();

    // in
	// List<Integer> list = List.of(10,20,30)
	List<Emp> findByDeptDeptnoIn(List<Integer> deptnos);
	
	// not
	// WHERE NOT job=? 
	List<Emp> findByJobNot(String job);
	
}
