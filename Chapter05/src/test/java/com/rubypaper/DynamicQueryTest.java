package com.rubypaper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;
import com.rubypaper.domain.Board;
import com.rubypaper.domain.QBoard;
import com.rubypaper.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {
	
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	@Test
	public void testDynamicQuery() {
		String searchCondition = "CONTENT"; //필드
		String searchKeyword= "테스트 내용 1"; //찾는 내용
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.contains(searchKeyword));
		}else if (searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%"+searchKeyword+"%"));
		}
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> pageInfo = boardRepo.findAll(builder, paging);
		
		List<Board> boardList = pageInfo.getContent();
		
		System.out.println("*** 검색결과 ***");
		for(Board b: boardList) {
			System.out.println("---> " + b);
		}
	}
}
