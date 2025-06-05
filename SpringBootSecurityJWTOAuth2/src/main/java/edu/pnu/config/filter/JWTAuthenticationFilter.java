package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException; //javax는 거의 안씀, jakarta버전만 쓰기때문에
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
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;	//인증 객체
	
	@Override	 // **POST/login 요청이 왔을 때 인증을 시도하는 메소드**
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// request에서 json 타입의 [username/password]를 읽어서 Member 객체를 생성한다.
		ObjectMapper mapper = new ObjectMapper();
		try {
			Member member = mapper.readValue(request.getInputStream(), Member.class);
			// Security에게 자격 증명 요청에 필요한 객체 생성
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			
			// 인증 진행 -> UserDetailService의 loadUserByUsername에서 
			// ""DB로부터 사용자 정보(service 클래스)를 읽어와 사용자 입력 정보와 비교""
			// 비교 한 뒤 자격 증명에 성공하면 Authentication 객체를 만들어서 리턴한다.
			return authenticationManager.authenticate(authToken);	
			
		} catch (Exception e) {
			log.info(e.getMessage()); //"자격 증명에 실패하였습니다." 로그 출력
			response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 자격 증명에 실패하면 응답코드 리턴
		}
		return null;
	}
	
	@Override	// **인증이 성공했을 때 실행되는 후처리 메서드**
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// 자격 증명이 성공하면 loadByUsername에서 만든 객체가 autoResult에 담겨 있다.
		User user = (User)authResult.getPrincipal();
		System.out.println("auth: " + user); //user 객체를 콘솔에 출력해서 확인
		
		// username으로 JWT를 생성해서 Response Header - Authorization에 담아서 돌려준다.
		// 이것은 하나의 예시로서 필요에 따라 추가 정보를 담을 수 있다.
		String token = JWT.create()
						.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*100))
						.withClaim("username", user.getUsername())
						.sign(Algorithm.HMAC256("edu.pnu.jwt"));
		response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		response.setStatus(HttpStatus.OK.value());

	}

	
	
	
	
	
	
	
	
	
}
