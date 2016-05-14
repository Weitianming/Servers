package com.example.main;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.database.DataBaseDemo;
import com.example.database.DataBaseFriends;
import com.example.http.ConnectManager;

public class JsonParam {
	String string;
	JSONObject jsonObject;

	public JSONObject Param(String string, HttpServletResponse response) {
		this.string = string;
		JSONObject jsonObject = JsonAnalytical();

		return jsonObject;
	}

	// 解析json信息
	public JSONObject JsonAnalytical() {
		try {
			jsonObject = new JSONObject(string);

			if (jsonObject.getString("object").equals("login")) { // 登录请求
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().LoginResponse(new DataBaseDemo().LoginDB(
						object.getString("username"),
						object.getString("password"), object.getString("deviceId")));

			} else if (jsonObject.getString("object").equals("register")) { // 注册请求
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().RegisterResponse(new DataBaseDemo()
						.RegistrDB(object.getString("username"),
								object.getString("password")));

			} else if (jsonObject.getString("object").equals("getAllUsersName")) { // 获取用户
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().getAllUsersName(new DataBaseFriends()
						.FriendsDB(object.getString("sender")));

			} else if (jsonObject.getString("object").equals("notice")) { // 消息
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().NoticeResponse(new ConnectManager()
						.Notice(object.getString("sender"),
								object.getString("receiver"),
								object.getString("content")));

			} else if (jsonObject.getString("object").equals("logout")) { // 注销
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().LogoutResponse(new ConnectManager()
						.logout(object.getString("sender")));

			} else if (jsonObject.getString("object").equals("onLogin")) { // 上线提醒
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().onLoginResponse(new ConnectManager()
						.onLogin(object.getString("sender")));

			} else if (jsonObject.getString("object").equals("onLogout")) { // 下线提醒
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().onLogoutResponse(new ConnectManager()
						.onLogou(object.getString("sender")));

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

}
