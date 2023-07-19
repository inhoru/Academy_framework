package com.bs.spring.member.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.member.model.dto.Member;

public interface MemberService {
	int insertMember(Member m);
	Member selectMemberById(Map param);
	List<Member> selectAll();
}
