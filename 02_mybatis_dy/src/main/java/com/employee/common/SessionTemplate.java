package com.employee.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionTemplate {
	public static SqlSession getSession() {
		String file="mybatis-config.xml";
		SqlSession session=null;
		try(InputStream is=Resources.getResourceAsStream(file);) {
			session=new SqlSessionFactoryBuilder().build(is).openSession(false);

		}catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	public static SqlSession getWebSession() {
		String file="mybatis-config.xml";
		SqlSession session=null;
		try(InputStream is=Resources.getResourceAsStream(file);) {
			session=new SqlSessionFactoryBuilder().build(is,"web").openSession(false);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	
}
