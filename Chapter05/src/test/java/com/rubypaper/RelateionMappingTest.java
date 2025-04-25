package com.rubypaper;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) //method꺼, test할때는 jupyter----------------------------------!
public class RelateionMappingTest {
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	
	//@BeforeAll로 해도됨
	@Order(1)
	@Test
	public void testManyToOneInsert() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("둘리");
		member1.setRole("User");
		//memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("도우너");
		member2.setRole("User");
		//memberRepo.save(member2);
		
		for(int i =1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle("둘리가 등록한 게시글 "+i);
			board.setContent("둘리가 등록한 게시글 내용 "+i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			//boardRepo.save(board);
		}
		memberRepo.save(member1); // Member에 정보 넣으면 Board정보 넣는것도 같이 실행
		for(int i =1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle("도우너가 등록한 게시글 "+i);
			board.setContent("도우너가 등록한 게시글 내용 "+i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			//boardRepo.save(board);
		}
		memberRepo.save(member2);
	}
	
	@Order(2)
	@Test
	public void testTwoWayMapping() {
		Member member = memberRepo.findById("member1").get();
		
		System.out.println("=".repeat(100));
		System.out.println(member.getName()+"가(이) 저장한 게시글 목록");
		System.out.println("=".repeat(100));
		List<Board> list = member.getBoardList();
		for(Board board : list) {
			System.out.println(board.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
