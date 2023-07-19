package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	private BoardDao dao;
	private SqlSession session;

	public BoardServiceImpl(BoardDao dao, SqlSession session) {
		this.dao = dao;
		this.session = session;
	}

//	@Override
//	public List<Board> selectBoard(int cPage, int numPerpage) {
//			return dao.selectBoard(session,cPage,numPerpage);
//	}
	@Override
	public List<Board> selectBoard(Map<String, Object> param) {
		return dao.selectBoard(session, param);
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);

	}

	@Override
	public Board selectBoardNo(int no) {
		return dao.selectBoardNo(session, no);
	}

	@Override
	//@Transactional
	public int insertBoard(Board b){
		log.info("실행 전 {}", b.getBoardNo());
	      int result = dao.insertBoard(session, b); // boardNo는 지금 생성됨 -> Attachment에 boardNo FK로 사용중이라 필요함
	      log.info("실행 후 {}", b.getBoardNo());
	      if(result > 0) {
	         if(b.getFile().size() > 0) {
	            for(Attachment a : b.getFile()) {
	               a.setBoardNo(b.getBoardNo());
	               result += dao.insertAttachment(session, a);
//	               result = dao.insertAttachment(session, a);
//	               if(result != 1) throw new RuntimeException("첨부파일 내용이 이상해요~");
	            }
	         }
	      }   
	      // rollback처리를 원한다면..? RuntimeException 발생시키면 됨
	      // if(result>0) throw new RuntimeException("내가 그냥 싫어!");
	      if(result != b.getFile().size() + 1) throw new RuntimeException("첨부파일 내용이 이상해요~");
	      
//	      if(result !=0) throw new RuntimeException("첨부파일 내용이 이상해요~");
	      // result != b.getFile().size() + 1 -> 첨부파일 insert문 + 보드 등록 insert문이기 때문에 +1
	      
	      return result;
	}

}
