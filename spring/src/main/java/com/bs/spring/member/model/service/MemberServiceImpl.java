package com.bs.spring.member.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao dao;
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		return dao.insertMember(session, m);
	}
	
	@Override
	public Member selectMemberById(Map param) {
		return dao.selectMemberById(session,param);
	}

	@Override
	public List<Member> selectAll() {
		return dao.selectAll(session);
	}
}
