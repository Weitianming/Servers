package com.example.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	private ServerSocket chatServerSocket;

	@Override
	public void run() {
		try {
			chatServerSocket = new ServerSocket(5123);
			while (true) {
				System.out.println("等待连接！！");
				Socket socket = chatServerSocket.accept();
				System.out.println("连接成功:"
						+ socket.getInetAddress().getHostAddress());
				new ConnectServer(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		super.run();
	}

}
