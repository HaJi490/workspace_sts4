package edu.pnu.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
@RequestMapping("/api") //통일된 url
public class MemberController {
	private MemberService service;
	
	public MemberController() {
		service = new MemberService();
	}
	
	@GetMapping("/members")
	public List<MemberVO> getMembers(){
		return service.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMemberById(@PathVariable(name="id") Integer idd) {
		return service.getMemberById(idd);
	}
	
	@PostMapping("/member")
	public ResponseEntity<?> insertMember(MemberVO member){
		//있는 아이디면 리턴
		if(getMemberById(member.getId()) != null) {
			return ResponseEntity.ok("이미 존재하는 id");
		}
		
		return ResponseEntity.ok(service.insertMember(member));
	}
	
	@PutMapping("/member")
	public ResponseEntity<?> putMember(@RequestBody MemberVO member){//@ >Json형식으로 Body에서 받은 정보를 
		if(member.getId() == null)	//입력값이 없으면
			return ResponseEntity.status(400).body("수정할 id를 입력하세요");
		
		member = service.putMember(member);
		
		if(member == null) //id가 존재하지 않으면 리턴
			return ResponseEntity.ok("존재하지 않는 id");
		
		
		return ResponseEntity.ok(member);
	}
	
	@PatchMapping("/member/{id}")
	public ResponseEntity<?> patchMember(@PathVariable Integer id, @RequestBody MemberVO member){//@ >url에서 받은 정보
		if(id == null) 		//입력값이 없으면
			return ResponseEntity.status(400).body("수정할 id를 입력하세요");
		
		member = service.patchMember(id, member);
		
		if (member == null) 
			return ResponseEntity.status(400).body("id가 존재하지 않습니다.");
		
		
		return ResponseEntity.ok(member);
	}
	
	@DeleteMapping("/member/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable Integer id){
		//입력한 id가 없으면
		if(id == null)
			return ResponseEntity.status(400).body("삭제할 id를 입력하세요");
		
		MemberVO member =service.deleteMember(id); 
		
		if(member == null) 
			return ResponseEntity.status(400).body("존재하지 않는 id");
		
		
		return ResponseEntity.ok(member);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
