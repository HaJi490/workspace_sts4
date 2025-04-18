package edu.pnu.service;

import java.util.ArrayList;
import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

public class MemberService {
	MemberDao dao = new MemberDao();
	List<MemberDTO> list = new ArrayList<MemberDTO>();
	
	//생성자
	public MemberService() {
		list = dao.getMembers();
	}
	
	//전체멤버 리턴 (-> Controller)
	public List<MemberDTO> getMember(){
		return list;
	}
	
	//해당 id멤버 리턴(-> Controller)
	public MemberDTO getMemberById(Integer id) {
		return dao.getMemberById(id);
	}
	
	//POST
	public MemberDTO insertMember(MemberDTO member) {
		int result = dao.insertMember(member);
		if(result > 0) {
			return member;
		}
		return null;
	}
	
	//PUT
	public MemberDTO putMember(Integer id,MemberDTO member) {
		int result = dao.putMember(id, member);
		
		if(result > 0) {
			return member;
		}
		return null;
	}
	
	//PATCH
	public int patchMember(Integer id, MemberDTO member) {
		return dao.patchMember(id, member);
	}
	
	
	//DELETE
	public int deleteMember(Integer id) {
		return dao.deleteMember(id);
	}
	
	
	
	
}
