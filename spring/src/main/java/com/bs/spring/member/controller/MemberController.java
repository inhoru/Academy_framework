package com.bs.spring.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member") // 아래 RequestMapping에서 생략가능
@SessionAttributes({"loginMember"})
@Slf4j // 롬복에서 제공(log 많이 씀)
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "member/enrollMember";
	}
	
	@RequestMapping(value = "/insertMember.do", method = RequestMethod.POST)
	public String insertMember(Member mem, Model m) {
		
		// 패스워드를 암호화해서 처리하자.
		String oriPassword = mem.getPassword();
//		System.out.println(oriPassword);
		log.debug(oriPassword);
		
		
		String encodePassword = passwordEncoder.encode(oriPassword); // 인코더(암호화해줌)
//		System.out.println(encodePassword);
		log.debug(encodePassword);
		
		mem.setPassword(encodePassword); // 암호화된 채로 저장됨
		
		int result = service.insertMember(mem);
		if(result>0) {
			m.addAttribute("msg", "회원가입 성공!");
			m.addAttribute("loc", "/");
		}
		else {
			m.addAttribute("msg", "회원가입 실패..");
			m.addAttribute("loc", "/member/enrollMember.do");
		}
		return "common/msg";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String selectMemberById(@RequestParam Map member, Model model, HttpSession session) {
		Member m = service.selectMemberById(member);
		
		// 암호화된 값을 비교하기 위해서는 BCryptPasswordEncoder가 제공하는 메소드를 이용해야 한다.
		// passwordEncoder.matches(사용자 입력값, 암호화 값)
		if(m!=null && passwordEncoder.matches((String)member.get("password"), m.getPassword())) {
			// 로그인 성공
			// session.setAttribute("loginMember", m);
			
			// Model을 이용한 로그인 처리하기
			model.addAttribute("loginMember", m);
			
		} else {
			// 로그인 실패
			model.addAttribute("msg", "로그인 실패");
			model.addAttribute("loc", "/");
			return "common/msg";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
//	public String logout(HttpSession session) {
//		if(session != null) session.invalidate(); // 로그아웃이니까 전체 세션을 삭제해야 함
	public String logout(SessionStatus status) {
		// @SessionAttributes로 등록된 내용 삭제하기
		// SessionStatus객체를 이용해서 삭제
		if(!status.isComplete()) status.setComplete(); // 세션 만료
		return "redirect:/";
	}
	
	@RequestMapping("/mypageView.do")
	public String mypageView() {
		return "member/mypageView";
	}
}
