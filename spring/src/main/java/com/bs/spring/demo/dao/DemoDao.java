package com.bs.spring.demo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.demo.model.dto.Demo;

public interface DemoDao {
	
	// SqlSession 해도 됨(SqlSessionTemplate가 SqlSession 상속받음)
	int insertDemo(SqlSessionTemplate session, Demo m);
	
	List<Demo> selectDemoAll(SqlSessionTemplate session);
}
