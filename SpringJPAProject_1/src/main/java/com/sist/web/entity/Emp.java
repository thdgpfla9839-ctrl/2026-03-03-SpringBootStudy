package com.sist.web.entity;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="EMP")
@Getter
@Setter
// 이런 경우 DATA로 주는 것보다 게터/세터로 각자 주는게 좋음
public class Emp {
 
	@Id // primary key 설정
	private Integer empno;
	private String ename;
	private String job;
	private  Integer mgr; // 컬럼 안에 널값이 있는 경우에는 클래스형으로 만들어 줘야 한다 => int로 변환을 못시킴
	private Date hiredate;
	private int sal; // 이렇게 쓰는 경우에는 컬럼 안에 널값이 없다
	private Integer comm;
	// 여기에 조인 걸리는 컬럼을 사용하면 에러가 난다 그래서 여기서는 deptno를 선언하지 않았다 private int deptno;
	
	//부서 하나당 사원 여러개 조인 거는 방식
	@ManyToOne
	// 어떤 조인 컬럼을 사용할 건지
	@JoinColumn(name="deptno")
	private Dept dept;
}
