package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/h.do")
public class HelloStart extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int su1 = Integer.parseInt(request.getParameter("su1"));
		int su2 = Integer.parseInt(request.getParameter("su2"));
		
		
		// su1과 su2 사이의 합
		int sum=0;
		for(int i =su1; i <=su2; i++) {
			sum+= i;
		}
		
		PrintWriter out=response.getWriter();
		out.println("total= " + sum);	
		}

}
