package com.project.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Course;
import com.project.persistence.entity.Prereq;
import com.project.persistence.entity.PrereqId;

@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class PrereqDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private PrereqDao prereqDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Before
	public void setUp(){
		Course cour1 = new Course("CS1");
		cour1.setTitle("BASE DE DATOS1");
		Course cour2 = new Course("CS2");
		cour2.setTitle("BASE DE DATOS2");
		Course cour3 = new Course("CS3");
		cour3.setTitle("BASE DE DATOS3");
		Course cour4 = new Course("CS4");
		cour4.setTitle("BASE DE DATOS4");
		courseDao.saveOrUpdate(cour1);
		courseDao.saveOrUpdate(cour2);
		courseDao.saveOrUpdate(cour3);
		courseDao.saveOrUpdate(cour4);
		Prereq pre1 = new Prereq(new PrereqId("CS2", "CS1"));
		Prereq pre2 = new Prereq(new PrereqId("CS3", "CS2"));
		Prereq pre3 = new Prereq(new PrereqId("CS4", "CS2"));
		Prereq pre4 = new Prereq(new PrereqId("CS4", "CS3"));
		prereqDao.saveOrUpdate(pre1);
		prereqDao.saveOrUpdate(pre2);
		prereqDao.saveOrUpdate(pre3);
		prereqDao.saveOrUpdate(pre4);
	}
	
	@Test
	public void getAll(){
		List<Prereq> all = prereqDao.findAll();
		for(Prereq pre : all){
			Course cour = courseDao.getById(pre.getId().getCourseId());
			Course cPre = courseDao.getById(pre.getId().getPrereqId());
			System.out.print("Curso ");
			System.out.println(cour.getTitle());
			System.out.print("Prereq ");
			System.out.println(cPre.getTitle());
		}
	}
	
	@Test
	public void getById(){
		Prereq pre = prereqDao.getById(new PrereqId("CS3", "CS2"));
		Course cour = courseDao.getById(pre.getId().getCourseId());
		Course cPre = courseDao.getById(pre.getId().getPrereqId());
		System.out.print("Curso ");
		System.out.println(cour.getTitle());
		System.out.print("Prereq ");
		System.out.println(cPre.getTitle());
	}
	
	@Test
	public void getPrereq(){
		List<Prereq> all = prereqDao.getPrereq("CS4");
		for(Prereq pre : all){			
			Course cPre = courseDao.getById(pre.getId().getPrereqId());
			System.out.print("Prereq ");
			System.out.println(cPre.getTitle());
		}
	}
	
}
