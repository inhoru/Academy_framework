package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // spring에서 Controller 역할을 하는 클래스에 적용한다.
// spring bean으로 등록된다.
public class MainController {
	
	// log를 출력하기 위한 Logger 가져오기
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	
	
	
	
	
	// controller로 등록된 클래스는 클라이언트가 요청한 서비스를 진행하는 메소드(맵핑메소드)를 선언한다.
	// 요청주소(url)와 연결되는 메소드
	
	// @RequestMapping 어노테이션을 맵핑메소드 선언부에 선언을 한다.
	// controller에 선언된 메소드는 일반적으로 String값을 반환하게 설정함.
	// view를 선택해서 출력시킬 때..!(String 자주 사용)
	@RequestMapping("/")
	public String main(HttpServletRequest req, HttpSession session, HttpServletResponse res) {
		// 메소드가 반환하는 값은 viewResolver Bean이 처리함
		// 등록된 InternalResourceResolver Bean은 반환된 문자열에
		// 객체에 설정된 prefix, suffix를 붙여 내부에서 화면출력파일을 찾는다.
		// /WEB-INF/views/리턴값.jsp
		// RequestDispatcher("/WEB-INF/views/리턴값.jsp").forward -> 내부적으로 이렇게 처리
		
		// 쿠키 추가하기
		Cookie c = new Cookie("testData", "cookiedata");
		c.setMaxAge(60*60*24);
		res.addCookie(c);
		
		// 세션 설정
		session.setAttribute("sessionId", "admin");
		
		
		
		// ***log4j를 이용해서 log 출력하기***
		// slf4j에서 제공하는 Logger인터페이스를 구현한 클래스를 이용함
		// LoggerFactory 클래스에 static메소드인 getLogger(logger를 가져오는 클래스 지정);
		
		// *log를 출력할 때는 logger가 제공하는 메소드를 이용*
		// - debug() : 개발 시에 사용하는 로그를 출력할 때 사용
		// - info() : 프로그램 실행하는 정보를 출력할 때 사용
		// - warn() : 잘못된 사용을 출력할 때 사용
		// - error() : Exception이 발생, 실행이 불가능한 기능에 대한 로그 출력할 때 사용
		// *메소드의 매개변수는 String만 가능, 객체나 다른 데이터를 출력하려면 ("{}", 출력할 변수) -> 자동으로 String으로 변환해줌
		
		// *level*
		// debug -> info -> warn -> error -> fatal
		
		// 로그 출력하기
		logger.debug("debug 내용 출력하기");
		logger.info("info 내용 출력하기");
		logger.warn("warn 내용 출력하기");
		logger.error("error 내용 출력하기");
		
		
		
		
		return "index";
	}
	
}
