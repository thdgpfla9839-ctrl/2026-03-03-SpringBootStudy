package com.sist.web.repository;
// 이게 JPQL 오라클에서 쓰는건 네이티브쿼리? 마이바티스에서 쓰는것도 어쩌구 있음
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.Emp;
@Repository
public interface EmpJpqlRepository extends JpaRepository<Emp, Integer>
{
   @Query("SELECT e FROM Emp e") // Emp는 테이블명이 아니라 엔티티 이름, 클래스명(객체명)이고 그 뒤에 e는 별칭 / 앞에 e는 *을 의미해서 모든 컬럼을 가져옴
                                 // 반드시 뒤에는 별칭을 사용해야 한다 => Emp e
   public List<Emp> empListData();
   
   
  
	
	    // Emp findByEmpno(int empno);
         @Query("SELECT e FROM Emp e WHERE e.empno=:empno")
         public Emp empDetailData(@Param("empno") int empno);

        // List<Emp> findByEname(String ename);
         @Query("SELECT e FROM Emp e WHERE e.ename=:ename")
         public List<Emp> empEnameFind(@Param("ename") String ename);
         
         // List<Emp> findByEnameStartsWith(String ename);
         // CONCAT => 문자열 결합
         @Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT(:ename,'%')")
         public List<Emp> empEnameStartsLike(@Param("ename") String ename);
         
         // List<Emp> findByEnameEndsWith(String ename);
         @Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT('%',:ename)")
         public List<Emp> empEnameEndsLike(@Param("ename") String ename);
         
         // List<Emp> findByEnameContains(String ename);
         @Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT('%',:ename,'%')")
         public List<Emp> empEnameContains(@Param("ename") String ename);
         
         
         // List<Emp> findBySalGreaterThanEqual(int sal);
         @Query("SELECT e FROM Emp e WHERE e.sal>=:sal")
         public List<Emp> findBySalGreaterThanEqual(@Param("sal") int sal);

         
         // List<Emp> findBySalLessThanEqual(int sal);
         @Query("SELECT e FROM Emp e WHERE e.sal<=:sal")
         public List<Emp> findBySalLessThanEqual(@Param("sal") int sal);
        
         
         // findBySalBetween(int min,int max);
         @Query("SELECT e FROM Emp e WHERE e.sal BETWEEN :min AND :max")
         public List<Emp> findBySalBetween(@Param("min") int min, @Param("max") int max);
 
         // findByJobAndSalGreaterThan(String job, int sal);
         @Query("SELECT e FROM Emp e WHERE e.job=:job AND e.sal>:sal ")
         public List<Emp> findByJobAndSalGreaterThan(@Param("job") String job, @Param("sal") int sal);

         // 조인이 걸리면 쿼리클래스를 따로 생성해 줘야한다
         // List<Emp> findByDeptDname(String Dname);
         @Query("SELECT e FROM Emp e JOIN e.dept d WHERE d.dname=:dname") // 조인 뒤 dept는 EMP 안에 있는 객체이다
         public List<Emp> findByDeptDname(@Param("dname") String dname);

         // List<Emp> findByDeptDnameContains(String dname);
         @Query("SELECT e FROM Emp e JOIN e.dept d WHERE d.dname LIKE CONCAT('%',:dname,'%')") 
         public List<Emp> findByDeptDnameContains(@Param("dname") String dname);
         
        // List<Emp> findByCommIsNull();
         @Query("SELECT e FROM Emp e WHERE e.comm IS NULL")
         List<Emp> findByCommIsNull();

         
         @Query("SELECT e FROM Emp e WHERE e.job!=:job") // != 대신에 <>를 사용해도 됨
         List<Emp> findByJobNot(@Param("job") String job);
         
         // findByDeptDeptnoIn(List<Integer> deptnos);
         @Query("SELECT e FROM Emp e WHERE e.dept.deptno IN :deptnos")
         List<Emp> findByDeptDeptnoIn(@Param("deptnos") List<Integer> deptnos);
         
         
         /*
	 * List<Emp> findByEnameEndsWith(String ename); // %A // WHERE ename LIKE '%?%'
	 * List<Emp> findByEnameContains(String ename); // %A%
	 * 
	 * // 4. 비교연산자 // ~ 이상인지 / ~ 이하인지 // 이상일 때 (ThanEqual이게 포함의 의미) // WHERE sal>=?
	 * List<Emp> findBySalGreaterThanEqual(int sal);
	 * 
	 * // 이하일 때 // WHERE sal<=? List<Emp> findBySalLessThanEqual(int sal);
	 * 
	 * // Between => ? ? 매개변수가 2개 // WHERE sal BETWEEN ?(min) AND ?(max) List<Emp>
	 * findBySalBetween(int min,int max);
	 * 
	 * // AND => job과 sal을 이용해서 // WHERE job=? AND Sal>? List<Emp>
	 * findByJobAndSalGreaterThan(String job, int sal);
	 * 
	 * // OR // WHERE job =? OR ename=? List<Emp> findByJobOrEname(String job,
	 * String ename);
	 * 
	 * // 부서명으로 emp 검색 List<Emp> findByDeptDname(String dname); // 어디서 먼저 가져올지
	 * List<Emp> findByDeptLoc(String loc);
	 * 
	 * // 부서명 LIKE List<Emp> findByDeptDnameContains(String dname);
	 * 
	 * // 정렬 // ORDER BY sal DESC List<Emp> findByOrderBySalDesc();
	 * 
	 * // Top-N(가져오는 개수) // WHERE rownum<=3 ORDER BY sal DESC List<Emp>
	 * findTop3ByOrderBySalDesc();
	 * 
	 * // 중복 없이 처리 => 중복 제거 List<Emp> findDistinctByJob(String job);
	 * 
	 * // Not NULL / NULL // WHERE comm ISNULL List<Emp> findByCommIsNull(); //
	 * WHERE comm ISNOTNULL List<Emp> findByCommIsNotNull();
	 * 
	 * // in // List<Integer> list = List.of(10,20,30) List<Emp>
	 * findByDeptDeptnoIn(List<Integer> deptnos);
	 * 
	 * // not // WHERE NOT job=? List<Emp> findByJobNot(String job);
	 */
}
