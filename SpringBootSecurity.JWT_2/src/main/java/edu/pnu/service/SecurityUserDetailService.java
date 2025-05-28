package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class SecurityUserDetailService implements UserDetailsService {//상속받은 UserDetailsService라는 타입의 객체를 찾기 때문에---------------------------------------!
	@Autowired
	private MemberRepository memRepo;
	
	@Override // Object에는 해당 메서드가 없기때문에 상속을 안했을때 Override 오류가 뜸 ----------------------------------------------!
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memRepo.findById(username)
								.orElseThrow(()->new UsernameNotFoundException("Not Found!"));
		return new User(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}
}