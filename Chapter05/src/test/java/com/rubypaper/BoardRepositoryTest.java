package com.rubypaper;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;
	
	//@Test
	public void testInsertBoard() {
		Board board = Board.builder()
					.title("첫번째 게시글")
					.writer("테스터")
					.content("잘 등록되나요?")
					.createDate(new Date())
					.cnt(0L)
					.build();
		boardRepo.save(board);
	}
	
	//@Test
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		System.out.println(board);
	}
	
	//@Test
	public void testUpdateBoard() {
		System.out.println("== 1번 게시글 조회 ==");
		Board board = boardRepo.findById(1L).get();
		System.out.println("== 게시글 제목 수정 ==");
		board.setTitle("제목을 수정했습니다.");
		boardRepo.save(board);
	}
	
	@Test
	public void testDeleteBoard() {
		boardRepo.deleteById(1L);
	}

}
