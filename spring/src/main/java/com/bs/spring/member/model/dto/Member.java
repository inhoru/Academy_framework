package com.bs.spring.member.model.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	@NotEmpty(message = "이부분은 반드시 입력해주시요플레")
	@Size(min=4,message = "4글자이상 써주세요루")
	private String userId;
	@Pattern(regexp= "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z~!@#$%^&*()]{8,}",message = "8글자이상 대,소,특수 다넣어주세요구르트")
	private String password;
	
	private String userName;
	
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
	
}
