package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.sist.web.vo.*;
import java.util.*;
@Mapper
@Repository
public interface UserMapper {

	@Select("SELECT userid as username,userpwd,enable "
			+"FROM springmember WHERE userid=#{userid}")
	public MemberVO findByUserid(String userid);
	
	@Select("SELECT authority FROM authority WHERE userid=#{userid}")
	public List<String> findRolesByUserid(String userid); // List<String> 권한 여러개일 경우 동시에 가져오기 위해
}
