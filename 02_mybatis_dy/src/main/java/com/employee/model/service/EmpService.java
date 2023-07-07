package com.employee.model.service;

import java.util.List;
import java.util.Map;

import com.employee.model.vo.Department;
import com.employee.model.vo.Employee;

public interface EmpService {
	List<Employee> selectAllemp(int cPage,int numPerpage);
	
	List<Employee> searchEmp(Map<String,Object> param);
	
	int selectEmployeeCount();
	
	List<Department> selectAllDept();
}
