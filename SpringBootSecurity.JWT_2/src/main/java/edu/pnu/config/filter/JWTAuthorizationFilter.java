package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter{	// 상속받은 객체에 의해 하나의 요청에 한번만 실행
	// 인가 설정을 위해 사용자의 Role정보를 읽어들이기 위한 객체 설정
	private final MemberRepository memRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String srcToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (srcToken == null || !srcToken.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String jwtToken = srcToken.replace("Bearer ", "");
		
		// 토큰에서 username 추출
		String username = JWT.require(Algorithm.HMAC256("edu.pnu.jwt")).build().verify(jwtToken).getClaim("username").asString();
		
		Optional<Member> opt = memRepo.findById(username);
		if(!opt.isPresent()) {
			filterChain.doFilter(request, response);
			return;
		}
		
		Member findmember = opt.get();
		
		// DB에서 읽은 사용자 정보를 이용해 UserDetails 타입의 객체생성
		User user = new User(findmember.getUsername(), findmember.getPassword(), AuthorityUtils.createAuthorityList(findmember.getRole().toString()));
		
		// Authentication 객체 생성
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		// 시큐리티 세션에 등록
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
	}
}
