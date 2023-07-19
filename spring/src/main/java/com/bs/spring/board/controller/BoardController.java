package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
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

	@RequestMapping("/boardList.do")
	public String boardList(@RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "20") int numPerpage, Model m) {
		List<Board> boards = service.selectBoard(Map.of("cPage", cPage, "numPerpage", numPerpage));
		int totalData = service.selectBoardCount();
		// paging
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "boardList.do"));

		m.addAttribute("totalData", totalData);
		m.addAttribute("board", boards);

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
	@RequestMapping("/boardInfo.do")
	public String boardInfo(int no, Model m) {
		Board board = service.selectBoardNo(no);
		m.addAttribute("board", board);
		return "board/boardInfo";
	}

	@RequestMapping("/boardWriteMove.do")
	public String boardWriteMove(Model m) {

		return "board/boardWrite";
	}

	@RequestMapping("/boardWrite.do")
	public String insertBoard(Board b, MultipartFile[] upFile, 
			HttpSession session, Model m) {
	      
	      // MultipartFile에서 제공하는 메소드를 이용해서 파일을 저장할 수 있음 -> transferTo()메소드
	      // 절대경로 가져오기
	      String path = session.getServletContext().getRealPath("/resources/upload/board/");
	      // 파일명에 대한 renamed 규칙을 설정
	      // 직접 리네임 규칙을 만들어서 저장해보자.
	      // yyyyMMdd_HHmmssSSS_랜덤값
	      
	      // *** 파일 여러개 등록하기 ***
//	      List <Attachment> files = new ArrayList(); -> Board에서 new 선언해줌
	      if(upFile != null) {
	         for(MultipartFile mf : upFile) {
	            if(!mf.isEmpty()) {
	               // 파일 등록하기
	               String oriName = mf.getOriginalFilename();
	               String ext = oriName.substring(oriName.lastIndexOf(".")); // 확장자명
	               Date today = new Date(System.currentTimeMillis());
	               SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
	               int rdn=(int)(Math.random()*10000) + 1; // 카카오톡처럼 랜덤값 부여
	               String rename = sdf.format(today) + "_" + rdn + ext; // renamed 규칙
	               
	               try {
	                  mf.transferTo(new File(path + rename));   
	               } catch(IOException e) {
	                  e.printStackTrace();
	               }
	               
	               Attachment file = Attachment.builder()
	                     .originalFilename(oriName)
	                     .renamedFilename(rename)
	                     .build();
	               
	               b.getFile().add(file);
	            }
	         }
	      }
	      try {
	         service.insertBoard(b);
	      } catch(RuntimeException e) {
	         // DB 등록 실패 시 파일도 폴더에 저장되면 안됨(삭제해주기)
	         for(Attachment a : b.getFile()) {
	            File delFile = new File(path + a.getRenamedFilename());
	            delFile.delete();
	         }
	         
	         m.addAttribute("msg", "글쓰기 등록 실패! :(");
	         m.addAttribute("loc", "/board/boardWriteMove.do");
	         return "common/msg";
	      }
	      
//	      String msg;
//	      String loc = "/board/boardList.do";
//	      if(result > 0) {
//	         msg = "등록 성공!";
//	      } else {
//	         msg = "등록 실패!";
//	      }
//	      m.addAttribute("msg", msg);
//	      m.addAttribute("loc", loc);
	      
	      return "redirect:/board/boardList.do";
	}
	
	@RequestMapping("/filedownload")
	public void fileDown(String oriname,String rename,OutputStream out,
			@RequestHeader(value="user-agent") String header,HttpSession session,HttpServletResponse res) {
		
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile=new File(path+rename);
		try (FileInputStream fis=new FileInputStream(downloadFile); BufferedInputStream bis=new BufferedInputStream(fis);
				BufferedOutputStream bos=new BufferedOutputStream(out)){
			
			//	파일명 인코딩 코드 한글로 맞춰주기위해서 사용
				boolean isMs=header.contains("Trident")||header.contains("MSIE");
				String ecodeRename="";
				if(isMs) {
					ecodeRename=URLEncoder.encode(oriname,"UTF-8");
					ecodeRename=ecodeRename.replaceAll("\\+","%20");
				}else {
					ecodeRename=new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
				}
				res.setContentType("application/octet-stream;charset=utf-8");
				res.setHeader("Content-Disposition","attachment;filename=\""+ecodeRename+"\"");
				
				int read=-1;
				while((read=bis.read())!=-1){
					bos.write(read);
				}
				//
				
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
