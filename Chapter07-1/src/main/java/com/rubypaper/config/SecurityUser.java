package com.rubypaper.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.rubypaper.domain.Member;

public class SecurityUser extends User{
	private static final long serialVersionUID = 1L;

	public SecurityUser(Member member) {
		super(member.getId(), member.getPassword(), 
			AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}

}
