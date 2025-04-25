package edu.pnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.BoardRepository;
import lombok.Data;

@Data //Lombok제공: getter, setter, toString.. 같은 메서드 자동생성
class BoardFilter{//쿼리스트링으로 보낸값을 받음 --> 자동바인딩
	private String key;
	private String value;
	private Integer pageNum = 0;
	private Integer pageSize = 5;
}

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	BoardRepository boardRepo;
	
	@GetMapping("/board")
	public BoardFilter getBoard(BoardFilter bf) {
		System.out.println(bf);
		return bf;
	}
	
	@GetMapping("/boardlist")
	public List<Board> getBoardList(BoardFilter bf){
		if (bf.getKey() == null) {
			if (bf.getPageNum() == 0) {
				return boardRepo.findAll();
			}
			else {
				 Pageable paging = PageRequest.of(bf.getPageNum() - 1, bf.getPageSize());
				 Page<Board> boardList =boardRepo.findAll(paging);
				 return boardList.getContent();
			}
		}
		else {
			BooleanBuilder builder = new BooleanBuilder();//쿼리문 생성(select, from)
			QBoard qboard = QBoard.board; //QBoard.엔티티 이름(where 조건)
			if (bf.getKey().equalsIgnoreCase("title")) {
				builder.and(qboard.title.contains(bf.getValue()));
			}
			else if (bf.getKey().equalsIgnoreCase("content")) {
				builder.and(qboard.content.contains(bf.getValue()));
			}
			if (bf.getPageNum() == 0) {
				List<Board> list = new ArrayList<>();
				Iterable<Board> iter = boardRepo.findAll(builder);
				iter.forEach(b -> list.add(b));
				return list;
			}
			else {
				 Pageable paging = PageRequest.of(bf.getPageNum() - 1, bf.getPageSize());
				 Page<Board> boardList = boardRepo.findAll(builder, paging);
				 return boardList.getContent();
			}
		}
	}
	
	
}
