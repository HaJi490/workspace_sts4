package com.rubypaper.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/board")
	public List<Board> getBoards(){
			
		return boardRepo.findAll();
	}
	
	@GetMapping("/board/{seq}")
	public Board getBoard(@PathVariable Long seq) {
		//optional은 예외처리를 쉽게하려고 쓰는거임, .get()추가해서 Board객체 반환
		return boardRepo.findById(seq).get();
		 
	}
	
	@PostMapping("/board")
	public Board postBoard(Board board) {
		board.setCreateDate(new Date());
		return boardRepo.save(board);
	}
	
	@PutMapping("/board")
	public Board putBoard(Board board) {
//		Board cngBoard = getBoard(board.getSeq());
//		cngBoard.setTitle(board.getTitle());
//		cngBoard.setWriter(board.getWriter());
//		cngBoard.setContent(board.getContent());
		board.setCreateDate(new Date());
		return boardRepo.save(board);
	}
	
	@DeleteMapping("/board/{seq}")
	public ResponseEntity<?> deleteBoard(@PathVariable Long seq) {
		Board board = getBoard(seq);
		try {
			boardRepo.deleteById(seq); //void
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete 오류");
			return ResponseEntity.status(500).body("삭제 안됨");
		}
		return ResponseEntity.ok(board); // 삭제 안되도 리턴 > 삭제된걸 어떻게 확인
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
