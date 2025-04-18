package com.rubypaper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient {
	//글 등록(사용하려면 main 메서드로)
	public static void addText(EntityManagerFactory emf) {
		//List<Board> list = new ArrayList<Board>();
		EntityManager em = emf.createEntityManager();
		
		//Transaction 생성 >> 등록/수정/삭제시 필수---------------------------------------!
		EntityTransaction tx = em.getTransaction();
		try {
			//Transaction 시작
			tx.begin();
			
			for(int i = 1; i <= 5 ; i++) {
				Board board = new Board();
				board.setTitle("JPA 제목"+i);
				board.setWriter("관리자"+i);
				board.setContent("JPA 글 등록 잘됨");
				board.setCreateDate(new Date());
				board.setCnt(0L);
				
				//list.add(board);
				//글 등록
				em.persist(board);
			}
			
			//Transaction commit
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			
			//Transaction rollback
			tx.rollback();
		}finally {
			em.close();
		}
				
	}
	
	//검색 기능
	public static void select(EntityManagerFactory emf) {
		//EntityManager 생성
		EntityManager em = emf.createEntityManager();
		
		try {
			//글 상세 조회
			//for(Long i = 1L; i <= 5 ; i++) {
				Board searchBoard = em.find(Board.class, 1L);//Board.class타입으로 받음 ///1L= id 1번
				
				System.out.println("---> " + searchBoard.toString());
			//}
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			em.close();
		}
	}
	
	//update
	public static void update(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		//transaction 생성
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			//수정할 게시글 조회
			Board board = em.find(Board.class, 2L);
			board.setTitle("검색한 게시글의 제목 수정");
			
			//Transaction commit
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			
			//transaction rollback
			tx.rollback();
		}finally {
			em.close();
		}
	}
	
	//delete
	public static void delete(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		//transaction 생성
		EntityTransaction tx = em.getTransaction();
		try {
			//transaction 시작
			tx.begin();
			
			//삭제할 게시글 조회
			Board board = em.find(Board.class, 3L);
			em.remove(board);
			
			//Transaction commit
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			
			//transaction rollback
			tx.rollback();
		}finally {
			em.close();
		}
	}
	
	//JPQL
	public static void jpql(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		//transaction 생성
		EntityTransaction tx = em.getTransaction();
		try {
			//transaction 시작
			tx.begin();
			
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘됨");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			em.persist(board);
			
			//transaction commit
			tx.commit();
			
			//목록 조회
			String jpql = "select b from Board b order by b.seq desc";
			List<Board> boardList =
				em.createQuery(jpql, Board.class).getResultList();
			for(Board brd : boardList) {
				System.out.println("---> " + brd.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
			//transaction rollback
			tx.rollback();
		}finally {
			em.close();
		}
	}
	
	public static void main(String[] args) {
		//EntityManagerFactory 생성(emf는 한클래스당 하나) -----------------------------------------!
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		
//		addText(emf);
//		System.out.println("생성1" + "-".repeat(50));
//		select(emf);
//		update(emf);
//		delete(emf);
//		System.out.println("생성2" + "-".repeat(50));
//		select(emf);
		
		jpql(emf);
		
		emf.close();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
