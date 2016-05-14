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

	// ����json��Ϣ
	public JSONObject JsonAnalytical() {
		try {
			jsonObject = new JSONObject(string);

			if (jsonObject.getString("object").equals("login")) { // ��¼����
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().LoginResponse(new DataBaseDemo().LoginDB(
						object.getString("username"),
						object.getString("password"), object.getString("deviceId")));

			} else if (jsonObject.getString("object").equals("register")) { // ע������
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().RegisterResponse(new DataBaseDemo()
						.RegistrDB(object.getString("username"),
								object.getString("password")));

			} else if (jsonObject.getString("object").equals("getAllUsersName")) { // ��ȡ�û�
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().getAllUsersName(new DataBaseFriends()
						.FriendsDB(object.getString("sender")));

			} else if (jsonObject.getString("object").equals("notice")) { // ��Ϣ
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().NoticeResponse(new ConnectManager()
						.Notice(object.getString("sender"),
								object.getString("receiver"),
								object.getString("content")));

			} else if (jsonObject.getString("object").equals("logout")) { // ע��
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().LogoutResponse(new ConnectManager()
						.logout(object.getString("sender")));

			} else if (jsonObject.getString("object").equals("onLogin")) { // ��������
				JSONObject object = new JSONObject(
						jsonObject.getString("message"));
				return new Connect().onLoginResponse(new ConnectManager()
						.onLogin(object.getString("sender")));

			} else if (jsonObject.getString("object").equals("onLogout")) { // ��������
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
