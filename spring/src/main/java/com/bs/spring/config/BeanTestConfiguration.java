package com.bs.spring.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Department;
import com.bs.spring.beantest.Employee;

//클래스방식으로 bean등록해서 사용하기
// pojo클래스를 configuration으로 사용할 수 있음 
// -> @Configuration어노테이션이용 

@Configuration
@ComponentScan(basePackages = "com.bs.spring",
		includeFilters = {
			@ComponentScan.Filter(
					type = FilterType.REGEX,
					pattern = {"com.bs.spring.include.*"})},//어노테이션표시가 없더라도 해당이되면 bean으로 등록함, 
		excludeFilters = {}
		)
//@Import()//다른configuration을 가져와 처리하는것
public class BeanTestConfiguration {
	//springbeanconfiguration.xml과 동일한 기능
	
	//spring에서 사용할 bean을 자바코드로 등록할 수 있다.
	//@Bean어노테이션을 이용
	//메소드선언으로 통해 등록함.
	@Bean
	@Order(1)//bean우선순위를 설정할 수 있다.
	public Animal ani() {
		return Animal.builder()
				.name("킥킥")
				.age(5).height(80).build();
	}
	
	@Bean
	//등록된 bean에 특정id값 부여하기
	@Qualifier(value="sol")
	public Employee getEmployee(@Autowired @Qualifier("sal") Department d) {
		return Employee.builder()
				.name("최솔")
				.age(27)
				.address("경기도 안양시")
				.salary(200)
				.dept(d)
				.build();
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
	public BasicDataSource getDateSource() {
		BasicDataSource source=new BasicDataSource();
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




