package edu.pnu.config.filter;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor 
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager authManager; // 인증객체
	
	@Override	//POST/login이 왔을때(*상속받은 객체가 가로챔) 인증을 시도하는 메서드
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper(); // Objectmapper:: 객체로부터 Json 형태의 문자열을 만들어냄-------------------------!
		try {
			Member member = mapper.readValue(request.getInputStream(), Member.class); // json타입을 읽어서 Member객체에 저장
			
			// security에 자격 증명에 필요한 객체 생성
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
		}
		
		return null;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	}
}
