package com.rubypaper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.MemberDao;
import com.rubypaper.domain.MemberVO;

@TestMethodOrder(OrderAnnotation.class) //jupiter order사용!
@SpringBootTest //모든 어노테이션을 확인하기 때문에 다른 파일이 오류있어도 실행안됨
public class MemberDaoTest {
	
	@Autowired
	private MemberDao dao;
	private int i;
	
	@Order(3)
	@Test
	@DisplayName("Member select All 테스트")
	public void selectTest() {
		Map<String, Object> map = dao.getMembers();
		@SuppressWarnings("unchecked")
		List<MemberVO> list = (List<MemberVO>)map.get("list");
		for(MemberVO m : list)
			System.out.println(m);
	}
	
	@Order(1)
	@Test
	@DisplayName("Member insert 테스트")
	public void insertTest() {
		for(int i =1 ; i <= 3 ; i++) {	
			MemberVO m =MemberVO.builder()
								.id( i )
								.pass("1234")
								.name("test"+i)
								.regidate(new Date())
								.build();
			dao.postMember(m);
		}	
		System.out.println("InsertTest");
	}
	
	@Order(2)
	@Test
	@DisplayName("Member update 테스트")
	public void updateTest() {
		MemberVO m = MemberVO.builder()
				.id( i )
				.pass("1234")
				.name("test"+20)
				.regidate(new Date())
				.build();
		dao.putMember(m);
		System.out.println("UpdateTest");
	}
	
	@Order(4)
	@Test
	@DisplayName("Member delete 테스트")
	public void deleteTest() {
		dao.deleteMember(i-1);
		i++;
		System.out.println("DeleteTest");
	}
}
