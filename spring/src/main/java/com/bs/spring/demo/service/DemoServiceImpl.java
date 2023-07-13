package com.bs.spring.demo.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.dao.DemoDao;
import com.bs.spring.demo.model.dto.Demo;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired // 인터페이스는 생성이 안되기 때문에 자동으로 구현체를 찾아줌
	private DemoDao dao;
	
	@Autowired // bean으로 만들었기 때문에 @Autowired로 가져올 수 있음
	private SqlSessionTemplate session; // 굳이 서비스? -> controller에 묶을 수도 있음
	
	@Override
	public int insertDemo(Demo demo) {
		return dao.insertDemo(session, demo);
	}

	@Override
	public List<Demo> selectDemoAll() {
		return dao.selectDemoAll(session);
	}
	
}
