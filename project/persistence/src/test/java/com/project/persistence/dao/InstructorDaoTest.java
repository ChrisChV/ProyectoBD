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

import com.project.persistence.dao.InstructorDao;
import com.project.persistence.entity.Instructor;

@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class InstructorDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private InstructorDao instDao;
	
	@Before
	public void setUp(){
		Instructor ins = new Instructor("C4444","Chris");
		instDao.saveOrUpdate(ins);
	}
	
	@Test
	public void getAll(){
		List<Instructor> all = instDao.findAll();
		for(Instructor ins : all){
			System.out.print("ID ");
			System.out.println(ins.getId());
			System.out.print("Nombre ");
			System.out.println(ins.getName());
		}
	}
	
	@Test
	public void findById(){
		Instructor ins = instDao.getById("C4444");
		System.out.print("ID ");
		System.out.println(ins.getId());
		System.out.print("Nombre ");
		System.out.println(ins.getName());
		
	}
	
}
