package edu.pnu.service;

import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

public class MemberService {
	private MemberDao dao;
	private LogDao logDao ;
	
	public MemberService() {
		dao = new MemberDao();
		logDao = new LogDao();
	}
	
	@SuppressWarnings("unchecked") //리턴타입 알고있으니까 신경쓰지 않겠다
	public List<MemberDTO> getMembers(){
		Map<String, Object> map = dao.getMembers();
		logDao.addLog(map);
		
		return (List<MemberDTO>)map.get("list");
	}
	
	public MemberDTO getMemberById(Integer id) {
		Map<String, Object> map = dao.getMemberById(id);
		logDao.addLog(map);
		
		return (MemberDTO) map.get("dto");
	}
	
	public int postMember(MemberDTO member) {
		Map<String, Object> map = dao.postMember(member);
		logDao.addLog(map);
		return (int)map.get("result");
	}

	public int putMember(MemberDTO member) {
		Map<String, Object> map = dao.putMember(member);
		logDao.addLog(map);
		
		return (int)map.get("result");
	}
	
	public int patchMember(MemberDTO member) {
		Map<String, Object> map = dao.patchMember(member);
		logDao.addLog(map);
		
		return (int)map.get("result");
	}
	
	public int deleteMember(Integer id) {
		Map<String, Object> map = dao.deleteMember(id);
		logDao.addLog(map);
		
		return (int)map.get("result");
	}
	
	
	

}
