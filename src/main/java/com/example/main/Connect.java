package com.example.main;

import org.json.JSONException;
import org.json.JSONObject;

public class Connect {
	JSONObject jsonObject = new JSONObject();

	// 反馈登录信息
	public JSONObject LoginResponse(String Result) {
		try {
			jsonObject.put("LoginResponse", Result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	// 反馈注册信息
	public JSONObject RegisterResponse(String Result) {
		try {
			jsonObject.put("RegisterResponse", Result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	// 获取账号下的所有好友
	public JSONObject getAllUsersName(String Result) {
		JSONObject Object = new JSONObject();
		try {
			Object.put("content", Result);
			jsonObject.put("object", "getAllUsersName");
			jsonObject.put("message", Object);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	// 向好友发生消息
	public JSONObject NoticeResponse(String Result) {
		try {
			jsonObject.put("NoticeResponse", Result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	// 注销
	public JSONObject LogoutResponse(String Result) {
		try {
			jsonObject.put("LogoutResponse", Result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	// 上线通知
	public JSONObject onLoginResponse(String Result) {
		try {
			jsonObject.put("onLogin", Result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	// 下线通知
	public JSONObject onLogoutResponse(String Result) {
		try {
			jsonObject.put("onLogout", Result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}
