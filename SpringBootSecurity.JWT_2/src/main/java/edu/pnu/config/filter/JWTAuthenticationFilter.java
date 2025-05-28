package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter { //상속받은게 post /login 실행시 로그인을 가로챔------------------------------!
	private final AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		//request에서 json타입의 [username/password]를 읽어서 Member 객체를 생성한다
		ObjectMapper mapper = new ObjectMapper();
		try {
			Member member = mapper.readValue(request.getInputStream(), Member.class);
			// Security에게 자격 증명 요청에 필요한 객체 생성
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			// 인증 진행
			return authenticationManager.authenticate(authToken);
		} catch(Exception e) {	//자격 실ㅍㅐ 시
			log.info(e.getMessage());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		return null;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// 자격 증명 성공하면
		User user = (User) authResult.getPrincipal();
		System.out.println("auth:" + user);
		
		String token = JWT.create()
						.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*100))
						.withClaim("username", user.getUsername())
						.sign(Algorithm.HMAC256("edu.pnu.jwt"));
		response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ token);
		response.setStatus(HttpStatus.OK.value());
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
