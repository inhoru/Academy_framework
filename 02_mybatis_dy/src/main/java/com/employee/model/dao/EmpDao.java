package com.employee.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.employee.model.vo.Department;
import com.employee.model.vo.Employee;

public interface EmpDao {
	List<Employee> selectAllemp(SqlSession session, int cPage, int numPerpage);

	List<Employee> searchEmp(SqlSession session, Map<String, Object> param);

	int selectEmployeeCount(SqlSession session);

	List<Department> selectAllDept(SqlSession session);

}
