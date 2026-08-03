package com.sist.web.repository;

// DB에서 데이터를 가져오고, 저장하고, 수정하고, 삭제하는 파일
// 기존 마이바티스에서 사용했던 mapper.xml 파일이라고 생각하자
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sist.web.vo.*;
import java.util.*;
import com.sist.web.entity.*;
@Repository // 이거 해주면 서비스임플에서 오토와이드? 안 해줘도 됨
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

	public BoardEntity findByNo(int no); // 상세보기에 해당됨 => 무조건 만드는 건 아님 => 간단한 건 메소드 선언하는 방식으로 작성
	// find => SELECT *
	// By => WHERE no =1
	// FindBtNameLike => WHERE 문장이 생김 => WHERE name Like
	// FindByNoBetweenAnd(int a, int b)
	
	// 직접 sQL 문장을 만들 수 있다 => 복잡한 건 sql문장을 만들어주기
	@Query(value="SELECT no,subject,name,hit,TO_CHAR(regdate,'yyyy-MM-dd') as dbday FROM japboard ORDER BY no DESC "
			+"OFFSET :start ROWS FETCH NEXT 10 ROWS ONLY",
			nativeQuery = true) // sql을 jpql로 변경없이 문장 그대로
	public List<BoardDTD> boardListData(@Param("start") int start); // 값을 줄 떄는 Param에서 가져오는데 마이바티스거 가져오면 X 
	                                                                   // @Param("매개변수") 매개변수의 자료형 
	                                                                   // " " 따옴표 안에는 sql에서 사용할 이름
}
