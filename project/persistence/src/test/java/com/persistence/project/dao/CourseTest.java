package com.persistence.project.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.dao.CourseDao;
import com.project.persistence.entity.Course;

@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CourseDao courseDao;
	
	@Before
	public void setUp(){
		Course cou = new Course("CSSSS");
		cou.setTitle("BASE DE DATOS");
		courseDao.saveOrUpdate(cou);
	}
	
	@Test
	public void getAll(){
		List<Course> all = courseDao.findAll();
		for(Course cou : all){
			System.out.print("ID ");
			System.out.println(cou.getCourseId());
			System.out.print("Nombre ");
			System.out.println(cou.getTitle());
		}
	}
	
	@Test
	public void getById(){
		Course cou = courseDao.getById("CSSSS");
		System.out.print("ID ");
		System.out.println(cou.getCourseId());
		System.out.print("Nombre ");
		System.out.println(cou.getTitle());
	}
	
	
	
	
	
	
	
}
