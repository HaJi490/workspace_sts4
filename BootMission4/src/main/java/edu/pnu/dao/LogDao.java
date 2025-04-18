package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;

public class LogDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public LogDao() {
		try {
			//musthave권한으로 url가져옴
			String url = "jdbc:mysql://localhost:3306/bootmission";
			con = DriverManager.getConnection(url, "musthave", "tiger");
			
		} catch (Exception e) {
			System.out.println("sqlLog: 생성자 오류");
			e.printStackTrace();
		}
	}
	
	// 로그 추가
	public void addLog(Map<String, Object> map) {
		try{
			String sql = "Insert Into dblog( method, sqlstring, success) values ( ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, (String)map.get("method")); //타입캐스팅--------------------------------------!
			ps.setString(2, (String)map.get("sqlString"));
			ps.setBoolean(3, (boolean)map.get("success"));
			ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("sqlLog: insert 오류");
			e.printStackTrace();
		}
	}
}