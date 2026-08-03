package com.sist.web.service;

import org.springframework.stereotype.Service;

import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.repository.*;
import com.sist.web.vo.BoardDTD;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardRepository dao;

	@Override
	public BoardEntity findByNo(int no) {
		// TODO Auto-generated method stub
		return dao.findByNo(no);
	}

	@Override
	public List<BoardDTD> boardListData(int start) {
		// TODO Auto-generated method stub
		return dao.boardListData(start);
	}

	@Override
	public void boardUpdate(BoardEntity vo) {
		// TODO Auto-generated method stub
		dao.save(vo);
		
	}

	@Override
	public void boardInsert(BoardEntity vo) {
		// TODO Auto-generated method stub
		dao.save(vo);
		
	}

	@Override
	public void boardDelete(BoardEntity vo) {
		// TODO Auto-generated method stub
		dao.delete(vo);
		
	}

	@Override
	public int boardCount() {
		// TODO Auto-generated method stub
		return (int)dao.count(); // 이 부분이 기존 SELECT COUNT (*) FROM board 
	}
	
}
