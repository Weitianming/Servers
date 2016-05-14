package com.example.http;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.database.DataBaseDemo;
import com.example.database.UpdateState;
import com.example.push.PushAndroid;

public class ConnectManager {
	private PrintWriter writer;
	private JSONObject jsonObject = new JSONObject();
	private Socket socket;
	
	public ConnectManager() {
	}

	// 发送消息
	public boolean send(String receiver, JSONObject jsonObject)
			throws IOException {
		if (HttpURL.map.get(receiver) != null) {
			socket = HttpURL.map.get(receiver);
			writer = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream(), "UTF-8"), true);
			writer.println(jsonObject.toString());
			writer.flush();
			return true;
		} else {
			return false;
		}
	}

	// 转发客户端之间的消息
	public String Notice(String sender, String receiver, String content) {
		
		String a = new DataBaseDemo().QueryDeviceId(receiver);
		
		
		try {
			new PushAndroid().PushNoticeToAndroid(sender, a, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
//		JSONObject object = new JSONObject();
//		try {
//			object.put("sender", sender);
//			object.put("content", content);
//			jsonObject.put("object", "notice");
//			jsonObject.put("message", object);
//
//			if (send(receiver, jsonObject)) {
//				return "Ok";
//			} else {
//				return "No";
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		return "No";
	}

	// 注销
	public String logout(String sender) {
		onLogou(sender); // 注销时处于下线状态
		if (HttpURL.map.get(sender) != null) {
			HttpURL.map.remove(sender);
			return "Ok";
		}
		return "No";
	}

	// 上线通知
	public String onLogin(String sender) {
		String string = new UpdateState().FriendsState(sender);
		String[] NAMES = string.split("/");
		if (NAMES[0].equals("")) {
			NAMES = new String[0];
		}

		try {
			for (int i = 0; i < NAMES.length; i++) {
				jsonObject.put("object", "onLogin");
				jsonObject.put("sender", sender);
				send(NAMES[i], jsonObject);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "OK";
	}

	// 下线通知
	public String onLogou(String sender) {
		// 修改该用户在其他好友中的状态

		return null;
	}

}
