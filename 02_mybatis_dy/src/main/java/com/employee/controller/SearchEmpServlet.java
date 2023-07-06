package com.employee.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.service.EmpService;
import com.employee.model.service.EmpServiceImpl;
import com.employee.model.vo.Employee;

/**
 * Servlet implementation class SearchEmpServlet
 */
@WebServlet("/searchEmp.do")
public class SearchEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpService service; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type=request.getParameter("type");
		String keyword=request.getParameter("keyword");
		String gender =request.getParameter("gender");
		int salary =Integer.parseInt(request.getParameter("salary").equals("")?"0":request.getParameter("salary"));
		String salFlag=request.getParameter("salFlag");
		
		
		
		Map<String,Object> param=new HashMap<>();
		param.put("type", type);
		param.put("keyword", keyword);
		param.put("gender", gender);
		param.put("salary", salary);
		param.put("salFlag", salFlag);
		param.put("deptCodes", request.getParameterValues("deptCode"));
		
		
		/*
		 * System.out.println(Arrays.toString(request.getParameterValues("deptCode")));
		 */
		
		service=new EmpServiceImpl();
		List<Employee> employee=service.searchEmp(param);
		
		System.out.println(employee);
		
		
		request.setAttribute("employee", employee);
		request.getRequestDispatcher("/views/employee.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
