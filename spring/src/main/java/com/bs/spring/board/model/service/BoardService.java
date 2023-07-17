package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.dto.Board;

public interface BoardService {
//	List<Board> selectBoard(int cPage,int numPerpage);
	List<Board> selectBoard(Map<String,Object> param);
	int selectBoardCount();
	Board selectBoardNo(int no);
	int insertBoard(Board b);
}
