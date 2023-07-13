package com.bs.spring.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.service.DemoService;

@Controller
public class DemoController {
	
	private Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	
	@Autowired
	private DemoService service;
	
	
	// Mapping메소드 = 하나의 서블릿 -> 하나의 서비스
	@RequestMapping("/demo/demo.do")
	public String demo() {
		return "demo/demo";
	}
	
	// 메소드 선언하기
	// 리턴값, 매개변수 알아보기
	// 1. 반환형
	// 	1) String : ViewResolver에 의해서 view화면을 출력해줌 * 기본적으로 많이 사용
	//	2) void : HttpServletResponse객체로 직접 응답메세지를 작성할 때 사용(doGet과 완전히 동일해질 수 있다)
	//			ex) 파일업로드 등(response 사용했던 것들)
	//	3) ModelAndView : 화면에 전달할 데이터와 view내용을 동시에 저장하는 객체(Spring이 제공)
	//	4) 클래스타입(일반객체) : json으로 데이터를 반환할 때, Rest방식으로 Restful하게 서버를 구성했을 때 많이 사용
	//			* ResponseEntity<클래스타입> 으로 많이 보냄
	
	// 2. 매개변수
	//	1) HttpServletRequest : servlet처럼 사용가능
	//	2) HttpServletResponse : servlet처럼 사용가능
	//	3) HttpSession : servlet처럼 사용가능 -> session값을 가져와서 대입해줌
	//	4) java.util.Locale : 서버의 로케일 정보를 저장한 객체
	//	5) InputStream/Reader : 파일을 io할 때(읽어올 때) 사용하는 stream
	//	6) OutStream/Writer : 파일을 보낼 때 사용하는 stream
	// 	7) 기본자료형 변수 : 클라이언트가 보낸 parameter데이터랑 선언한 변수 이름이 동일하면 자동으로 맵핑해줌
	//			* 선언한 이름과 파라미터 이름이 다를 경우 @RequestParam 어노테이션을 이용해서 연결할 수 있음
	//		@RequestParam은 맵핑, 기본값 설정, 필수여부 설정 가능(파라미터에 대한 추가설정도 가능)
	// 	8) 클래스 변수 : Command라고 함, parameter데이터를 필드에 넣어서 객체를 전달
	//			* parameter의 이름과 필드명이 같은 데이터를 대입
	// 	9) java.util.Map : @RequestParam 어노테이션이랑 같이 사용, parameter값을 map으로 선언
	
	// 	> 어노테이션 매개변수
	//	10) @RequestHeader(name값)와 기본 자료형을 작성하면 header정보를 받을 수 있음
	//	11) @CookieValue(name값)와 기본 자료형을 작성하면 cookie에 저장된 값을 받을 수 있음
	
	// 	> 스프링 제공 매개변수
	//	12) Model : request와 비슷하게 데이터를 key:value형식으로 저장할 수 있는 객체(데이터 전송용 객체)
	//	13) ModelAndView : model과 view를 동시에 저장하는 객체 
	
	// 메소드 어노테이션
	// @ResponseBody -> Rest방식으로 클래스를 json으로 전송할 때 사용
	// @Requestbody -> json방식으로 전송된 parameter를 클래스로 받을 때 사용
	// @GetMapping, @PostMapping, @DeleteMapping..

	
	// ***서블릿 방식으로 이용하기***
	@RequestMapping("/demo/demo1.do")
	public void demo1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		logger.debug("request : {}", req); // debug(String -> 객체일 경우 {}위치에 객체 들어옴)
		logger.debug("response : {}" + res);
		// System.out.println(req);
		// System.out.println(res);
		
		String devName = req.getParameter("devName");
		int devAge = Integer.parseInt(req.getParameter("devAge"));
		String devGender = req.getParameter("devGender");
		String devEmail = req.getParameter("devEmail");
		String[] devLang = req.getParameterValues("devLang");
		
		logger.debug(devName + devAge + devGender + devEmail);
		
		// System.out.println(devName + devAge + devGender + devEmail);
		for(String l : devLang) {
			logger.debug(l);
			// System.out.println(l);
		}
		
