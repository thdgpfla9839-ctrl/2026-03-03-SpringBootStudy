package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
// @repository : DAO1개를 나타냄 => 테이블 1개만 연동
// @service :관련된 DAO 여러개를 묶어서 한번에 처리
// => 그래서 이런걸 합쳐서 BI

import com.sist.web.vo.AuthorityVO;
@Mapper
@Repository
public interface AuthorityMapper {

	@Select("SELECT userid,authority "
			+"FROM authority "
			+"WHERE userid=#{userid}")
	public List<AuthorityVO> getAuthorityData(String userid);
}
