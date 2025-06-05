package edu.pnu.controller;

import java.net.URLDecoder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {
	@GetMapping({"/", "/index"})
	public @ResponseBody String index() {	//RestController안하면 @ResponseBody붙여줘야함
		return "index";
	}
	@GetMapping({"/member"})
	public @ResponseBody String member() {
		return "member";
	}
	@GetMapping({"/manager"})
	public @ResponseBody String manager() {
		return "manager";
	}
	@GetMapping({"/admin"})
	public @ResponseBody String admin() {
		return "admin";
	}
	@GetMapping("/login")
	public String login() {
		return "index.html";
	}
	
	@PostMapping("/api/jwtcallback")
	public @ResponseBody ResponseEntity<?> jwtCallBack(HttpServletRequest request){
		String jwtToken = null;
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie: cookies) {
			if(!cookie.getName().equals("jwtToken")) continue;
			try {
				// 쿠키에 저장된 토큰은 인코딩되어 있으므로 디코딩해서 저장
				jwtToken = URLDecoder.decode(cookie.getValue(), "utf-8");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		// 응답헤더(Authorization)에 JWT를 저장해서 응답
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken).build();
 	}
}