		Demo d = Demo.builder()
				.devName(devName)
				.devAge(devAge)
				.devEmail(devEmail)
				.devGender(devGender)
				.devLang(devLang)
				.build();
		req.setAttribute("demo", d);
		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req, res);
		
		
//		res.setContentType("text/html; charset=utf-8");
//		PrintWriter out = res.getWriter();
//		out.print("<h2>" + devName + devAge + devGender + devEmail + "</h2>");
		
	}
	
	
	// ***스프링 방식으로 이용하기***
	// 1:1 매칭하여 데이터 받기
	// 맵핑메소드의 매개변수에 파라미터로 전송되는 name과 동일한 이름의 변수를 선언(이름이 다르면 맵핑 안돼서 데이터 안받아옴)
	// 매개변수의 타입은 사용할 타입으로 설정 *변경이 가능해야 한다.
	@RequestMapping("/demo/demo2.do")
	public String demo2(String devName, int devAge, String devGender, String devEmail, String[] devLang,
			// double weight, -> 매개변수에 넘어올 데이터가 아닌 추가로 참조형을 넣으면 null로 넣어서 에러x, 기본형(double, int 등)은 에러남
			// 데이터를 받아올 때도 기본형 데이터에 넘어오는 값이 없으면 에러남
			Model model) {
		
		System.out.println(devName + devAge + devGender + devEmail + Arrays.toString(devLang));
		
		// 페이지에 생성한 데이터를 전송하려면..? request, session, servletcontext..
		// Spring에서 데이터 전송하는 객체를 제공함 -> Model(인터페이스)
		// Model에 데이터 저장하기 -> model.addAttribute("key", value);
		Demo d = Demo.builder()
				.devName(devName)
				.devAge(devAge)
				.devGender(devGender)
				.devEmail(devEmail)
				.devLang(devLang)
				.build();
		model.addAttribute("demo", d);
		
		return "demo/demoResult";
	}
	
	
	// 파라미터 데이터를 받을 때 @RequestParam 어노테이션을 이용해서 옵션을 설정할 수 있다.
	@RequestMapping("/demo/demo3.do")
	public String requestParamUse(@RequestParam(value = "devName", defaultValue = "아무개") String name, @RequestParam(value = "devAge", defaultValue = "10") int age, @RequestParam(value = "devGender") String gender,
			@RequestParam(value = "devEmail", required = false) String devEmail, String[] devLang, Model model) {
		// defaultValue는 들어오는 값이 없을 때 기본값, required = false는 필수값이 아니다.(값이 없으면 null로 들어오는 것도 아니고 아예 무시함)
		System.out.println(name + age + gender + devEmail + Arrays.toString(devLang));
		
		Demo d = Demo.builder()
				.devName(name)
				.devAge(age)
				.devGender(gender)
				.devEmail(devEmail)
				.devLang(devLang)
				.build();
		model.addAttribute("demo", d);
		
		return "demo/demoResult";
	}
	
	
	// DTO/VO 객체로 직접 parameter값 받기
	// 매개변수로 전달된 parameter이름과 동일한 필드를 갖는 객체를 선언함.
	// * 주의! 클래스(객체)타입! Date를 전달받을 때는 java.sql.Date로 하자. 
	@RequestMapping("/demo/demo4.do")
	public String commandMapping(Demo demo, Model m) {
		System.out.println(demo);
		// 필드명과 프론트엔드에서 보내는 name이 일치해야함
		// util.Date는 안되지만 sql.Date는 가능 -> has-a관계 객체 파싱 불가능
		m.addAttribute("demo", demo);
		return "demo/demoResult";
	}
	
	
	// Map으로 parameter데이터 받아오기
	// @RequestParam 어노테이션 설정, Map
	@RequestMapping("/demo/demo5.do")
	public String mapMapping(@RequestParam Map param, String[] devLang, Model m) {
		System.out.println(param);
		// 배열처리는 안해줌, Date는 String으로 넘어와서 파싱해줘야 함 -> Map으로는 단일값 받는 게 좋음
		param.put("devLang", devLang);
		m.addAttribute("demo", param);
		return "demo/demoResult";
	}
	
	
	// 추가데이터 받아오기
	// Cookie, Header, Session
	// Cookie : @CookieValue(value = "key") String data
	// Header : @RequestHeader(value = "헤더이름") String header
	// Session : @SessionAttribute(value = "세션key값") String id;
	@RequestMapping("/demo/demo6.do")
	public String extraData(@CookieValue(value="testData", required = false, defaultValue = "rest-time") String data,
							@RequestHeader(value = "User-agent") String userAgent,
							@SessionAttribute(value = "sessionId") String sessionId,
							@RequestHeader(value = "Referer") String referer) {
		// referer : 이전페이지
		System.out.println("구키 : " + data);
		System.out.println("헤더 : " + userAgent);
		System.out.println("세션 : " + sessionId);
		System.out.println("이전페이지 : " + referer);
		// 쿠키 없으면 에러남 -> 옵션으로 required 설정해주기
		return "index";
	}
	
	
	// ***ModelAndView객체를 이용해서 반환하기***
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewReturn(Demo d, ModelAndView mv) {
		// ModelAndView view설정과 Model설정을 같이 할 수 있는 객체
		// view : setViewName()메소드를 이용해서 저장
		// data : addObject("key",value) 메소드 이용해서 저장
		mv.addObject("demo", d);
		mv.setViewName("demo/demoResult"); // resolver 통해서 prefix, suffix 붙음
		// mv.get
		return mv;
	}
	
	
	// ***자료형에 대해 반환하기*** -> Data만 응답할 때 사용 -> jackson라이브러리를 이용해서 처리
	// 메소드선언부에 @ResponseBody 어노테이션 사용
	// Restful메소드를 구현했을 때 사용
	@RequestMapping("/demo/demo8.do")
	@ResponseBody
	public String dataReturn(){
		return "유병승, 최주영, 조장흠, 최솔, 조윤진";
	}
	
	
	// Request요청 메소드(GET, POST)를 필터링하기
	// @RequestMapping(value = "url", method = RequestMethod.Get || RequestMethod.POST)
	// @RequestMapping(value = "/demo/demo9.do", method = RequestMethod.POST) // 프론트에서 post로 설정되어 있는데 GET으로 설정하면 405 에러 뜸(반대도 동일)
