package com.rubypaper.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rubypaper.config.SecurityUser;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.MemberRepository;

@Service
public class BoardUserDetailService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// MemberRepository로 회원 정보를 조회하여
		// UserDetails 타입의 객체로 리턴한다.
		Optional<Member> optional = memberRepo.findById(username);
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(username+ "사용자 없음");
		} else {
			Member member = optional.get();
			return new SecurityUser(member); //User정보를 추가적으로 저장하고 싶을때 사용
//			return new User(member);	// 로그인 유무 정보만 필요하다면 User만 사용해도 됨(ㅈㅣ금 코드도 ㅇㅇ)
		}
	}

}
