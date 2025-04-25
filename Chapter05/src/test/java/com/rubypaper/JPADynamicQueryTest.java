package com.rubypaper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@SpringBootTest
public class JPADynamicQueryTest {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void testDynamicQuery() {
		String searchCondition = "title";
		String searchKeyword =  "테스트 제목 1";
		
		StringBuilder sb = new StringBuilder("select b from Board b where 1=1");
		if(searchCondition.equals("title")) {
			sb.append(" And b.title like '%" + searchKeyword + "%'");
		}else if (searchCondition.equals("content")) {
			sb.append(" And b.content like '%" + searchKeyword + "%'");
		}
		sb.append(" Order By b.id Asc");
		
		TypedQuery<Board> query = entityManager.createQuery(sb.toString(), Board.class);
		
		//페이징 설정
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Board> list = query.getResultList();
		
		//검색 결과 출력
		System.out.println("*** 검색결과 ***");
		for(Board b : list) {
			System.out.println("---> " + b);
		}
	}
}
