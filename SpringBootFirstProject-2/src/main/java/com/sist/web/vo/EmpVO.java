package com.sist.web.vo;

import java.sql.Date;

import lombok.Data;
@Data
public class EmpVO {

	private int empno,sal;
	private String ename,job,dbday;
	private Date hiredate;
}
