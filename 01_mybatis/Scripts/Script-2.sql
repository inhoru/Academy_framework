alter SESSION SET "_ORACLE_SCRIPT"=TRUE;
CREATE USER mybatis IDENTIFIED BY mybatis 
DEFAULT tablespace users quota unlimited ON users;
GRANT resource, CONNECT TO mybatis;

create table student(
		student_no number primary key,
		student_name varchar2(30) not null,
		student_tel char(11) not null,
		student_email varchar2(50),
		student_addr varchar2(256),
		reg_date date default sysdate
	);

	create sequence seq_student;

SELECT * FROM student;
SELECT * FROM user_sequences;
insert into student values (seq_student.nextval, '홍길동', '01012345678', 'honggd@google.com','서울시 강남구', default);
SELECT * FROM student WHERE student_no=1;
SELECT count(*) FROM student;




