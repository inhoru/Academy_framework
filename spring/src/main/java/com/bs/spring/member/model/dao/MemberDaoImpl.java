package com.bs.spring.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertMember(SqlSession session, Member m) {
		return session.insert("member.insertMember",m);
	}

	@Override
	public Member selectMemberById(SqlSession session, Map param) {
		return session.selectOne("member.selectById",param);
	}

	@Override
	public List<Member> selectAll(SqlSession session) {
		return session.selectList("member.selectAll");
	}
	

}
