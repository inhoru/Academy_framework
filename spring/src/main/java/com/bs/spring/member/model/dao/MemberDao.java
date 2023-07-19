package com.bs.spring.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.member.model.dto.Member;

public interface MemberDao {
	int insertMember(SqlSession session,Member m);
	Member selectMemberById(SqlSession session,Map param);
	List<Member> selectAll(SqlSession session);
	
}
