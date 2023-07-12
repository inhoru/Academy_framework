package com.bs.spring.demo.service;

import java.util.List;

import com.bs.spring.demo.model.dto.Demo;

public interface DemoService {
	
	int insertDemo(Demo demo);
	
	List<Demo> selectDemoAll();
	
	Demo selectDemoNo(int no);
	
	int updateDemo(Demo demo);
	
}
