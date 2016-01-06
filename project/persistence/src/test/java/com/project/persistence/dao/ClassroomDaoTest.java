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

import com.project.persistence.dao.ClassroomDao;
import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;

@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class ClassroomDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ClassroomDao classroomDao;
	
	@Before
	public void setUp(){
		classroomDao.insert("CHRIS", "123", (short) 120);
		classroomDao.insert("ALE", "321", (short) 500);
	}
	
	@Test
	public void getAll(){
		List<Classroom> all = classroomDao.findAll();
		for(Classroom cla : all){
			System.out.print("Name ");
			System.out.println(cla.getId().getBuilding());
			System.out.print("Number ");
			System.out.println(cla.getId().getRoomNumber());
			System.out.print("Capacity ");
			System.out.println(cla.getCapacity());
		}	
	}
	
	@Test
	public void getById(){
		Classroom cla = classroomDao.getById(new ClassroomId("CHRIS", "123"));
		System.out.print("Name ");
		System.out.println(cla.getId().getBuilding());
		System.out.print("Number ");
		System.out.println(cla.getId().getRoomNumber());
		System.out.print("Capacity ");
		System.out.println(cla.getCapacity());
	}
	
	
	@Test
	public void delete(){
		classroomDao.delete(new ClassroomId("CHRIS", "123"));
		List<Classroom> all = classroomDao.findAll();
		for(Classroom cla : all){
			System.out.print("Name ");
			System.out.println(cla.getId().getBuilding());
			System.out.print("Number ");
			System.out.println(cla.getId().getRoomNumber());
			System.out.print("Capacity ");
			System.out.println(cla.getCapacity());
		}	
	}
	
	@Test
	public void update(){
		Classroom cla1 = classroomDao.getById(new ClassroomId("ALE", "321"));
		cla1.setCapacity((short) 1000);
		classroomDao.update(cla1);
		List<Classroom> all = classroomDao.findAll();
		for(Classroom cla : all){
			System.out.print("Name ");
			System.out.println(cla.getId().getBuilding());
			System.out.print("Number ");
			System.out.println(cla.getId().getRoomNumber());
			System.out.print("Capacity ");
			System.out.println(cla.getCapacity());
		}
	}
	
	
	
	
	
	
	
}
