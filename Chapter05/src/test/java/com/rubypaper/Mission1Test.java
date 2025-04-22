package com.rubypaper;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class Mission1Test {
	@Autowired
	private BoardRepository boardRepo;
	
	@BeforeAll
	static void dataPrepare(@Autowired BoardRepository boardRepo) {//BoardRepository를 매개변수로 받아야하는 이유: 정적메서드(static) >> 인스턴스 필드에 접근못함(클래스의 인스턴스가 없어도 호출되므로)
	Random rnd = new Random();
	for(int i = 1 ; i <= 100 ; i++ ) {
		boardRepo.save(Board.builder()
							.title("테스터 제목"+ i)
							.writer("테스터")
							.content("테스터 내용 "+ i)
							.createDate(new Date())
							.cnt(rnd.nextLong(100L))
							.build());
		}
	}
	
	//@Test
	public void testFindByTitleContaining() {
		List<Board> boardList = boardRepo.findByTitleContaining("1");
		
		System.out.println("***검색 결과***");
		for(Board board: boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	//@Test
	public void testFindTitleContainingAndCntGreaterThan() {
		List<Board> bList = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50L);
		
		System.out.println("***검색 결과***");
		for(Board board: bList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	//@Test
	public void testFindByGreaterThanEqualAndLessThanEqual() {
		List<Board> bList = boardRepo.findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeqAsc(10L, 50L);
		
		System.out.println("***검색 결과***");
		for(Board board: bList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	@Test
	public void testFindByTitleContainingOrContentContainingOrderBySeqDesc() {
		List<Board> bList = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2");
		
		System.out.println("***검색 결과***");
		for(Board board: bList) {
			System.out.println("--->" + board.toString());
		}
	}


}
