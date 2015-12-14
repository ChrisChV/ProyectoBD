package com.project.persistence.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.dao.StudentDao;
import com.project.persistence.entity.Student;

@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private StudentDao studentDao;
	
	@Before
	public void setUp(){
		studentDao.insert("C1234", null, "Chris", null);
		studentDao.insert("C1235", null, "Ale", null);
	}
	
	@Test
	public void findAll(){
		List<Student> all = studentDao.findAll();
		for(Student st : all){
			System.out.print("ID ");
			System.out.println(st.getId());
			System.out.print("Nombre ");
			System.out.println(st.getName());
		}
	}
	
	@Test
	public void findById(){
		Student st = studentDao.getById("C1234");
		System.out.print("ID ");
		System.out.println(st.getId());
		System.out.print("Nombre ");
		System.out.println(st.getName());
	}
	
	@Test
	public void delete(){
		studentDao.delete("C1234");
		List<Student> all = studentDao.findAll();
		for(Student st : all){
			System.out.print("ID ");
			System.out.println(st.getId());
			System.out.print("Nombre ");
			System.out.println(st.getName());
		}
	}
	
	@Test
	public void update(){
		Student st2 = studentDao.getById("C1234");
		st2.setName("XNPIO");
		studentDao.update(st2);
		List<Student> all = studentDao.findAll();
		for(Student st : all){
			System.out.print("ID ");
			System.out.println(st.getId());
			System.out.print("Nombre ");
			System.out.println(st.getName());
		}
	}
	
}
