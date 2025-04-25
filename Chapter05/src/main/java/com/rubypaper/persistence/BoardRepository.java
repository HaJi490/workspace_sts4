package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rubypaper.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	//쿼리 메서드
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByContentContaining(String searchKeyword);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
	//쿼리메서드 실습
	List<Board> findByTitleContaining(String searchKeyword);
	List<Board> findByTitleContainingAndCntGreaterThan(String title, Long cnt);
	List<Board> findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeqAsc(Long cnt1, Long cnt2);
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
	
	//페이징
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
	//@Query
//	///위치 기반 파라미터
//	@Query("Select b From Board b Where b.title like %?1% Order By b.seq Desc")
//	List<Board> queryAnnotationTest1(String searchKeyword);
//	
//	///이름 기반 파라미터
//	@Query("Select b From Board b Where b.title like %:searchKeyword% Order By b.seq Desc")
//	List<Board> queryAnnotationTest2(@Param ("searchKeyword") String searchKeyword);
//	
//	///특정 변수만 조회
//	@Query("Select b.seq, b.title, b.writer, b.createDate From Board b "
//			+ "Where b.title like %?1% Order By b.seq Desc")
//	List<Object[]> queryAnnotationTest3(String searchKeyword);
//	
//	///네이티브 쿼리
//	@Query(value = "Select seq, title, writer, create_date From board "
//			+ "Where title like '%' ||?1|| '%' "
//			+ "Order By seq Desc", nativeQuery = true)
//	List<Object[]> queryAnnotaionTest4(String searchKeyword);
//	
//	///네이티브 쿼리_페이징
//	@Query("Select b from Board b order by b.seq Desc")
//	Page<Board> queryAnnotaionTest5(Pageable paging);
	
	
	
}
