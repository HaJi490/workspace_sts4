package com.rubypaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rubypaper.config.SecurityUser;
import com.rubypaper.domain.Member;
import com.rubypaper.service.BoardUserDetailService;
import com.rubypaper.service.MemberService;

@Controller
public class LoginContoller {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/login")
	public void login() {
		System.out.println("login 요청");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess 요청");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("accessDenied");
	}
	
	// 로그인 세션 정보 확인용 URL > 세션에 저장된 객체의 일부 내용을 주입(어노테이션 이용)
	@GetMapping("/auth")
	public @ResponseBody ResponseEntity<?> auth(@AuthenticationPrincipal SecurityUser user){
		if(user == null) {
			return ResponseEntity.ok("로그인 상태가 아닙니다.");
		}
		return ResponseEntity.ok(user);
	}
	
	// 로그인 세션 정보 확인용 URL > 세션에 저장된 객체 자체를 주입
	@GetMapping("/auth2")
	public @ResponseBody void auth1(Authentication auth){
		System.out.println(auth.getPrincipal());
		System.out.println(auth.getCredentials());
		System.out.println(auth.getAuthorities());
	}
	
	@GetMapping("/join")
	public void join() {}
	
	@PostMapping("/join")
	public String joinProc(Member member) {
		memberService.save(member);
		return "welcome";
	}
}
