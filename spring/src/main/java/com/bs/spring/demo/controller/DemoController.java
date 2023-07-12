package com.bs.spring.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
//Mapping메소드가 하나의 서블릿이라고 생각하면 된다.
public class DemoController {
	
	@Autowired
	private DemoService service;
	
	@RequestMapping("/demo/demo.do")
	public String demo() {
		return "demo/demo";
	}

	// 메소드 선언하기
	// return 값과 매개변수알아보기
	// 1. 반환형
	// 1)String : ViewResolver에 의해서 view화면을 출력해줌 * 기본적으로 많이 사용
	// 2)void : HttpServletResponse객체로 직접 응답메세지를 작성할때 사용
	// 3)ModelAndView: 화면에 전달할 데이터와 view 내용을 저장하는 객체 spring제공해줌
	// 4) 클래스타입 : json으로 데이터를 반환할때 많이 사용 Restful하게 서버를 구성했을때 많이사용
	// *ResponesEntity<클래타입>

	// 2. 매개변수로 선언할 수 있는 타입
	// 1) HttpServletRequest : servlet처럼 사용가능
	// 2) HttpServletResopnse: servlet처럼 사용가능
	// 3) HttpSession : servlet처럼 사용가능
	// 4) java.util.Locale : 서버의 로케일정보 저장한 객체
	// 5) InpitStream/Reader : 파일 읽어올때 사용하는 stream
	// 6) OutputStream.Writer : 파일을 보낼때 사용하는 stream
	// 7) 기본자료형 변수 : 클라이언트가 보낸 parameter데이터랑 선언한 변수 이름이 동일하면 자동으로 매핑해준다.
	// 선언이름과 파라미터이름이 다를경우 @RequestParam어노테이션을 이용해서 연결할 수 있음
	// @RequestParam은 매핑, 기본값설정,필수여부설정
	// 8) 클래스변수 : Command라고 함, parameter데이터를 필드에 넣어서 객체를 전달
	// * parmaeter이름과 필드명이 같은 데이터를 대입해줌.
	// 9) java.uilt.Map : @RequestParam 어노테이션이랑 같이 사용, parameter값을 map으로 선언

	// 10) @RequestHeader(name값)와 기본자료형을 작성하면 header정보를 받을 수 있음
	// 11) @CookieValue(name값)와 기본자료형을 작성하면 Cookie에 저장된 값들 받을수 있음
	// 12) Model : request와 비슷하게 데이터를 key/value형식으로 저장할 수 있는 객체
	// 13) ModelAndView : model과 view를 동시에 저장하는 객체

	// 메소드 어노테이션
	// @ResponseBody -> Rest방식으로 클래스를 json으로 전송할때
	// @RequestBody -> Json방식으로 전송된 parameter를 클래스로 받을때 사용
	// @GetMapping,@postMapping,@DeleteMapping...

	// 서블릿 방식으로 매핑메소드 이용하기
	@RequestMapping("/demo/demo1.do")
	public void demo1(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, IOException, ServletException {
		System.out.println(req);
		System.out.println(res);
		String devName = req.getParameter("devName");
		int devAge = Integer.parseInt(req.getParameter("devAge"));
		String devGender = req.getParameter("devGender");
		String devEmail = req.getParameter("devEmail");
		String[] devLang = req.getParameterValues("devLang");
		System.out.println(devName + devAge + devGender + devEmail);
		for (String l : devLang) {
			System.out.println(l);
		}

		Demo d = Demo.builder().devName(devName).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang)
				.build();
		req.setAttribute("demo", d);
		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req, res);

