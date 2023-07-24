package com.bs.spring.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.service.MemberService;

@RestController
//Controller이 설정되어있고  모든 메소드앞에@ResponseBody 이선언되어있다.
@RequestMapping("/member")
public class RestMemberController {
	
	private MemberService service;
	
	public RestMemberController(MemberService service) {
		this.service=service;
	}
	
	@GetMapping("/{id}")
	public Member selectMemberById(@PathVariable("id")String id){
		return service.selectMemberById(Map.of("userId",id));
	}
	
	@GetMapping
	//public List<Member> selectMemberAll(){
	public ResponseEntity<List<Member>> selectMemberAll(){
		List<Member> members=service.selectAll();
		ResponseEntity<List<Member>> respones= ResponseEntity.ok(members);
//				new ResponseEntity(members,HttpStatus.BAD_REQUEST);
				
		return respones;
	}
	
	@PostMapping
	public int insertMember(@RequestBody Member m) {
		return service.insertMember(m);
	}
	
//	@PutMapping
//	public int updateMember(@RequestBody Member m) {
//		return service.updateMember(m);
//	}
	
//	@DeleteMapping
//	public int deleteMember(@PathValiable("id") String id) {
//		return service.deleteMember(id);
//	}
	//특정 게시글에 댓글들 가져오기
	// /board/{no}/comment/{commentno}
	//ResponseEntity객체
	
}
