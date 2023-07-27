package com.bs.spring.jpa.model.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.entity.JpaMember;
@Repository
public class JpaDaoImpl implements JpaDao {

	@Override
	public void basictest(EntityManager em) {
		//em메소드 이용하기
		//1. JpaMember클래스 영속성등록하기
		JpaMember m=JpaMember.builder().memberId("inhoru").memberPwd("2noru").age(25).height(180.2).build();//->비영속
		//영속화 처리하기
		em.persist(m);//매개변수로 전달된 객체가 영속성 컨텍스트에 저장이 되고 영속성 컨테스트에 새로 저장되면 insert sql문을 자동으로 생성
		JpaMember m2=JpaMember.builder()
				.memberId("김현빵")
				.memberPwd("빵빵빵으악으악")
				.age(26)
				.height(165.2)
				.build();
		em.persist(m2);
		System.out.println(m);
		
		//저장된 객체 불러우기 -> select문
		JpaMember selectM=em.find(JpaMember.class, 1L);
		System.out.println(selectM);
	
		
	}
	
}
