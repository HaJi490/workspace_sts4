package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LogDao {
Connection con = null;
	
	public LogDao() {
		//db연결
		try {
			String url = "jdbc:mysql://localhost:3306/bootmission";
			con = DriverManager.getConnection(url, "musthave", "tiger");
			
		} catch (SQLException e) {
			System.out.println("sql: 생성자 오류");
			e.printStackTrace();
		}
	}
	
	public addLog(Map<String, Object> map) {
		String sql = "Insert into dblog("
		PreparedStatement ps = null;
		
	}
}
