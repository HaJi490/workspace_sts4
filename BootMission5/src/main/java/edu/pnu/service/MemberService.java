package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

@Service
public class MemberService {
	
	@Autowired //컨테이너의 객체를 여기에 넣음, 참조변수임
	private MemberDao dao;
	@Autowired
	private LogDao logDao;
	
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
