package com.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateState {
	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;
	StringBuffer stringBuffer = new StringBuffer();
	
	// 访问数据库内容
	public String FriendsState(String name) {
		sql = "select account.`phone` from " + name + "friends, account where "+name+"friends.`name` = account.`phone` AND state != '下线'";// SQL语句
		db1 = new DBHelper("myclient");// 创建DBHelper对象
		try {
			ret = db1.st.executeQuery(sql);// 执行语句，得到结果集
			while (ret.next()) {
				stringBuffer.append(ret.getString(1)+"/");
			}
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}

}
