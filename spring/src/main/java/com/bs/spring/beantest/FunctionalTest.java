package com.bs.spring.beantest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Data;

// POJO 클래스를 생성하고 선언부에서 bean으로 등록할 수 있다.
// @Component, @Controller, @Service, @Repository
// 어노테이션을 이용해서 spring bean으로 등록

// @Component : 기본 spring bean으로 등록할 때
// @Controller, @Service, @Repository : mvc패턴에 의해 역할이 지정된 클래스를 bean으로 등록할 때 사용

// @Data
@Component
public class FunctionalTest {
	
	private String name = "test";
	
	// @Autowired // 필드에 사용하는 경우는 많이 없음(-> 생성자를 이용한 DI)
	private Animal a;
	
	// @Component || @Controller 사용할 때는
	// 생성자를 이용한 DI
//	public FunctionalTest(@Qualifier("dog") Animal a) {
//		this.a = a;
//	}
	
	// setter를 이용한 DI
	@Autowired
	public void setA(@Qualifier("dog") Animal a) {
		this.a = a;
	}
	
	public Animal getA() {
		return this.a;
	}
	
}
