package com.sist.web.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
// JPA를 사용하여 프로젝트를 만들 예정
// 기존 마이바티스와 달리 이 파일은 단순히 객체가 아니라 실제 데이터베이스의 테이블과 연결되는 객체
// 기존 VO와 비슷하지만 몇가지가 더 추가된 상태
// JPA한테 해당 '테이븧의 구조'를 알려주는 파일
// Entity => 혼자서 DB 데이터를 가져오지 못함 => DB 데이터를 가져올 별도의 파일을 만들어준다
import jakarta.persistence.Table;
import lombok.Data;

@Entity // 데이터베이스 오라클 컬럼과 매칭을 한다
@Table(name="japboard") // 테이블명을 적어준다 => 어떤 테이블과 매칭을 할지 적어준다
@DynamicUpdate // 필요시에 업데이트 설정
@Data
@DynamicInsert
@SequenceGenerator(name="jpb_no_seq",sequenceName = "jpb_no_seq", allocationSize = 1)
// save(vo) 객체(Entity) => 자동저장 => 데이터베이스의 컬럼과 연결해서 들어간다
public class BoardEntity {

	@Id // 자동증가 컬럼 => 자동으로 sql문장을 제작해준다
	// 테이블에 시퀀스를 줬으면 시퀀스명을 등록해줘야해 안 해주면 어디가 증가되는지 몰라
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "jpb_no_seq")
	private int no;
    private String name, subject,content; // 여기는 인서트랑 없데이트 둘다
    
    @Column(insertable = true, updatable = false) // 데이터가 안 바뀐다 왜냐 업데이트 펄스니까
    private String pwd;
    
    @ColumnDefault("0")
    private int hit;
    
    @Column(insertable = true, updatable = false)
    @ColumnDefault("SYSDATE")
    private String regdate;
    
    @PrePersist // 날짜변환을 할 때 사용한다
    public void regdate()
    {
    	this.regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
