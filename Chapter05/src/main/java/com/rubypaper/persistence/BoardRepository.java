package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

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
}
