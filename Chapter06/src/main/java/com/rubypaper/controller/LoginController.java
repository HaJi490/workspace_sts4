package com.rubypaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.rubypaper.domain.Member;
import com.rubypaper.service.MemberService;

@SessionAttributes("member")
@Controller
public class LoginController {
	@Autowired
	private MemberService memeberService;
	
	@GetMapping("/login")
	public void loginView() {
		
	}
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = memeberService.getMember(member);
		
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember);
			
			return "redirect:getBoardList"; //forward: getBoardList를 post방식인 것만 찾음
		} else {
			return "redirect:login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:index.html";
	}
}
