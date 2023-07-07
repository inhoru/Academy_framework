package com.employee.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.employee.model.vo.Board;

public class BoardDaoImpl implements BoardDao {

	@Override
	public Board selectBoardAll(SqlSession session,int no) {
		return session.selectOne("member.selectBoardAll",no);
		
	}
	

}
