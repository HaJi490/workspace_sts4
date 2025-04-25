package edu.pnu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepo;
	
	public List<Member> getMembers(){
		List<Member> list = memberRepo.findAll();
		return list;
	}
	
	public Member getMemberbyId(Integer id){
		Member m = memberRepo.findById(id).get();
		return m;
	}
	
	public Member postMember(Member member) {
		Member m = memberRepo.save(member);
		m.setRegidate(new Date());
		return m;
	}
	
	public Member putMember(Member member) {
		Member m = memberRepo.findById(member.getId()).get();
		m.setName(member.getName());
		m.setPass(member.getPass());
		m.setRegidate(new Date());
		return memberRepo.save(m);
	}
	
	public Member deleteMember(Integer id) {
		Member m = memberRepo.findById(id).get();
		memberRepo.deleteById(id); 
		return m;
	}
}
