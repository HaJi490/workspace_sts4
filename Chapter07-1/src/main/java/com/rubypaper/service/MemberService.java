package com.rubypaper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	public void save(Member member) {
		// name값이 없을 때 
		if(member.getName() == null) {
			member.setName("Member");
		}
		// role 설정(회원가입은 무조건 Member || Manager, Admin은 db차원에서 관리 )
		if(member.getName().equals("Member")) { // "=="은 주소비교
			member.setRole(Role.ROLE_MEMBER);
		} else if(member.getName().equals("Manager")){
			member.setRole(Role.ROLE_MANAGER);
		} else if(member.getName().equals("Admin")){
			member.setRole(Role.ROLE_ADMIN);
		}
		// 비밀번호 암호화
		member.setPassword(encoder.encode(member.getPassword()));
		memberRepo.save(member);
	}
}
