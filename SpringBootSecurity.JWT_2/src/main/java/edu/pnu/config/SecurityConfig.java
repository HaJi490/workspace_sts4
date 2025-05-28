package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
public class SecurityConfig {
	@Autowired
	private AuthenticationConfiguration authConfig;
	@Autowired
	private MemberRepository memRepo;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable());
		// 인가 필터 등록
		http.addFilterBefore(new JWTAuthorizationFilter(memRepo), AuthorizationFilter.class);
		
		// AuthorizationFilter 등록
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.formLogin(frmLogin->frmLogin.disable());
		http.httpBasic(basic->basic.disable());
		http.sessionManagement(sm->sm.sessionCreationPolicy(
							SessionCreationPolicy.STATELESS));
		
		// 필터링 생성
		http.addFilter(new JWTAuthenticationFilter(authConfig.getAuthenticationManager()));
		return http.build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
