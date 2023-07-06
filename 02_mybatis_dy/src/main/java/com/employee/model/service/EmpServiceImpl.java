package com.employee.model.service;

import static com.employee.common.SessionTemplate.getSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.employee.model.dao.EmpDao;
import com.employee.model.dao.EmpDaoImpl;
import com.employee.model.vo.Employee;

public class EmpServiceImpl implements EmpService {
	private EmpDao dao=new EmpDaoImpl();

	@Override
	public List<Employee> selectAllemp(int cPage,int numPerpage) {
		SqlSession session=getSession();
		List<Employee> result=dao.selectAllemp(session,cPage,numPerpage);
		session.close();
		return result;
		
	}
	

	@Override
	public List<Employee> searchEmp(Map<String, Object> param) {
	SqlSession session=getSession();
	List<Employee> list=dao.searchEmp(session, param);
	session.close();
	return list;
	}


	@Override
	public int selectEmployeeCount() {
		SqlSession session=getSession();
		int result=dao.selectEmployeeCount(session);
		session.close();
		return result;
	}

	
}

	
