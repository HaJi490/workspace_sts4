package com.rubypaper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.MemberRepository;

@Component
public class UserDataInit implements ApplicationRunner {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		{
			Member m1 = new Member();
			m1.setId("member");
			m1.setPassword(encoder.encode("abcd"));
			m1.setName("Member");
			m1.setRole(Role.ROLE_MEMBER);
			m1.setEnabled(true);
			memberRepo.save(m1);
		}
		{
			Member m1 = new Member();
			m1.setId("manager");
			m1.setPassword(encoder.encode("abcd"));
			m1.setName("Manager");
			m1.setRole(Role.ROLE_MANAGER);
			m1.setEnabled(true);
			memberRepo.save(m1);
		}
		{
			Member m1 = new Member();
			m1.setId("admin");
			m1.setPassword(encoder.encode("abcd"));
			m1.setName("Admin");
			m1.setRole(Role.ROLE_ADMIN);
			m1.setEnabled(true);
			memberRepo.save(m1);
		}		
	}

}
