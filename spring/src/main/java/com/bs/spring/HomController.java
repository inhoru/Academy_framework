package com.bs.spring;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Employee;
import com.bs.spring.beantest.FuntionalTest;
import com.bs.spring.include.TargetComponent;

@Controller
public class HomController {
	
	//springbean으로 등록된 객체는 필드로 가져와 사용할 수 있음
	@Autowired
	//중복된 타입이 있는 경우 
	//@Qualifier어노테이션을 이용해서 특정 bean을 선택할 수 있음
	@Qualifier("dog")
	private Animal a;
	@Autowired
	@Qualifier("bbo")
	private Animal b;
	
	//springBean으로 등록되지않은 객체를 Autowired하면?? -> 에러발생
	@Autowired(required = false)
	private Employee emp;
	
	@Autowired
	private Employee emp2;
	
	//java로 등록한 bean가져오기
	@Autowired
	@Qualifier("ani")
	private Animal c;
	
	@Autowired
	@Qualifier("sol")
	private Employee sol;
	
	@Autowired
	private List<Animal> animals;
	
	@Autowired
	private TargetComponent tc; 
	
	
	//@어노테이션으로 bean등록
	@Autowired
	private FuntionalTest ft;
	//basepackage 외부에 있는 @Component
	@Autowired
	private Test test;
	
	@RequestMapping("/test")
	public String home() {
		System.out.println(a);
		System.out.println(b);
		System.out.println(emp);
		System.out.println(emp2);
		System.out.println(c);
		System.out.println(sol);
		animals.forEach(System.out::println);
		
		System.out.println(tc);
		System.out.println("functionalTest출력");
		System.out.println(ft);
		System.out.println(ft.getA());
		
		//spring에서 파일을 불러올 수 있는 Resource객체를 제공
		Resource resource=new ClassPathResource("mydata.properties");
		try {
			Properties prop=PropertiesLoaderUtils.loadProperties(resource);
			System.out.println(prop);
			resource=new FileSystemResource("F:\\GDJ64\\sharedata\\git정리.txt");
			Files.lines(Paths.get(resource.getURI()),Charset.forName("UTF-8"))
			.forEach(System.out::println);
			
			resource=new UrlResource("https://www.naver.com");
			InputStream is=resource.getInputStream();
			int d=0;
			StringBuffer sb=new StringBuffer();
			while((d=is.read())!=-1) {
				sb.append((char)d);
			}
			System.out.println(sb);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		return "index";
	}
}
