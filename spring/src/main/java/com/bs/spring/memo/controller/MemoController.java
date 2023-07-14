package com.bs.spring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.model.service.MemoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {
	
	private MemoService service;
	
	@Autowired
	public MemoController(MemoService service) {
		this.service=service;
	}
	
	
	@RequestMapping("/selectMemoAll.do")
	public String selectMemoAll(Model m) {
		
		List<Memo> list=service.selectMemoAll();
		log.debug("{}",list);
		m.addAttribute("memos",list);
		
		return "memo/memoList";
	}
	
	
	@RequestMapping(value="/insertMemo.do", method=RequestMethod.POST)
	//@PostMapping("/insertMemo.do")
	public String inertMemo(Memo m) {
		int result=service.insertMemo(m);
		if(result==0) {
			
			return "common/msg";
		}
		return "redirect:/memo/selectMemoAll.do";
	}
	
}
