//package com.rubypaper;
//
//import java.util.Date;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import com.rubypaper.domain.Board;
//import com.rubypaper.persistence.BoardRepository;
//
//@SpringBootTest
//public class QueryAnnotationTest {
//	@Autowired
//	private BoardRepository boardRepo;
//	
//	//@BeforeAll
//	static void dataPrepare(@Autowired BoardRepository boardRepo) {
//		for(int i = 1 ; i<= 200 ; i++) {
//			boardRepo.save(Board.builder()
//								.title("테스트 제목 "+i)
//								.writer("테스터")
//								.content("테스트 내용 "+i)
//								.createDate(new Date())
//								.cnt(0L)
//								.build());
//		}
//	}
//	
//	//@Test
//	public void testQueryAnnotationTest1() {
//		List<Board> boardList = boardRepo.queryAnnotationTest1("테스트 제목 10");
//		
//		for(Board b : boardList) {
//			System.out.println(b);
//		}
//	}
//	
//	//@Test
//	public void testQueryAnnotationTest2() {
//		List<Board> boardList = boardRepo.queryAnnotationTest2("테스트 제목 10");
//		
//		for(Board b : boardList) {
//			System.out.println(b);
//		}
//	}
//	
//	//@Test
//	public void testQueryAnnotationTest3() {
//		List<Object[]> boardList = boardRepo.queryAnnotationTest3("테스트 제목 10");
//		
//		//Arrays.toString(boardList);
//		for(Object[] b : boardList) {
//			System.out.println(b);
//		}
//	}
//	
//	@Test
//	public void testQueryAnnotationTest4() {
//		List<Object[]> boardList = boardRepo.queryAnnotaionTest4("테스트 제목 10");
//		
//		System.out.println("*** 검색결과 ***");
//		for(Object[] b : boardList) {
//			System.out.println("---> " + b);
//		}
//	}
//	
//	@Test
//	public void testQueryAnnotation5() {
//		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
//		
//		//Page<Board> pageInfo = boardRepo.queryAnnotaionTest5(paging);
//		Page<Board> pageInfo = boardRepo.queryAnnotaionTest5(paging);
//		
//		System.out.println("PAGE SIZE: " + pageInfo.getSize());
//		System.out.println("NEXT: " + pageInfo.nextPageable());
//		
//		System.out.println("*** 검색결과 ***");
//		for(Board b: pageInfo) {
//			System.out.println("---> " + b);
//		}
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//}
