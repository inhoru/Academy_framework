package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Board;
@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardDao dao;
	private SqlSession session;
	
	public BoardServiceImpl(BoardDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}

//	@Override
//	public List<Board> selectBoard(int cPage, int numPerpage) {
//			return dao.selectBoard(session,cPage,numPerpage);
//	}
	@Override
	public List<Board> selectBoard(Map<String,Object> param) {
			return dao.selectBoard(session,param);
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	
	}

	@Override
	public Board selectBoardNo(int no) {
		return dao.selectBoardNo(session,no);
	}

	@Override
	public int insertBoard(Board b) {
		return dao.insertBoard(session,b);
	}

	
}
