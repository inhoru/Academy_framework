package com.employee.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.employee.model.vo.Department;
import com.employee.model.vo.Employee;

public class EmpDaoImpl implements EmpDao {

	@Override
	public List<Employee> selectAllemp(SqlSession session,int cPage,int numPerpage) {
		
		return session.selectList("employee.selectAllemp",null,new RowBounds((cPage-1)*numPerpage,numPerpage));
	}

	@Override
	public List<Employee> searchEmp(SqlSession session, Map<String, Object> param) {
		return session.selectList("employee.searchEmp",param);
	}

	@Override
	public int selectEmployeeCount(SqlSession session) {
		return session.selectOne("employee.selectEmployeeCount");
	}
	@Override
	public List<Department> selectAllDept(SqlSession session){
		return session.selectList("employee.selectAllDept");
	}
	
	
	
}
