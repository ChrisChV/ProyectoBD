package com.project.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.dao.AdvisorDao;
import com.project.persistence.dao.InstructorDao;
import com.project.persistence.dao.StudentDao;
import com.project.persistence.entity.Advisor;
import com.project.persistence.entity.Instructor;
import com.project.persistence.entity.Student;

@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class AdvisorDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private AdvisorDao advisorDao;
	
	@Autowired
	private InstructorDao InstructorDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Before
	public void setUp(){
		Instructor ins = new Instructor("C4444","Chávez");
		Instructor ins2 = new Instructor("C4443","Xim");
		InstructorDao.saveOrUpdate(ins);
		InstructorDao.saveOrUpdate(ins2);
		Student st = new Student("C1234","Chris");
		Student st2 = new Student("A1234","Ale");
		studentDao.saveOrUpdate(st);
		studentDao.saveOrUpdate(st2);
		advisorDao.insert("C4444", "C1234");
		advisorDao.insert("C4444", "A1234");
	}
	
	@Test
	public void getAll(){
		List<Advisor> all = advisorDao.findAll();
		for(Advisor ad : all){
			System.out.print("Student Name ");
			System.out.println(ad.getStudent().getName());
			System.out.print("Instructor Name ");
			System.out.println(ad.getInstructor().getName());
		}
	}
	
	@Test
	public void getById(){
		Advisor ad = advisorDao.getById("C1234");
		System.out.print("Student Name ");
		System.out.println(ad.getStudent().getName());
		System.out.print("Instructor Name ");
		System.out.println(ad.getInstructor().getName());
	}
	
	@Test
	public void getByStudent(){
		List<Advisor> st = advisorDao.getByStudent("A1234");
		for(Advisor ad : st){
			System.out.print("Instructor Name ");
			System.out.println(ad.getInstructor().getName());
		}
	}
	
	@Test
	public void getByInstructor(){
		List<Advisor> st = advisorDao.getByInstructor("C4444");
		for(Advisor ad : st){
			System.out.print("Student Name ");
			System.out.println(ad.getStudent().getName());
		}
	}	
	
	@Test
	public void delete(){
		advisorDao.delete("A1234");
		List<Advisor> all = advisorDao.findAll();
		for(Advisor ad : all){
			System.out.print("Student Name ");
			System.out.println(ad.getStudent().getName());
			System.out.print("Instructor Name ");
			System.out.println(ad.getInstructor().getName());
		}
	}

	@Test
	public void update(){
		Advisor ad1 = advisorDao.getById("A1234");
		Instructor ins = InstructorDao.getById("C4443");
		ad1.setInstructor(ins);
		advisorDao.update(ad1);
		List<Advisor> all = advisorDao.findAll();
		for(Advisor ad : all){
			System.out.print("Student Name ");
			System.out.println(ad.getStudent().getName());
			System.out.print("Instructor Name ");
			System.out.println(ad.getInstructor().getName());
		}
	}
	
	
}
