package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	public static final String url = "jdbc:mysql://localhost:3306/";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "root";

	public static Connection conn = null;
	public static PreparedStatement pst = null;
	public static Statement st = null;
	
	public DBHelper(String string) {
		try {
			Class.forName(name).newInstance();// ָ����������
			conn = DriverManager.getConnection(url + string, user, password);// ��ȡ����
//			pst = conn.prepareStatement(sql);// ׼��ִ�����
			st = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			this.conn.close();
//			this.pst.close();
			this.st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
