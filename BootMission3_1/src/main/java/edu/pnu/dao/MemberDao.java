package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberDTO;

public class MemberDao {
	//메서드 만들어서 메인에서 호출
	Connection con = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	List<MemberDTO> list = new ArrayList<MemberDTO>();
	
	//생성자에서 리스트를 가져오면 executeUpdate가 적용안되서 권한 가져오는것만
	//바뀐멤버를 리턴안해도됨
	
	//생성자
	public MemberDao() {
		// 새로 실행되어야 변경된값 확인가능------------------------------------?
		try {
			//musthave권한으로 url가져옴
			String url = "jdbc:mysql://localhost:3306/bootmission";
			con = DriverManager.getConnection(url, "musthave", "tiger");
			
		} catch (Exception e) {
			System.out.println("sql: 생성자 오류");
			e.printStackTrace();
		}
	}
	
	//전체멤버 리턴(-> Service)
	public List<MemberDTO> getMembers(){
		try {
		//쿼리문 준비
			String sql = "Select * From member";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			//반환된 목록 List에 추가
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setPass(rs.getString("pass"));
				member.setRegidate(rs.getDate("regidate"));
				list.add(member);
			}
		} catch (SQLException e) {
				System.out.println("sql: MemberList 오류");
				e.printStackTrace();
		}
		return list;
	}
	
	//해당 id멤버 리턴(-> Service)
	public MemberDTO getMemberById(Integer id) {
		for(MemberDTO m : list) {
			if(m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	//POST_insert, 결과(int -> Service)
	public int insertMember(MemberDTO member) {
		int result = -1;
		try {
			//쿼리문 준비
			String sql = "Insert Into member(pass, name) values (? ,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getPass());
			ps.setString(2, member.getName());
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("sql: insert 실패");
			e.printStackTrace();
		}
		return result;
	}
	
	//PUT_update, 결과(int -> Service)
	public int putMember(Integer id, MemberDTO member) {
		int result = -1;
		try {
			//쿼리문 준비
			String sql = "Update member Set pass=?, name=? where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getPass());
			ps.setString(2, member.getName());
			ps.setInt(3, id);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("sql: put 실패");
			e.printStackTrace();
		}
		return result;
	}
	
	//PATCH_부분update
	public int patchMember(Integer id, MemberDTO member){
		int result = -1;
		try {
			//쿼리문 준비(prepare쓸려면 ?개수 계산해서 넣어줘야함)
			String sql = "Update member Set ";
				if(member.getPass() != null) {	
					sql += " pass='"+ member.getPass()+ "'";
				}
				if(member.getName() != null) {
					if( sql.contains("pass"))
						sql += ", ";
					sql += " name ='" + member.getName()+"'";
				}
			sql += " where id = " + id;
			st = con.createStatement();
			result = st.executeUpdate(sql); 
		} catch (SQLException e) {
			System.out.println("sql: fetch 실패");
			e.printStackTrace();
		}
		return result;
	}
		
	//DELETE
	public int deleteMember(Integer id) {
		int result = -1;
		//쿼리문 준비
		try {
		String sql = "Delete From member Where id=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql: delete 실패");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
