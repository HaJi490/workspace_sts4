package edu.pnu.controller;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController // Controller + ResponseBody
public class MemberController {
	
	@Autowired //필요한 bean을 자동으로 연결
	private MemberService service;
	
	
	@GetMapping("/members")
	public List<MemberDTO> getMembers(){
		return service.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public ResponseEntity<?> getMemberById(@PathVariable Integer id) {
		MemberDTO member = service.getMemberById(id);
		if(member == null) {
			return ResponseEntity.status(400).body("해당 id는 없습니다.");
		}
		return ResponseEntity.ok(member);
	}
	
	@PostMapping("/member")
	public String postMember(MemberDTO member) {
		int result = service.postMember(member);
		if( result > 0 ) {
			return "데이터가 추가되었습니다.";
		}
		return "insert data 실패";
	}
	
	@PutMapping("/member")
	public String putMember(MemberDTO member) {
		int result = service.putMember(member);
		if( result > 0 ) {
			return "데이터가 수정되었습니다.";
		}
		return "put data 실패";
	}
	
	@PatchMapping("/member")
	public String patchMember(MemberDTO member) {
		int result = service.patchMember(member);
		if( result > 0 ) {
			return "데이터가 부분수정되었습니다.";
		}
		return "put data 실패";
	}
	
	@DeleteMapping("/member/{id}")
	public String deleteMember(@PathVariable Integer id) {
		int result = service.deleteMember(id);
		if( result > 0) {
			return "데이터가 삭제되었습니다.";
		}
		return "delete data 실패";
	}
}
