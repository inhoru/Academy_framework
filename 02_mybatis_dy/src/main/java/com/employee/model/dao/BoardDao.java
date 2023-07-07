package com.employee.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.employee.model.vo.Board;

public interface BoardDao {
	Board selectBoardAll(SqlSession session,int no);
}
