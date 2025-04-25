package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import edu.pnu.service.MemberService;

 //getMemberbyId오류해결----------------------------------------?
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private MemberService service;
	
	//@Test
	public void testInsertMember() {
		for(int i = 0 ; i <= 10; i++) {
			memberRepo.save(Member.builder()
									.pass("1234")
									.name("user"+i)
									.regidate(new Date())
									.build());
		}
	}
	
	@Order(4)
	//@Test
	public void testGetMembers() {
		List<Member> members = service.getMembers();
		
		for(Member m: members) {
			System.out.println("---> "+ m);
		}
	}
	
	//@Test
	//@Transactional
	public void testGetMemberById(){
		Member m = service.getMemberbyId(3);
		System.out.println("GETBYID---> "+ m);
	}
	
	@Order(1)
	@Test
	@Transactional //자동 rollback되서 테스트 안됨
	public void testPostMember() {
		Member member = Member.builder()
				.pass("9999")
				.name("insert")
				.regidate(new Date())
				.build();
		Member m = service.postMember(member);
		System.out.println("POST---> "+ m);
	}
	
	//@Order(2)
	//@Test
	public void testPutMember() {
		Member member = Member.builder()
				.id(5)
				.pass("8888")
				.name("put")
				.regidate(new Date())
				.build();
		Member m = service.putMember(member);
		System.out.println("PUT---> "+ m);
	}
	
	
	@Order(3)
	//@Test
	public void testDeleteMember() {
		Member m =service.deleteMember(9);
		System.out.println("DELETE---> "+ m);
	}
	
	
	
	
	
	
	
	
	
	
	
}
