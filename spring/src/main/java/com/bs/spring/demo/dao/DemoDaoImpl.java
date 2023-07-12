package com.bs.spring.demo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.dto.Demo;

@Repository
public class DemoDaoImpl implements DemoDao {

	@Override
	public int insertDemo(SqlSessionTemplate session, Demo m) {
		
		return session.insert("dev.insertDemo",m);
	}

	@Override
	public List<Demo> selectDemoAll(SqlSessionTemplate session) {
		return session.selectList("dev.selectDemoAll");
	}

	@Override
	public int updateDemo(SqlSessionTemplate session, Demo m) {
		// TODO Auto-generated method stub
		return session.update("dev.updateDemo",m);
	}

	@Override
	public Demo selectDemoNo(SqlSessionTemplate session, int no) {
		
		return session.selectOne("dev.selectDemoNo",no);
	}

}
