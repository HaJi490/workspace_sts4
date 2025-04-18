package edu.pnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	public MemberController(MemberService service) {

	}
	
	@GetMapping("/members")
	public List<MemberDTO> getMembers(){
		return service.getMembers();
		
	}
	
	

}
