package com.bs.spring.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertMember(SqlSessionTemplate session, Member mem) {
		return session.insert("member.insertMember", mem);
	}

	@Override
	public Member selectMemberById(SqlSessionTemplate session, Map member) {
		return session.selectOne("member.selectMemberById", member);
	}

}
