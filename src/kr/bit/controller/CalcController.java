package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MyCalc;


@WebServlet("/calc.do")
public class CalcController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트에서 넘어오는 폼 파라메타 받기 name="su1","su2" << request에 받아져있음
				int su1 = Integer.parseInt(request.getParameter("su1"));
				int su2 = Integer.parseInt(request.getParameter("su2"));
				
				
				// su1과 su2 사이의 합
				// 비즈니스로직 -> model로 분리
				/*
				 * int sum=0; for(int i =su1; i <=su2; i++) { sum+= i; }
				 */
				MyCalc myCalc = new MyCalc();
				int sum = myCalc.hap(su1, su2);
				
				
				PrintWriter out=response.getWriter();
				out.println("<html>");
				out.println("<body>");
				out.println("<table border='1'>");
				out.println("<tr>");
				out.println("<td>TOTAL</td>");
				out.println("<td>");
				out.println(sum);
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
				}

}
