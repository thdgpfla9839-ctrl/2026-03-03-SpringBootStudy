package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.service.*;
import com.sist.web.vo.BoardDTD;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("board/") // 중복되는 경로명을 설정한다 => 그럼 이후에 board/를 안 쓴다는 소리
public class BoardController {

	private final BoardService bService;
	
	@GetMapping("list") // => board/list로 경로가 잡힌다 => 디폴드 확장자가 *.do, /* 어쩌구라는데 이 부분 다시 정리하기 
	public String board_list(@RequestParam(value = "page",required = false) String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		int start=(curpage*10)-10;
		List<BoardDTD> list = bService.boardListData(start);
		int count = bService.boardCount();
		int totalpage=(int)(Math.ceil(count/10.0));
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		
		return "board/list"; // list.html => 마이바티스의 기존 jsp에서 html로 파일이 바뀜 => JPA를 사용하는 중 => jsp기능을 html에서 실행하게끔 하는 역할 => 속도가 더 빨라짐
	}
	
	@GetMapping("detail") // board/detail
	public String board_detail(@RequestParam("no") int no, Model model)
	{
		BoardEntity vo = bService.findByNo(no); // select * from board where no=? =>?안에는 findByNo(no)안에 no가 듷어간다
		vo.setHit(vo.getHit()+1);
		bService.boardUpdate(vo); // 조회수 증가
		
		
		// 데이터 보내기 => vo
		vo = bService.findByNo(no);
		model.addAttribute("vo",vo);
		return "board/detail"; // 확장자가 기존 jsp에서 html이다
	}
	
	// 화면만 이동하면 됨
	@GetMapping("insert")
	public String board_insert()
	{
		return "board/insert";
	}
	
	@PostMapping("insert_ok")
	public String board_insert_ok(@ModelAttribute("vo") BoardEntity vo) // vo단위로 받을 때는 boardEntity 해줘야 객체단위로 값을 받을 수 있음
	{
		bService.boardInsert(vo);
		return "redirect:/board/list";
	}
	
	@GetMapping("delete")
	public String board_delete(@RequestParam("no") int no, Model model)
	{
		model.addAttribute("no",no); // 이 값이  delete로 넘어간거임
		return "/board/delete";
	}
	
	@PostMapping("delete_ok")
	public String board_delete_ok(@RequestParam("no") int no, @RequestParam("pwd") String pwd, Model model)
	{
		String res = "no";
		BoardEntity vo =bService.findByNo(no);
		if(vo.getPwd().equals(pwd))
		{
			res = "yes";
			bService.boardDelete(vo); // 삭제하는 과정
		}
		model.addAttribute("res",res);
		return "board/delete_ok";
		
	}
	
	@GetMapping("update")
	public String board_update(@RequestParam("no") int no, Model model)
	{
		BoardEntity vo = bService.findByNo(no);
		model.addAttribute("vo", vo);
		
		return "board/update";
	}
	
	@PostMapping("update_ok")
	public String board_update(@ModelAttribute("vo") BoardEntity vo, Model model)
	{
		BoardEntity dbVO = bService.findByNo(vo.getNo());
		String res = "no";
		if(vo.getPwd().equals(dbVO.getPwd()))
		{
			vo.setNo(vo.getNo()); // 이 번호에 대해 수정해라
			vo.setHit(vo.getHit());
			bService.boardUpdate(vo);
			res = "yes";
		}
		model.addAttribute("res",res);
		model.addAttribute("no",vo.getNo());
		return "board/update_ok"; // return 바로 뒤에 /는 주지 말자
		                          // 스프링(Spring)은 화면(HTML) 파일을 찾을 때, 이미 정해진 기본 폴더(templates) 안에서 파일을 찾을 준비를 하고 있습니다.
                                 //그런데 파일 이름 맨 앞에 슬래시(/)를 붙이면, 스프링이 "아, 정해진 폴더 안이 아니라 아예 컴퓨터의 가장 처음(최상위 경로)부터 다시 찾아야 하나?" 하고 헷갈려합니다.

	}
}
