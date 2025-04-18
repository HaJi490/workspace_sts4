package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberService {
	List<MemberVO> list = new ArrayList<MemberVO>();
	
	public MemberService() {
		for(int i = 1; i <= 10 ; i++) {
			list.add(MemberVO.builder()//list.add***********************************************
					.id(i)
					.pass("1234")
					.name("member"+i)
					.regidate(new Date())
					.build());
		}
	}
	
	//GET_select
	public List<MemberVO> getMembers() {
		return list;
	}
	
	//GET_select
	public MemberVO getMemberById(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id)
				return m;
		}
		System.out.println("해당 아이디는 존재하지 않음");
		return null;
	}
	
	//POST_insert
	public MemberVO insertMember(MemberVO member){
		member.setRegidate(new Date());
		list.add(member);
		return member;
	}
	
	//PUT_update
	public MemberVO putMember(MemberVO member){
		//update(객체 자체를 바꾸)
		for(int i = 0 ; i < list.size() ; i++) {
			MemberVO m = list.get(i);
			if(m.getId() == member.getId()) {
//				m.setName(member.getName());
//				m.setPass(member.getPass());
				member.setRegidate(new Date());
				//확장형 for문의 객체를 바꾸면 list의 해당ㄹ체가 바뀌는게 아님
				list.set(i, member);
				return member;
			}
		}
		return null;
	}
	
	//PATCH_부분update
	public MemberVO patchMember(Integer id, MemberVO member){
	
		//patch
		for(MemberVO m : list) {
			if(m.getId() == id) {
				if (member.getName() != null)
					m.setName(member.getName());
				if (member.getPass() != null)
					m.setPass(member.getPass());
				return m;
			}
		}
		return null;
	}
	
	//DELETE_delete
	public MemberVO deleteMember(Integer id){
		//delete
		for(int i = 0 ; i < list.size() ; i++) {
			if(list.get(i).getId() == id)
				return list.remove(i);
		}
		return null;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
}
