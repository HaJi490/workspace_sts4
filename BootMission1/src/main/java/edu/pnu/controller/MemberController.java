package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberController {
	List<MemberVO> list = new ArrayList<MemberVO>();
	
	public MemberController(){
		for(int i = 1 ; i <=10 ; i++) {
			list.add(MemberVO.builder()
						.id(i)
						.pass("1234")
						.name("사용자"+i)
						.regidate(new Date())
						.build());
		}
	}
	
	//검색(Read - select)
	@GetMapping("/members")
	public List<MemberVO> members() {
		return list;
	}
	
	//검색(Read - select)
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {//파라미터에서 받은 id
		for (MemberVO m : list) {
			if(m.getId() == id)
				return m;
		}
		return null;
	}

	
	//입력(Create - insert)
	// localhost:8080/member?seq=1 -> 매개변수((name="seq")MemberVO member)하면 됨
	@PostMapping("/member")
	public ResponseEntity<?> addMember(MemberVO member) {//ResponseEntity #타입 관계없이 사용가능
		if(getMemberById(member.getId()) != null) {
			//System.out.println("'"+member.getId() + "'는 이미 존재함");//콘솔에서 확인
			return ResponseEntity.ok("'"+member.getId() + "'은/는 이미 존재함"); //return ResponseEntity.notFound().build();
		}
		member.setRegidate(new Date());
		list.add(member);
		return ResponseEntity.ok(member);
	}
	
	//수정(Update - update)
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO member) {
		//입력값이 없으면 리턴
		if(member.getId() == null) {
			System.out.println("입력값이 없음");
			return null;
		}
		//입력값이 리스트에 존재하면 바꾼값 리턴
		for(MemberVO m : list) {
			if(m.getId() == member.getId()) {
				m.setPass(member.getPass());
				m.setName(member.getName());
				m.setRegidate(new Date());
				return m;
			}
		}
		//입력값이 리스트에 없으면 리턴
		System.out.println("수정할 데이터없음");
		return null;
	}
	
	//삭제(Delete - delete)
	@DeleteMapping("/member")
	public int deleteMember(Integer id) {
		try {
			list.remove(getMemberById(id));
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}
	
	//입력(Create - insert)
	@PostMapping("/memberJSON")
	public ResponseEntity<?> addMemberJSON(@RequestBody MemberVO member) {
		if(getMemberById(member.getId()) != null) {
			return ResponseEntity.ok(member.getId()+"이/가 이미 존재합니다.");
		}
		member.setRegidate(new Date());
		list.add(member);
		return ResponseEntity.ok(member);
	}
	
	
	
	

}
