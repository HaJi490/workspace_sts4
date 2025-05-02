package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@GetMapping("/getBoardList")
	//@RequestMapping(value="getBoardList", met)
	public String getBoardList(@ModelAttribute("member") Member member, Model model) {
		if(member.getId() == null) return "redirect:login";
		
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoard(Board board) {
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoardPost(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@PostMapping("/updateBoard") //jsp의 form태그에서 post랑 get만 지원해서
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "redirect:getBoardList"; //포워딩을 하면 getBoardList를 post매핑으로 찾음 -> 1.리다이렉트하면 get으로 찾음 2.RequestMapping으로 방식 설정---!
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
	
	@GetMapping("/hello")
	public ModelAndView hello(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("greeting", "Hello 타임리프^^");
		mv.setViewName("hello");
		return mv;
	}
	
}
