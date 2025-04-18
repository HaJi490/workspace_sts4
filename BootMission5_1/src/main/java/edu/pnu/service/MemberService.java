package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;

@Service
public class MemberService {
	
	@Autowired
	public MemberService(MemberDao dao) {
		
	}
	
	//Get
	public List<MembmerDTO> getMembers(){
		return dao.getMembers();
	}
}
