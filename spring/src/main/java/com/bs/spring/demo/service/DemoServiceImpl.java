package com.bs.spring.demo.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.dao.DemoDao;
import com.bs.spring.demo.model.dto.Demo;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	//daoImpl를 찾는다.
	private DemoDao dao;
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public int insertDemo(Demo demo) {
		// TODO Auto-generated method stub
		return dao.insertDemo(session,demo);
	}

	@Override
	public List<Demo> selectDemoAll() {
		// TODO Auto-generated method stub
		return dao.selectDemoAll(session);
	}

	@Override
	public int updateDemo(Demo demo) {
		// TODO Auto-generated method stub
		return dao.updateDemo(session,demo);
	}

	@Override
	public Demo selectDemoNo(int no) {
		return dao.selectDemoNo(session,no);
	}

}
