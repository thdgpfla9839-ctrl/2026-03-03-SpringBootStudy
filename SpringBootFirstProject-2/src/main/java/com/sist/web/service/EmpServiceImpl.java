package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.EmpMapper;
import com.sist.web.vo.EmpVO;

@Service
public class EmpServiceImpl implements EmpService{

	private EmpMapper mapper;

	@Override
	public List<EmpVO> empListData() {
		// TODO Auto-generated method stub
		return mapper.empListData();
	}
	
}
