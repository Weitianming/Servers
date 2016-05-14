package com.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

public class DataBaseDemo {
	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;
	
	// 登录账号
	public String LoginDB(String username, String password, String deviceId) {
		if (RegistrQuery(username, password)) { // 该账号存在
			if (PasswordDB(username, password)) {
				return addDeviceId(username, deviceId); // 更新设备ID
				// 没有更新
				// 测试
			}
			return "NOPassword";
			// return PasswordDB(username, password, response); // 判断密码是否正确
		}
		return "NO";
	}

	// 注册账号
	public String RegistrDB(String username, String password) {
		if (!RegistrQuery(username, password)) { // 该账号未注册
			return Registr(username, password);
		}
		return "NO";
	}

	// 查询账号是否注册
	private boolean RegistrQuery(String username, String password) {
		sql = "select * from account";// SQL语句
		db1 = new DBHelper("myclient");// 创建DBHelper对象

		try {
			ret = db1.st.executeQuery(sql);// 执行语句，得到结果集
			while (ret.next()) {
				if (ret.getString(1).equals(username)) {
					return true;
				}
			}
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 查询密码是否正确
	private boolean PasswordDB(String username, String password) {
		sql = "select * from account where phone = '" + username + "'";// SQL语句
		db1 = new DBHelper("myclient");// 创建DBHelper对象

		try {
			ret = db1.st.executeQuery(sql);// 执行语句，得到结果集
			while (ret.next()) {
				System.out.println(ret.getString(2));
				if (ret.getString(2).equals(password)) {

					return true;
					// return "OK";
				}
			}
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		// return "NOPassword";
	}

	// 注册账号信息
	private String Registr(String username, String password) {
		db1 = new DBHelper("myclient");// 创建DBHelper对象

		try {
			if ((db1.st.executeUpdate("insert into account values('" + username
					+ "', '" + password + "')")) == 1) { // 注册成功

				db1.st.executeUpdate("create table "
						+ username
						+ "friends (name varchar(16) not null, age int null, state vachar(6) null)");
				return "OK";
			}
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "NO";
	}

	// 保存、刷新deviceId
	public String addDeviceId(String username, String deviceId) {
		db1 = new DBHelper("myclient");// 创建DBHelper对象

		try {
			if (db1.st.executeUpdate("update account set DeviceId = '"
					+ deviceId + "'  where  phone = '" + username + "'") == 1) {
				System.out.println("添加设备：" + deviceId);
				return "OK";
			}
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "NO";
	}
	
	// 查询DeviceId
	public String QueryDeviceId (String receiver) {
		db1 = new DBHelper("myclient");// 创建DBHelper对象
		ResultSet resultSet = null;
		try {
			resultSet = db1.st.executeQuery("select DeviceId from account where phone = '" + receiver +"'");
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			resultSet.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "No";
	}

}
