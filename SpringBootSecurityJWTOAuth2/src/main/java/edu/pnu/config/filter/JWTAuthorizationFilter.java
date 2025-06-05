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

@RequiredArgsConstructor // 생성자가 없더라도 롬복에서 생성자 만들어줌, final붙은 필드만 -----------------------------!
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private final MemberRepository memberRepo ;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String srcToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(srcToken == null || !srcToken.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String jwtToken = srcToken.replace("Bearer ", "");
		
		// 토큰에서 username 추출
		String username = JWT.require(Algorithm.HMAC256("edu.pnu.jwt")).build().verify(jwtToken).getClaim("username").asString();
		
		Optional<Member> opt = memberRepo.findById(username);	// 토큰에서 얻은 username으로 DB를 검색해서 사용자를 검색
		if(!opt.isPresent()) {									// 사용자가 존재하지 않는다면
			filterChain.doFilter(request, response);			// 필터를 그냥 통과
			return;
		}
		
		Member findmember = opt.get();
		
		// DB에서 읽은 사용자 정보를 이용해서 UserDetails 타입의 객체 생성
		User user = new User(findmember.getUsername(), findmember.getPassword(), 
							AuthorityUtils.createAuthorityList(findmember.getRole().toString()));
		
		// Authentication 객체 생성: 사용자명과 권한 관리를 위한 정보를 입력
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		// 시큐리티 세션에 등록
		SecurityContextHolder.getContext().setAuthentication(auth);
				
		filterChain.doFilter(request, response);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
