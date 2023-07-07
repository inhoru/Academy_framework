package com.employee.model.service;

import static com.employee.common.SessionTemplate.getWebSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.employee.model.dao.BoardDao;
import com.employee.model.dao.BoardDaoImpl;
import com.employee.model.vo.Board;

public class BoardServiceImpl implements BoardService {
	private BoardDao dao=new BoardDaoImpl();

	@Override
	public Board selectboardAll(int no) {
		SqlSession session=getWebSession();
		Board result=dao.selectBoardAll(session,no);
		session.close();
		return result;
	}

}