		res.setContentType("text/html;charset=-utf-8");
		PrintWriter out = res.getWriter();

	}

	// 1:1매칭하여 데이터 받기
	// 매핑메소드의 매개변수에 파라미터로 전송되는 name과 동일한 이름의 변수를 선언
	// 매개변수의 타입은 사용할 타입으로 설정 * 변경이 가능해야함.
	// 선언된 매개변수의 이름과 파라미터값이 일치해야한다.

	// 매개변수의 데이터갑은 모두 들어와있어야한다 안그럼 오류발생 String은 null로나와서 가능하긴하다.
	// public String demo2(String devName,int devAge,String devGender,String
	// devEmail,String[] devLang, double weight ,Model model) {

	@RequestMapping("/demo/demo2.do")
	public String demo2(String devName, int devAge, String devGender, String devEmail, String[] devLang, Model model) {
		System.out.println(devName + devAge + devGender + devEmail + Arrays.toString(devLang));
		// 페이지에 생성한 데이터를 전송하려면....request, session, servletcontext
		// Spring 에서 데이터전송하는 객체를 제공함. -> Model
		// Model에 데이터저장하기 -> model.addAttribute("key",balue);
		Demo d = Demo.builder().devName(devName).devAge(devAge).devGender(devGender).devEmail(devEmail).devLang(devLang)
				.build();
		model.addAttribute("demo", d);

		return "demo/demoResult";
	}

	// 선언된 매개변수의 이름과 파라미터값이 일치해야한다. 하지만@RequestParma 쓰면상관없다.
	// 파라미터데이터를 받을때 @RequestParma어노테이션을 이용해서
	// 옵션을 설정할 수 있다.
	@RequestMapping("/demo/demo3.do")
	public String requestParmuse(@RequestParam(value = "devName", defaultValue = "아무개") String name,
			@RequestParam(value = "devAge", defaultValue = "10") int age,
			@RequestParam(value = "devGender", defaultValue = "M") String gender,
			// 필수값이아니라 선택적으로 넘오오는 값이야 false를주면 필수값x
			@RequestParam(value = "devEmail", required = true) String devEmail, String[] devLang, Model model) {

		System.out.println(name + age + gender + devEmail + Arrays.toString(devLang));

		Demo d = Demo.builder().devName(name).devAge(age).devGender(gender).devEmail(devEmail).devLang(devLang).build();
		model.addAttribute("demo", d);

		return "demo/demoResult";
	}

	// DTO/VO 객체로 직접 parameter값 받기
	// 매개변수로 전달된 parameter이름과 동일한 필드를 갖는 객체를 선언함.
	// * 주의할점은 클래스타입 주의 Date를 전달받을때는 java.sql.Date로 하자.

	@RequestMapping("/demo/demo4.do")
	public String commangMapping(Demo demo, Model m) {
		System.out.println(demo);
		m.addAttribute("demo", demo);
		return "demo/demoResult";
	}

	// Map으로 parameter데이터 받아오기
	// @RequestParam어노테이션 설정 Map
	// Map은 단일값을 받을때사용 배열을 안해줌
	// 구지쓸거면 따로매개변수로받아서쓰자
	@RequestMapping("/demo/demo5.do")
	public String mapPapping(@RequestParam Map<String, Object> param, String[] devLang, Model m) {
		System.out.println(param);
		param.put("devLang", devLang);
		m.addAttribute("demo", param);
		return "demo/demoResult";
	}

	// 추가데이터 받아오기
	// Cookie, Header, Session
	// Cookie : @CookieValue(value="key") String data
	// Header : @RequestHeader(value="헤더이름") String header
	// Session : @SessionAttribute(value="세션key값)String id

	@RequestMapping("/demo/demo6.do")
	public String extraData(@CookieValue(value = "testData",required=false,defaultValue = "rest-time") String data,
			@RequestHeader(value = "User-agent") String userAgent,
			@SessionAttribute(value = "sessionId") String sessionId,
			@RequestHeader(value="Referer")String referer) {
		System.out.println("쿠키 : "+ data);
		System.out.println("헤더 : "+ userAgent);
		System.out.println("세션 : "+ sessionId);
		System.out.println("이전페이지 : "+ referer);
			
		return "index";
	}
	
	//ModelAndView 객체를 이용해서 반환하기
	
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewReturn(Demo d, ModelAndView mv) {
		//ModelAndView view설정과, Model설정은 같이 할 수 있는 객체
		//view : setViewName()메소드를 이용해서 저장
		//data : addObject("key",value)메소드이용해서 저장
		mv.addObject("demo", d);
		mv.setViewName("demo/demoResult");
		
		return mv;
	}
	
	//자료형에 대해 반환하기 -> Data만 응답할때 사용 - > jackson라이브러리를 이용해서 처리
	//메소드선언부 @ResponseBody어노테이션 사용
	//Restfull 메소드를 구현했을때 사용
	@RequestMapping("/demo/demo8.do")
	@ResponseBody
	public String dataReturn(){
		return " 강태풍 강바람 강호농 강노노 강동동";
	}
	
	//Request요청 메소드(GET,POST)를 필터링하기
	//@RequestMapping(value="url",method="RequestMethod.GET||RequestMethod.POST")
	
	//@RequestMapping(value="/demo/demo9.do",method = RequestMethod.POST)
	//@PostMapping("/demo/demo9.do")
	@GetMapping("/demo/demo9.do")
	public String methodCheck(Demo d,Model m) {
		m.addAttribute("demo", d);
		return "demo/demoResult";
	}
	
	//간편하게 사용할 수 있게 Mapping어노테이션을 지원
//	@GetMapping
//	@PostMapping
//	@DeleteMapping
//	@PutMapping
	
	//mapping주소를 설정할때 {}을 사용할 수 있음
	//board/boardview?no=1
	//board/1 method=GET
	//board/ method=GET
	//board method=GET
	@GetMapping("/demo/{no}")
	//주소에있는 값가져올떄 PathVariable를 사용
	public String searchDemo(@PathVariable(value="no")int no) {
		System.out.println(no);
		return "demo/demoResult";
	}
	
	@RequestMapping(value="/demo/insertDemo.do",method=RequestMethod.POST)
	public String insertDemo(Demo demo,Model m) {
		int result=service.insertDemo(demo );
		System.out.println(result);
		
		m.addAttribute("msg", result>0?"저장성공" : "저장실패");
		m.addAttribute("loc", "/demo/demo.do");
		
		//sendRedirect로 변경하는 방법
		//prefix redirect : 요청할 주소(매핑주소)
		//요청할주소에 jsp를 직접 호출할수가없다 왜냐? jsp는 WEB-INF안에있기때문에 직접호출이불가능 컨트롤러를통해서만 접근
		/* return "redirect:/"; */
		//return "redirect:/demo/demo.do";
		return "common/msg";
	}
	
	@RequestMapping("/demo/selectDemoAll.do")
	public String selectDemoAll(Model m) {
		List<Demo> result=service.selectDemoAll();
		
		m.addAttribute("demo", result);
		return "demo/demoList";
	}
	//주소에있는 값가져올떄 PathVariable를 사용
	@RequestMapping(value="/demo/updateDemoMove.do", method=RequestMethod.POST)
	public String updateDemoMove(Model m,int no) {
		Demo result=service.selectDemoNo(no);
		m.addAttribute("demo",result);
		return "demo/demoUpdate";
	}
	@RequestMapping(value="/demo/updateDemo.do", method=RequestMethod.POST)
	public String updateDemo(Demo demo,int no,Model m) {
		demo.setDevNo(no);
		int result=service.updateDemo(demo);
		System.out.println(result);
		m.addAttribute("msg", result>0?"수정성공" : "수정실패");
		m.addAttribute("loc", "/demo/selectDemoAll.do");
		return "common/msg";
	}
	
	
	
	
	
	
}
