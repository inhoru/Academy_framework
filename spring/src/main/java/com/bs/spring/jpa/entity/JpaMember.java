package com.bs.spring.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//일반 pojo클래스를 Entity로 등록하기 위해서는
//@Entity를 이용한다. -> 클래스 선언부에 선언.
// 기본생성자는 필수로 있어야한다. final클래스를 (enum,interface,inner)는 사용불가
@Entity//(name= "")
//@Table이용하기 -> Table에 대한 설정을 하는 어노테이션
//schema,catalog, 테이블레벨 제약조건 설정, DB에 생성될 테이블명설정
//@Table()
//@SequenceGenerator-> DB에서 사용할 sequence를 생성하는 어노테이션
//name 생성기이름 sequenceName DB의 시퀀스이름 initValue allocationSize 증가값
//@TableGenerator -> ID값을 중복없이 발급하는 테이블 생성해서ID 부여하는 용도

//@JsonIdentityInfo -> Entity겍체를 가져올때 양방향으로 일대다, 다대일 관게에잇으면 무한루핑현상이 발생하는데 이를 차단하는 어노테이션
//Table(name="memberjap")
@SequenceGenerator(name="seq_jpamemberno",sequenceName = "seq_jpamemberno",initialValue = 1,allocationSize = 1)
public class JpaMember {
	@Id//entity 를 식별하는 식별자, DB에서는 Primary key제약조건이 설정
	@GeneratedValue(generator = "seq_jpamemberno",strategy=GenerationType.SEQUENCE)
	private Long memberNo;
	private String memberId;
	private String memberPwd;
	private Integer age;
	private double height;
	
}
