package com.bs.spring.memo.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	
	private MemoDao dao;
	
	private SqlSession session;
	
	public MemoServiceImpl(MemoDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public List<Memo> selectMemoAll() {
		// TODO Auto-generated method stub
		return dao.selectMemoAll(session);
	}

	@Override
	public int insertMemo(Memo m) {
		// TODO Auto-generated method stub
		return dao.insertMemo(session, m);
	}

}
