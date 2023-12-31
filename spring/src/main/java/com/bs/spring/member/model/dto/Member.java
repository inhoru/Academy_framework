package com.bs.spring.member.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member implements UserDetails {
	@NotEmpty(message = "이부분은 반드시 입력해주시요플레")
	@Size(min=4,message = "4글자이상 써주세요루")
	private String userId;
	@Pattern(regexp= "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z~!@#$%^&*()]{8,}",message = "8글자이상 대,소,특수 다넣어주세요구르트")
	private String password;
	
	private String name;
	
	private String gender;
	@Min(value = 14,message = "14살이상만가눙")@Max(value = 150,message = "늙은이는 나가")
	private int age;
	@Email
	private String email;
	
	@NotEmpty(message = "핸드폰 없니?")
	private String phone;
	
	private String address;
	
	private String[] hobby;
	
	@Past
	private Date enrollDate;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 로그인한 사용자의 권환을 설정하는 메소드
		List<GrantedAuthority> auth=new ArrayList();
		auth.add(new SimpleGrantedAuthority("user"));
		if(userId.equals("admin")) {
			auth.add(new SimpleGrantedAuthority("admin"));
		}else if(userId.equals("user04")) {
			auth.add(new SimpleGrantedAuthority("manager"));
			
		}
		return auth;
	}

	@Override
	public String getUsername() {
		//인증할 id값을 반환해주는 함수
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
