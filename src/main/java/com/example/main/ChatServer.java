package com.example.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.http.HttpURL;

public class ChatServer extends Thread {
	private ServerSocket chatServerSocket;
	private JSONObject jsonObject;

	@Override
	public void run() {
		try {
			chatServerSocket = new ServerSocket(5123);
			while (true) {
				System.out.println("等待连接！！");
				Socket socket = chatServerSocket.accept();
				System.out.println("连接成功:"
						+ socket.getInetAddress().getHostAddress());
				new ChatSocket(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 记录已登录的客户端
	class ChatSocket extends Thread {
		private Socket socket;
		private BufferedReader reader;
		private String content = null;
		private String name = null;
		PrintWriter writer;

		ChatSocket(Socket socket) {
			this.socket = socket;
		}

		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			synchronized (this) {
				reader = null;
				content = null;
				name = null;
			}

			// 取客户端内容
			try {
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream(), "UTF-8"));
				writer = new PrintWriter(socket.getOutputStream());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 取出客户端的数据并保持Socket
			try {
				while (true) {
					if ((content = reader.readLine()) != null) {
						jsonObject = new JSONObject(content);
						if ((name = jsonObject.getString("sender")) != null) {
							if (HttpURL.map.get(name) != null) {
								HttpURL.map.remove(name);
								HttpURL.map.put(name, socket);
							} else {
								HttpURL.map.put(name, socket);
							}
						}
						System.out.println("连接数："+HttpURL.map.size());
						stop();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}

}
