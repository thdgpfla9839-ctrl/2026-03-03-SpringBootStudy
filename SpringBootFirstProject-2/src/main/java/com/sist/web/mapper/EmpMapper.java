package com.sist.web.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;
@Mapper
@Repository // 리포짓토리 해놔서 dao 없이 가도 됨
public interface EmpMapper {

	@Select("SELECT empno,ename,job "
			+"FROM emp")
	public List<EmpVO> empListData();
}
