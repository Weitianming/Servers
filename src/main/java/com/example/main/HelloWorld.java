package com.example.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class HelloWorld extends HttpServlet {
	JSONObject jsonObject;

//	public HelloWorld() {
//	}
	
	// Get请求
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get： " + request.getRequestURI().toString());
		
		response.getWriter().println("Hello, World!袁艺");
//		response.getWriter().println("socket    " + socket + "     asd");
	
//		 JSONObject jsonObject = new JSONObject();
//		 Map map = request.getParameterMap();
//		 Iterator<?> it = map.keySet().iterator();
//		 while (it.hasNext()) {
//		 String key = (String) it.next();
//		 System.out.println("key " + key);
//		 String[] value = (String[]) map.get(key);
//		
//		 try {
//		 jsonObject.accumulate(key, value[0]);
//		 } catch (JSONException e) {
//		 e.printStackTrace();
//		 }
//		 
//		 }

//		if (request.getRequestURI().toString()
//				.equals("/MyServersText/HelloWorld")) {
//
//			jsonArray = new DataBasePicture().PictureDB();
//
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("text/json;UTF-8");
//			response.getWriter().println(jsonArray.toString());
//
//		}
	}

	// Post请求
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 获取请求
		
		System.out.println("测试  "+req.getInputStream());
		
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(req.getInputStream(), "UTF-8"));
		String line = "";
		StringBuilder builder = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line);
		}
		String reqBody = builder.toString();

		// 反馈请求
		jsonObject = new JsonParam().Param(reqBody, resp);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json;UTF-8");
		PrintWriter out = resp.getWriter();
		System.out.println("返回的信息" + jsonObject.toString());
		out.write(jsonObject.toString());

	}

	@Override
	public void init() throws ServletException {
		new ChatServer().start();
		System.out.println("init");
	}

	// @Override
	// public void destroy()
	// {
	// System.out.println("Servlet " + this.getServletName() + " has stopped.");
	// }
}
