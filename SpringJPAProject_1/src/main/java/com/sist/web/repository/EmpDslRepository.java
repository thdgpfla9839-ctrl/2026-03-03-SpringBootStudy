package com.sist.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.Emp;

public interface EmpDslRepository extends JpaRepository<Emp, Integer>{

}
