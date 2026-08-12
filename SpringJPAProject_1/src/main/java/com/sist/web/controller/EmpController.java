package com.sist.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.repository.*;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class EmpController {

	private final EmpMethodRepository eDao;
	private final EmpJpqlRepository eDao2;
	private final EntityManager em;
	private final EmpQueryRepository eDao3;
	
	@GetMapping("/emp")
	public void emp_method()
	{
		// List<Emp> list = eDao.findByJobAndSalGreaterThan("SALESMAN", 1000);
		// List<Emp> list = eDao.findByDeptDnameContains("인");
		// List<Emp> list = eDao2.empListData();
		// List<Emp> list = eDao2.empEnameFind("SCOTT");
		// List<Emp> list = eDao2.empEnameStartsLike("A");
		// List<Emp> list = eDao2.empEnameEndsLike("E");
		// List<Emp> list = eDao2.empEnameEndsLike("A");
		
		// List<Emp> list = eDao2.findBySalLessThanEqual(1000);
		// List<Emp> list = eDao2.findBySalBetween(500, 840); 
		
		// List<Emp> list = eDao2.findByJobAndSalGreaterThan("M", 3500);
		// List<Emp> list = eDao2.findByDeptDname("개발팀");
		// List<Emp> list = eDao2.findByDeptDnameContains("개");
		
		// 그냥 service에서 코딩해도 됨
		// findTop3ByOrderBySalDesc();
		// String jpql = "SELECT e FROM Emp e ORDER BY e.sal DESC";
		 // List<Emp> list = em.createQuery(jpql,Emp.class).setMaxResults(3).getResultList();
		 
		// 중복없이 가져올 때
		// String jpql = "SELECT DISTINCT e.job FROM Emp e";
		// List<String> list = em.createQuery(jpql,String.class).getResultList();
		
		//List<Emp> list = eDao2.findByJobNot("개발");
		/*
		 * List<Emp> list = eDao2.findByDeptDeptnoIn(List.of(10,20,30)); for(Emp
		 * emp:list) //for(String job:list) {
		 * 
		 * System.out.println(emp.getEmpno()+" " +emp.getEname()+" "+emp.getJob()+" "
		 * +emp.getHiredate()+" "+emp.getSal());
		 * 
		 * //System.out.println(job); }
		 */
		
		// 쿼리dsl 출력
		// Emp e = eDao3.findByEmpno(7788);
		// List<Emp> e = eDao3.findByEnameContains("S");
		// List<Emp> e = eDao3.findBySalBetween(3000, 5000);
		List<Emp> e = eDao3.findTop3ByOrderBySalDesc(6000);
		 for(Emp emp : e)
		 {
		    System.out.println(emp.getEmpno()+" "
		                    +emp.getEname()+" "
		                      +emp.getJob()+" "
		                     +emp.getHiredate()+" "
		                    +emp.getSal());
		}
		
		//List<Integer> list = eDao3.findDistinctSal();
		// System.out.println(list);
	}
}
