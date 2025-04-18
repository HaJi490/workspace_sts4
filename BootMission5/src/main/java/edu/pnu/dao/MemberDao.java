package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberDTO;

@Repository
public class MemberDao {
	private Connection con = null;
	
	//생성자
	public MemberDao() {
		try {
			String url = "jdbc:mysql://localhost:3306/bootmission";
			con = DriverManager.getConnection(url, "musthave", "tiger");
			
		} catch (SQLException e) {
			System.out.println("sql: 생성자 오류");
			e.printStackTrace();
		}
	}
	
	//Get Members
	public Map<String, Object> getMembers(){
		Statement st = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<String, Object>(); //로그용
		List<MemberDTO> list = new ArrayList<>(); 
		try {
			//쿼리문 준비
			String sql = "Select * from member";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				MemberDTO m = new MemberDTO();
				m.setId(rs.getInt("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setRegidate(rs.getDate("regidate"));
				
				list.add(m);
			}
			
			//log 
			map.put("sqlString", sql);
			map.put("list", list);
			map.put("method", "GET");
			map.put("success", true);
			
		} catch (SQLException e) {
			map.put("success", false);
			System.out.println("sql: MemberList출력 오류");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				System.out.println("sql: close 오류");
				e.printStackTrace();
			}
		}
		return map;
	}
	
	
	//Get MemberById
	public Map<String, Object> getMemberById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		MemberDTO m = new MemberDTO();
		Statement st = null;
		ResultSet rs = null;
		try {
			//쿼리문 생성
			String sql = "Select * From member where id="+id;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			if(rs.next()) {
				m.setId(rs.getInt("id")); 
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setRegidate(rs.getDate("regidate"));
				map.put("success", true);
			}else {
				map.put("success", false);
				
			}
			//log
			map.put("method", "GET");
			map.put("sqlString", sql);
			map.put("dto", m);
		} catch (SQLException e) {
			System.out.println("sql: getById 오류");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				System.out.println("sql: close 오류");
				e.printStackTrace();
			}
		}
		return map;
	}
	
	//Post
	public Map<String, Object> postMember(MemberDTO member) {
		Map<String, Object> map = new HashMap<String, Object>();
		PreparedStatement ps = null;
		int result = -1;
		try {
			String sql = "Insert Into member( pass, name) values (?, ?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getPass());
			ps.setString(2, member.getName());
			result = ps.executeUpdate();
			
			//log(Insert)
			map.put("method", "POST");
			map.put("sqlString", sql);
			map.put("result", result);
			if(result > 0) 
				map.put("success", true);
			else 
				map.put("success", false);
			
		}catch(Exception e) {
			System.out.println("sql: insert 오류");
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("sql: close 오류");
				e.printStackTrace();
			}
		}
		return map;
	}
	
	//Put
	public Map<String, Object> putMember(MemberDTO member) {
		Map<String, Object> map = new HashMap<String, Object>();
		PreparedStatement ps = null;
		int result = -1;
		try {
			String sql = "Update member Set pass=?, name=? where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getPass());
			ps.setString(2, member.getName());
			ps.setInt(3, member.getId());
			result = ps.executeUpdate();
			
			//log(Put)
			map.put("method", "PUT");
			map.put("sqlString", sql);
			map.put("result", result);
			if(result > 0) 
				map.put("success", true);
			else 
				map.put("success", false);
			
		}catch(Exception e) {
			System.out.println("sql: put 오류");
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("sql: close 오류");
				e.printStackTrace();
			}
		}
		return map;
	}
	
	//Patch
	public Map<String, Object> patchMember(MemberDTO member) {
		Map<String, Object> map = new HashMap<String, Object>();
		Statement st = null;
		int result= -1;
		try {
			String sql = "Update member Set ";
			if(member.getPass() != null) {
				sql += " pass= '" + member.getPass() +"'"; 
			}
			if(member.getName() != null) {
				if(sql.contains("pass"))
					sql += ", ";
				sql += " name= '" + member.getName() + "'";
			}
			sql += " where id =" + member.getId();
			st = con.createStatement();
			result = st.executeUpdate(sql);
			
			//log
			map.put("method", "PATCH");
			map.put("sqlString", sql);
			map.put("result", result);
			if(result > 0)
				map.put("success", true);
			else
				map.put("success", false);
		}catch(Exception e) {
			System.out.println("sql: patch 오류");
			e.printStackTrace();
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println("sql: close 오류");
				e.printStackTrace();
			}
		}
		return map;
	}
	
	//Delete
	public Map<String, Object> deleteMember(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Statement st = null;
		int result = -1;
		try {
			String sql = "Delete From member Where id= " + id;
			st = con.createStatement();
			result = st.executeUpdate(sql);
			
			//log
			map.put("method", "DELETE");
			map.put("sqlString", sql);
			map.put("result", result);
			if(result>0)
				map.put("success", true);
			else
				map.put("success", false);
		}catch(Exception e) {
			System.out.println("sql: delete 오류");
			e.printStackTrace();
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println("sql: close 오류");
				e.printStackTrace();
			}
		}
		return map;
	}
}
