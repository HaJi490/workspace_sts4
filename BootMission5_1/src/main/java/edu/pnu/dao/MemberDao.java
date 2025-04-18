package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberDTO;

public class MemberDao {
	Connection con = null;
	
	public MemberDao() {
		//db연결
		try {
			String url = "jdbc:mysql://localhost:3306/bootmission";
			con = DriverManager.getConnection(url, "musthave", "tiger");
			
		} catch (SQLException e) {
			System.out.println("sql: 생성자 오류");
			e.printStackTrace();
		}
	}
	
	public List<MemberDTO> getMembers(){
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		Statement st = null;
		ResultSet rs = null;
		try {
			
			
			
		}catch(Exception e) {
			System.out.println("sql: getList 오류");
			e.printStackTrace();
		}
		
			
	}
}
