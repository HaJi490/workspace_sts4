package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable());
		
		// Authentication 등록
		http.authorizeHttpRequests(auth->auth
				.requestMatchers(" /member/**").authenticated()
				.requestMatchers(" /manager/**").hasAnyRole("manager", "admin") // 전체 대문자해야하나----------------?
				.requestMatchers(" /member/**").hasRole("admin")
				.anyRequest().permitAll());
		
		// 명시적 제거
		http.formLogin(frmLogin->frmLogin.disable());
		http.httpBasic(basic->basic.disable());
		// 세션유지 않겠다고 설정
		http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
}
