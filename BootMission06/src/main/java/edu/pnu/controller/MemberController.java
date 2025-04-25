package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	MemberService service;
	
	@GetMapping("/members")
	public ResponseEntity<?> getMembers(){
		return ResponseEntity.ok(service.getMembers());
	}
	
	@GetMapping("/member/{id}")
	public ResponseEntity<?> getMemberById(@PathVariable Integer id){
		return ResponseEntity.ok(service.getMemberbyId(id));
	}
	
	@PostMapping("/member")
	public ResponseEntity<?> postMember(Member member){
		return ResponseEntity.ok(service.postMember(member));
	}

	@PutMapping("/member")
	public ResponseEntity<?> putMember(Member member){
		return ResponseEntity.ok(service.putMember(member));
	}
	
	@DeleteMapping("/member/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable Integer id){
		return ResponseEntity.ok(service.deleteMember(id));
	}
	
}
