package com.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.service.EmpService;
import com.employee.model.service.EmpServiceImpl;
import com.employee.model.vo.Employee;

/**
 * Servlet implementation class selectEmployeeAllServlet
 */
@WebServlet("/employee/employeeAll.do")
public class selectEmployeeAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpService service; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectEmployeeAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
int cPage, numPerpage;
		
        try {
            cPage = Integer.parseInt(request.getParameter("cPage"));
        } catch (NumberFormatException e) {
            cPage = 1;
        }
        try {
            numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
        } catch (NumberFormatException e) {
            numPerpage = 5;
        }
        service=new EmpServiceImpl();
		int totalData=service.selectEmployeeCount();
		int totalPage=(int) Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="<ul class='pagination justify-content pagination'>";
		
		if(pageNo==1) {
			pageBar+="""
					<li class='page-item disabled'>
						<a class='page-link' href='#'>이전</a>
					</li>
					""";
		}else {
			pageBar+="<li class='page-item'>";
			pageBar+="<a class='page-link' href='"+request.getRequestURI() +"?cPage="+(pageNo-1)+"'>이전</a>";
			pageBar+="</li>";
			
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<li class='page-item disabled'>";
				pageBar+="<a class='page-link' href='#'>"+pageNo+"</a>";
				pageBar+="</li>";
			}else {
				pageBar+="<li class='page-item'>";
				pageBar+="<a class='page-link' href='"+request.getRequestURI()
						+"?cPage="+(pageNo)+"'>"+pageNo+"</a>";
				pageBar+="</li>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="""
					<li class='page-item disabled'>
						<a class='page-link' href='#'>다음</a>
					</li>
					""";
		}else {
			pageBar+="<li class='page-item'>";
			pageBar+="<a class='page-link' href='"+request.getRequestURI()
					+"?cPage="+(pageNo)+"'>다음</a>";
			pageBar+="</li>";
		}
		pageBar+="</ul>";
		
		List<Employee> result=service.selectAllemp(cPage,numPerpage);
		System.out.println(result);
		request.setAttribute("employee", result);
		request.setAttribute("pageBar", pageBar);
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
