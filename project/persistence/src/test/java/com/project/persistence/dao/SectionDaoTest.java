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

import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;
import com.project.persistence.entity.Course;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.TimeSlot;
import com.project.persistence.entity.TimeSlotId;

@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class SectionDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private ClassroomDao classroomDao;
	
	@Autowired
	private SectionDao sectionDao;
	
	@Autowired
	private TimeSlotDao timeSlotDao;
	
	@Before
	public void setUp(){
		TimeSlot time = new TimeSlot(new TimeSlotId("M", "M", (byte) 0, (byte) 13), (byte) 4, (byte) 5);
		TimeSlot time2 = new TimeSlot(new TimeSlotId("M", "F", (byte) 0, (byte) 13), (byte) 4, (byte) 5);
		timeSlotDao.saveOrUpdate(time);
		timeSlotDao.saveOrUpdate(time2);
		Classroom cla = new Classroom(new ClassroomId("CHRIS", "123"));
		cla.setCapacity((short) 120);
		classroomDao.saveOrUpdate(cla);
		Course cou = new Course("CSSSS");
		cou.setTitle("BASE DE DATOS");
		courseDao.saveOrUpdate(cou);
		sectionDao.insert("CSSSS", "1", "1", (short) 2015, new ClassroomId("CHRIS", "123"), "M");
		sectionDao.insert("CSSSS", "1", "2", (short) 2015, new ClassroomId("CHRIS", "123"), "M");
	}
	
	@Test
	public void getAll(){
		List<Section> all = sectionDao.findAll();
		for(Section sec : all){
			System.out.print("Curso ");
			System.out.println(sec.getCourse().getTitle());
			System.out.print("Semestre ");
			System.out.println(sec.getId().getSemester());
			System.out.print("Clase ");
			System.out.println(sec.getClassroom().getId().getBuilding());
			System.out.print("Time ");
			System.out.println(sec.getTimeSlotId());
		}
	}
	
	@Test
	public void getById(){
		Section sec = sectionDao.getById(new SectionId("CSSSS", "1", "1", (short)2015));
		System.out.print("Curso ");
		System.out.println(sec.getCourse().getTitle());
		System.out.print("Semestre ");
		System.out.println(sec.getId().getSemester());
		System.out.print("Clase ");
		System.out.println(sec.getClassroom().getId().getBuilding());
		System.out.print("Time ");
		System.out.println(sec.getTimeSlotId());
	}
	
	@Test
	public void getByCurso(){
		List<Section> all = sectionDao.getByCourse("CSSSS");
		for(Section sec : all){
			System.out.print("Curso ");
			System.out.println(sec.getCourse().getTitle());
			System.out.print("Semestre ");
			System.out.println(sec.getId().getSemester());
			System.out.print("Clase ");
			System.out.println(sec.getClassroom().getId().getBuilding());
			System.out.print("Time ");
			System.out.println(sec.getTimeSlotId());
		}
	}
	
	@Test
	public void delete(){
		sectionDao.delete(new SectionId("CSSSS","1","2",(short) 2015));
		List<Section> all = sectionDao.findAll();
		for(Section sec : all){
			System.out.print("Curso ");
			System.out.println(sec.getCourse().getTitle());
			System.out.print("Semestre ");
			System.out.println(sec.getId().getSemester());
			System.out.print("Clase ");
			System.out.println(sec.getClassroom().getId().getBuilding());
			System.out.print("Time ");
			System.out.println(sec.getTimeSlotId());
		}
	}
	
	@Test
	public void update(){
		Section sec2 = sectionDao.getById(new SectionId("CSSSS","1","2",(short) 2015));
		sec2.setTimeSlotId("N");
		sectionDao.update(sec2);
		List<Section> all = sectionDao.findAll();
		for(Section sec : all){
			System.out.print("Curso ");
			System.out.println(sec.getCourse().getTitle());
			System.out.print("Semestre ");
			System.out.println(sec.getId().getSemester());
			System.out.print("Clase ");
			System.out.println(sec.getClassroom().getId().getBuilding());
			System.out.print("Time ");
			System.out.println(sec.getTimeSlotId());
		}
	}
	
	@Test
	public void getByClassroom(){
		List<Section> all = sectionDao.getByClassroom(new ClassroomId("CHRIS", "123"));
		for(Section sec : all){
			System.out.print("Curso ");
			System.out.println(sec.getCourse().getTitle());
			System.out.print("Semestre ");
			System.out.println(sec.getId().getSemester());
			System.out.print("Clase ");
			System.out.println(sec.getClassroom().getId().getBuilding());
			System.out.print("Time ");
			System.out.println(sec.getTimeSlotId());
		}
	}
	
}
