package com.mybatis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.model.service.StudentService;
import com.mybatis.model.vo.Student;

/**
 * Servlet implementation class UpdateStudentServlet
 */
@WebServlet("/updateStudent.do")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Student s=Student.builder()
				.studentName(request.getParameter("name"))
				.studentTel(request.getParameter("phone"))
				.studentEmail(request.getParameter("email"))
				.studentAddress(request.getParameter("address"))
				.studentNo(Integer.parseInt(request.getParameter("no")))
				.build();
//		int no=Integer.parseInt(request.getParameter("no"));
		int result=new StudentService().updateStudent(s);
		
		response.setContentType("text/html;charset=utf-8");
		
		response.getWriter().write(result>0?"성공":"실패");
		
		
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
