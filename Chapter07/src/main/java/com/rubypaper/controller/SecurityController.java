package com.rubypaper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	@GetMapping("/")
	public String index() {
		System.out.println("index 요청");
		return "index"; //@Controller, 리턴타입 String >> 해당 html로 이동
	}

	@GetMapping("/member") //@Controller, 리턴타입 void >> 해당 html로 이동
	public void member() {
		System.out.println("Member 요청");
	}

	@GetMapping("/manager")
	public void manager() {
		System.out.println("Manager 요청");
	}
	@GetMapping("/admin")
	public void admin() {
		System.out.println("Admin 요청");
	}
}
