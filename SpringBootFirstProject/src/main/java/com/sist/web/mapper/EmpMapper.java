package com.sist.web.mapper;
/*
 *  1. Mapper => sql 문장
 *  2. DAO => Mapper 연동 : 데이터베이스 연결
 *  3. Service => DAO의 추가 기능 => 로그인이나 주소 자르기 처리가 되는 곳
 *  4. Controller => 브라우저에 출력 데이터 전손
 *  5. JSP / HTML 출력
 */
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;

@Mapper
@Repository
public interface EmpMapper {

	@Select("SELECT empno,ename,job "
			+"TO_CHAR(hiredate,'yyyy-mm-dd')as dbday,sal "
			+"FROM emp "
			+"ORDER BY empno ASC")
	public List<EmpVO> empListData();
}
