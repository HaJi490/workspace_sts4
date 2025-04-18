package edu.pnu.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.BootMission31Application;
import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {

    private final BootMission31Application bootMission31Application;
	MemberService service = new MemberService();
	
	public MemberController(BootMission31Application bootMission31Application) {
		service = new MemberService();
		this.bootMission31Application = bootMission31Application;
	}
	
	//GET
	@GetMapping("/members")
	public List<MemberDTO> getMembers(){
		return service.getMember();
	}
	
	//GET
	@GetMapping("/member/{id}")
	public ResponseEntity<?> getMemberById(@PathVariable Integer id) {
		MemberDTO member = service.getMemberById(id);
		if(member == null) {
			return ResponseEntity.status(400).body("해당 id는 존재하지 않습니다.");
		}
		return ResponseEntity.ok(member);
	}
	
	//POST
	@PostMapping("/member")
	public ResponseEntity<?> insertMember(MemberDTO member) {
		if(member == null) {//입력값이 없을때
			return ResponseEntity.status(400).body("추가할 데이터를 입력하세요.");
		}
		
		member = service.insertMember(member);
		
		if(member == null)
			return ResponseEntity.status(400).body("데이터추가 실패");
		return ResponseEntity.ok(member);
	}
	
	//PUT
	@PutMapping("/member/{id}")
	public ResponseEntity<?> putMember(@PathVariable Integer id, MemberDTO member){
		if(member == null) {
			return ResponseEntity.status(400).body("변경할 데이터를 입력하세요.");
		}
		
		member = service.putMember(id, member);
		
		if(member == null)
			return ResponseEntity.status(400).body("데이터변경 실패");
		return ResponseEntity.ok(member);
	}
	
	//PATCH
	@PatchMapping("/member/{id}")
	public String fetchMember (@PathVariable Integer id, MemberDTO member) {
		if( member == null) {
			return "변경할 정보를 입력하세요.";
		}
		int result = service.patchMember(id, member);
		if(result > 0 ) {
			return "patch 성공";
		}
		return "patch 실패";
		
		
	}
	
	
	//DELETE
	@DeleteMapping("/member/{id}")
	public String deleteMember(@PathVariable Integer id){
		if( id == null) {
			return "삭제할 데이터를 입력하세요.";
		}
		int result = service.deleteMember(id);
		
		if( result > 0) {
			return id + "를 삭제했습니다";
		}
		return "해당 id는 없는 아이디입니다.";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
