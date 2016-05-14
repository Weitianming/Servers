package com.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataBasePicture {
	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;
	JSONObject jsonObject;
	JSONArray jsonArray;
	
	// 访问数据库内容
	public JSONArray PictureDB() {
		sql = "select * from picture";// SQL语句
		db1 = new DBHelper("mypicture");// 创建DBHelper对象
		jsonArray = new JSONArray();
		try {
			ret = db1.st.executeQuery(sql);// 执行语句，得到结果集
			int i = 0;
			while (ret.next()) {
				jsonObject = new JSONObject();
				jsonObject.put("Title", ret.getString(2));
				jsonObject.put("Picture", ret.getString(3));
				jsonObject.put("Content", ret.getString(4));
				jsonArray.put(i++, jsonObject);
			}
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}

}
