package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig2 {
	
	// SecurityConfig가 OAuth2SuccessHandler를 주입 받고,
	// 동시에 OAuth2SuccessHandler는 PasswordEncoder를 주입 받아 "순환참조 오류발생"
	// Encoder Bean을 따로 생성
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
