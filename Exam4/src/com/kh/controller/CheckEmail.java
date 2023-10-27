package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.dao.MemberDAO1;

@WebServlet("/CheckEmail")
public class CheckEmail extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//제출하기전에 1 없애야해
		MemberDAO1 dao = new MemberDAO1();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter resp = response.getWriter();
		
		String email = request.getParameter("email");

		try {
		Boolean result = dao.isEmailExist(email);
		if(result) {
			resp.println("이미 사용중인 email ");
		}
		else { resp.println("사용 가능한 email");
		}
		}
		catch(Exception e) {
		e.printStackTrace();
		resp.println("조회하는 도중 오류가 발생했습니다.");
		}
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
