package com.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseFriends {
	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;
	StringBuffer stringBuffer = new StringBuffer();
	
	// �������ݿ�����
	public String FriendsDB(String name) {
		sql = "select * from " + name + "friends";// SQL���
		db1 = new DBHelper("myclient");// ����DBHelper����
		try {
			ret = db1.st.executeQuery(sql);// ִ����䣬�õ������
			while (ret.next()) {
				stringBuffer.append(ret.getString(1)+"/");
			}
			ret.close();
			db1.close();// �ر�����
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
}
