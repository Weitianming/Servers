package com.example.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ConnectServer extends Thread {
	private BufferedReader reader;
	
	public ConnectServer() {
	}
	
	public ConnectServer(Socket socket) {
		String string = "";
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			string = reader.readLine();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(string);
		
	}
	
	@Override
	public void run() {
		
		
		
		
		
		
		
		
		
		
		super.run();
	}
	

}