//	@GetMapping("/demo/demo9.do")
	@PostMapping("/demo/demo9.do")
	public String methodCheck(Demo d, Model m) {
		m.addAttribute("demo", d);
		return "demo/demoResult";
	}
	
	
	// 간편하게 사용할 수 있게 Mapping 어노테이션을 지원
//	@GetMapping
//	@PostMapping
//	@DeleteMapping
//	@PutMapping
	
	
	// mapping 주소를 설정할 때 {}를 사용할 수 있음
	// /board/boardView?no=1
	// /board/1 method=GET -> 1번게시글
	// /board method=GET -> 전체게시글
	@GetMapping("/demo/{no}")
	public String searchDemo(@PathVariable(value = "no") int no) {
		System.out.println(no);
		return "demo/demoResult";
	}
	
	
	// ********서버에서 데이터 가져오기********
	@RequestMapping(value="/demo/insertDemo.do", method=RequestMethod.POST)
	public String insertDemo(Demo demo, Model m) {
		int result = service.insertDemo(demo);
		m.addAttribute("msg", result>0 ? "저장성공" : "저장실패");
		m.addAttribute("loc", "/demo/demo.do");
		
		// sendRedirect로 변경하는 방법
		// prefix redirect:요청할 주소(맵핑주소) -> jsp 호출 불가(WEB-INF에 들어있어서 직접 접근 불가)
		// return "demo/demo";
//		return "redirect:/demo/demo.do";
		return "common/msg";
	}
	
	@RequestMapping(value="/demo/selectDemoAll.do", method=RequestMethod.POST)
	public String selectDemoAll(Model m) {
		List<Demo> d = service.selectDemoAll();
		m.addAttribute("demo", d);
		return "demo/demoList";
	}
	
	
}
