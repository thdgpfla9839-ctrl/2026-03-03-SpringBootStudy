package com.sist.web.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// 이게 쿼리DSL
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sist.web.entity.Emp;
import com.sist.web.entity.QDept;
import com.sist.web.entity.QEmp;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpQueryRepository {

	private final JPAQueryFactory queryFactory;
	
	// @Query("SELECT e FROM Emp e") : JPQL
	// Emp findByEmpno(int empno) : 메소드규칙으로 작성 
	// QueryDSL
	public Emp findByEmpno(int empno)
	{
		QEmp emp = QEmp.emp; // Q-class
		return (Emp) queryFactory.from(emp).where(emp.empno.eq(empno)).fetchOne();
	}
	
	  // List<Emp> findByEname(String ename);
      // @Query("SELECT e FROM Emp e WHERE e.ename=:ename")
      public List<Emp> findByEname(String ename)
      {
    	  QEmp emp = QEmp.emp;
    	  // 여러개 들어갈 떈(List로 보내면) fetch로 받는다
    	  return (List<Emp>) queryFactory.from(emp).where(emp.ename.eq(ename)).fetch();
      }
      
      // List<Emp> findByEnameStartsWith(String ename);
      //@Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT(:ename,'%')")
      public List<Emp> findByEnameStartsWith(String ename)
      {
    	  QEmp emp = QEmp.emp;
    	  return (List<Emp>) queryFactory.from(emp).where(emp.ename.startsWith(ename)).fetch();
      }
      
      // List<Emp> findByEnameEndsWith(String ename);
      // @Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT('%',:ename)")
      public List<Emp> findByEnameEndsWith(String ename)
      {
    	  QEmp emp = QEmp.emp;
    	  return (List<Emp>) queryFactory.from(emp).where(emp.ename.endsWith(ename)).fetch();
      }
      
       // List<Emp> findByEnameContains(String ename);
      // @Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT('%',:ename,'%')")
      public List<Emp> findByEnameContains(String ename)
      {
    	  QEmp emp = QEmp.emp;
    	  return (List<Emp>) queryFactory.from(emp).where(emp.ename.contains(ename)).fetch();
      }
     
      // List<Emp> findBySalGreaterThanEqual(int sal);
      // @Query("SELECT e FROM Emp e WHERE e.sal>=:sal")
      // sal>=?
     public List<Emp> findBySalGreaterThanEqual(int sal)
     {
    	 QEmp emp = QEmp.emp;
    	 return (List<Emp>) queryFactory.from(emp).where(emp.sal.goe(sal)).fetch();
     }
     
     // List<Emp> findBySalLessThanEqual(int sal);
     // @Query("SELECT e FROM Emp e WHERE e.sal<=:sal")
     // sal<=?
     public List<Emp> findBySalLessThanEqual(int sal)
     {
    	 QEmp emp = QEmp.emp;
    	 return (List<Emp>) queryFactory.from(emp).where(emp.sal.loe(sal)).fetch();
     }
     // 추가로 
     // sal <> sal => emp.sal.ne(sal)
     // sal = ?  => emp.sal.eq(sal)
     
     // List<Emp> findBySalBetween(int min,int max);
     // @Query("SELECT e FROM Emp e WHERE e.sal BETWEEN :min AND :max")
     public List<Emp> findBySalBetween(@Param("min") int min, @Param("max") int max)
     {
    	 QEmp emp = QEmp.emp;
    	 return (List<Emp>) queryFactory.from(emp).where(emp.sal.between(min,max)).fetch();
     }
     
     // AND
     // List<Emp> findByJobAndSalGreaterThan(String job, int sal);
     // @Query("SELECT e FROM Emp e WHERE e.job=:job AND e.sal>:sal ")
     public List<Emp> findByJobAndSalGreaterThan(@Param("job") String job, @Param("sal") int sal)
     {
    	 QEmp emp = QEmp.emp;
    	 return (List<Emp>) queryFactory.from(emp).where(emp.job.eq(job),emp.sal.gt(sal)).fetch(); 
    	 // 이렇게 작성할 수도.where(emp.job.eq(job).and(emp.sal.gt(sal)).fetch();
     }
     
     // IsNull
     // List<Emp> findByCommIsNull();
     // @Query("SELECT e FROM Emp e WHERE e.comm IS NULL")
      public List<Emp> findByCommIsNull()
      {
    	 QEmp emp = QEmp.emp;
     	 return (List<Emp>) queryFactory.from(emp).where(emp.comm.isNull()).fetch();
      }

      // NOT
     // @Query("SELECT e FROM Emp e WHERE e.job!=:job") 
     //List<Emp> findByJobNot(@Param("job") String job);
      public List<Emp> findByJobNot(@Param("job") String job)
      {
    	 QEmp emp = QEmp.emp;
      	 return (List<Emp>) queryFactory.from(emp).where(emp.job.ne(job)).fetch();
      }
     
      // IN
     // findByDeptDeptnoIn(List<Integer> deptnos);
     // @Query("SELECT e FROM Emp e WHERE e.dept.deptno IN :deptnos")
     public List<Emp> findByDeptDeptnoIn(@Param("deptnos") List<Integer> deptnos)
     {
    	 QEmp emp = QEmp.emp;
      	 return (List<Emp>) queryFactory.from(emp).where(emp.dept.deptno.in(deptnos)).fetch();
     }
     
     // 부서명
     // List<Emp> findByDeptDname(String Dname);
     // @Query("SELECT e FROM Emp e JOIN e.dept d WHERE d.dname=:dname") // 조인 뒤 dept는 EMP 안에 있는 객체이다
     public List<Emp> findByDeptDname(String dname)
     {
    	 QEmp emp = QEmp.emp;
    	 QDept dept = QDept.dept;
      	 return (List<Emp>) queryFactory.from(emp).join(emp.dept,dept).where(dept.dname.eq(dname)).fetch();
     }
     
     public List<Emp> findByDeptDnameLike(String dname)
     {
    	 QEmp emp = QEmp.emp;
    	 QDept dept = QDept.dept;
    	 return (List<Emp>) queryFactory.from(emp).join(emp.dept,dept).where(dept.dname.contains(dname)).fetch();
     }
     
     
   // 정렬
 	// ORDER BY sal DESC
 	public List<Emp> findByOrderBySalDesc()
 	{
 		QEmp emp = QEmp.emp;
     	return (List<Emp>) queryFactory.from(emp).orderBy(emp.sal.desc()).fetch();
 	}
         
 	// Top-N(가져오는 개수)
 	// WHERE rownum<=3 ORDER BY sal DESC
 	public List<Emp> findTop3ByOrderBySalDesc(int sal)
 	{
 		QEmp emp = QEmp.emp;
     	return (List<Emp>) queryFactory.from(emp).orderBy(emp.sal.desc()).limit(3).fetch();
 	}
 	
 	// Distinct
 	public List<Integer> findDistinctSal()
 	{
 		QEmp emp = QEmp.emp;
     	return (List<Integer>) queryFactory.select(emp.sal).distinct().from(emp).fetch();
 	}
}
