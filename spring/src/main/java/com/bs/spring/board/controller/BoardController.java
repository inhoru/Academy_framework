package com.bs.spring.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private final BoardService service;

	public BoardController(BoardService service) {
		this.service = service;
	}
	
	@RequestMapping("boardList.do")
	public String boardList(@RequestParam(value="cPage",defaultValue="1") int cPage,@RequestParam(value="numPerpage",defaultValue="20") int numPerpage, Model m){
		List<Board> boards=service.selectBoard(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData=service.selectBoardCount();
		//paging
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData,"boardList.do"));
		
		m.addAttribute("totalData",totalData);
		m.addAttribute("board",boards);
		
		return "board/boardList";
	
}

//	@RequestMapping("boardList.do")
//	public String boardList(HttpServletRequest request) {
//		int cPage, numPerpage;
//
//		try {
//			cPage = Integer.parseInt(request.getParameter("cPage"));
//		} catch (NumberFormatException e) {
//			cPage = 1;
//		}
//		try {
//			numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
//		} catch (NumberFormatException e) {
//			numPerpage = 5;
//		}
//
//		int totalData = service.selectBoardCount();
//		int totalPage = (int) Math.ceil((double) totalData / numPerpage);
//		int pageBarSize = 5;
//		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
//		int pageEnd = pageNo + pageBarSize - 1;
//
//		String pageBar = "<ul class='pagination justify-content pagination'>";
//
//		if (pageNo == 1) {
//			pageBar += """
//					<li class='page-item disabled'>
//						<a class='page-link' href='#'>이전</a>
//					</li>
//					""";
//		} else {
//			pageBar += "<li class='page-item'>";
//			pageBar += "<a class='page-link' href='" + request.getRequestURI() + "?cPage=" + (pageNo - 1) + "'>이전</a>";
//			pageBar += "</li>";
//
//		}
//		while (!(pageNo > pageEnd || pageNo > totalPage)) {
//			if (cPage == pageNo) {
//				pageBar += "<li class='page-item disabled'>";
//				pageBar += "<a class='page-link' href='#'>" + pageNo + "</a>";
//				pageBar += "</li>";
//			} else {
//				pageBar += "<li class='page-item'>";
//				pageBar += "<a class='page-link' href='" + request.getRequestURI() + "?cPage=" + (pageNo) + "'>"
//						+ pageNo + "</a>";
//				pageBar += "</li>";
//			}
//			pageNo++;
//		}
//
//		if (pageNo > totalPage) {
//			pageBar += """
//					<li class='page-item disabled'>
//						<a class='page-link' href='#'>다음</a>
//					</li>
//					""";
//		} else {
//			pageBar += "<li class='page-item'>";
//			pageBar += "<a class='page-link' href='" + request.getRequestURI() + "?cPage=" + (pageNo) + "'>다음</a>";
//			pageBar += "</li>";
//		}
//		pageBar += "</ul>";
//		List<Board> list = service.selectBoard(cPage,numPerpage);
//		request.setAttribute("board", list);
//		request.setAttribute("pageBar", pageBar);
//		request.setAttribute("totalContents", totalData);
//		return "board/boardList";
//	}
	@RequestMapping("boardInfo.do")
	public String boardInfo(int no,Model m) {
		Board board=service.selectBoardNo(no);
		m.addAttribute("board", board);
		return "board/boardInfo";
	}
	@RequestMapping("boardWriteMove.do")
	public String boardWriteMove(Model m) {
		
		
		return "board/boardWrite";
	}
	@GetMapping("boardWrite.do")
	public String boardWrite(Board b,Model m) {
		int result=service.insertBoard(b);
		String msg,loc;
		if(result>0) {
			msg="글작성 성공";
			loc="/board/boardList.do";
		}else {
			msg="글작성 실패";
			loc="/board/boardList.do";
		}
		m.addAttribute("msg",msg);
		m.addAttribute("loc",loc);
		return "common/msg";
		
	}

}
