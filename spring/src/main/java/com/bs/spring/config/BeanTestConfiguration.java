package com.bs.spring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Department;
import com.bs.spring.beantest.Employee;

// 클래스 방식으로 bean 등록해서 사용하기
// POJO클래스를 configuration으로 사용할 수도 있음 -> @Configuration 어노테이션 이용

@Configuration
@ComponentScan(basePackages = "com.bs.spring",
		includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.bs.spring.include.*"})}, // 어노테이션 표시가 없어도 필터에 해당되면 bean으로 등록 
		excludeFilters = {} // 등록된 걸 제외시킴
		)
// @Import() // 다른 configuration을 가져와 처리하는 것 
public class BeanTestConfiguration {
	// springbeanconfiguration.xml과 동일한 기능
	
	// spring에서 사용할 bean을 자바코드로 등록할 수 있다.
	// @Bean 어노테이션을 이용
	// 메소드 선언을 통해 등록
	@Bean
	@Order(1) // bean의 우선순의를 설정할 수 있다.
	public Animal ani() {
		return Animal.builder()
				.name("킥킥")
				.age(5)
				.height(80).build();
	}
	
	@Bean
	// 등록된 bean에 특정 id값 부여하기
	@Qualifier("sol")
	public Employee getEmployee(@Qualifier("sal") Department d) {
		// 매개변수는 자동으로 @Autowired 넣어줌
		return Employee.builder()
				.name("최솔")
				.age(27)
				.address("경기도 안양시")
				.salary(200)
				.dept(d).build();
	}
	
	@Bean
	public Department sal() {
		return Department.builder()
				.deptCode(2L)
				.deptTitle("영업부")
				.deptLocation("서울")
				.build();
	}
	
	@Bean
	public BasicDataSource getDataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		source.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		source.setUsername("spring");
		source.setPassword("spring");
		return source;
	}
	
//	@Bean
//	public Gson gson() {
//		return new Gson();
//	}
}
