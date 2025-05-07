package com.rubypaper.config;

import javax.sql.DataSource;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	//Security 설정하기
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(security -> security
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasAnyRole("ADMIN")
				.anyRequest().permitAll());
		http.csrf(cf -> cf.disable());
		
		//로그인 처리
		http.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/loginSuccess", true));
		
		//예외 처리
		http.exceptionHandling(ex -> ex.accessDeniedPage("/accessDenied"));
		
		//로그아웃 처리
		http.logout(logout -> logout.invalidateHttpSession(true)
									.deleteCookies("JSSESSIONID")
									.logoutSuccessUrl("/login"));
				
		return http.build();
	}
	
	
	//비밀번호 암호화
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Autowired
//	private DataSource dataSource;
//	
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
//		//1. 메모리 사용자 등록하기(잘 안씀)
////		auth.inMemoryAuthentication().withUser("member").password("{noop}abcd").roles("MEMBER");
////		auth.inMemoryAuthentication().withUser("manager").password("{noop}abcd").roles("MANAGER");
////		auth.inMemoryAuthentication().withUser("admin").password("{noop}abcd").roles("ADMIN");
//		
//		//2. 데이터베이스 연동하기(잘 안씀)
//		String query1 ="select id username, concat('{noop}', password) password, true eabled from member where id =?";
//		String query2 ="select id, role from member where id=?";
//		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query1).authoritiesByUsernameQuery(query2);
//		
//	}
}
