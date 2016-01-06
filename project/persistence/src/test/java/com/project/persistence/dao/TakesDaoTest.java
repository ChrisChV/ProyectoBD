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
import com.project.persistence.entity.Student;
import com.project.persistence.entity.Takes;
import com.project.persistence.entity.TakesId;
import com.project.persistence.entity.TimeSlot;
import com.project.persistence.entity.TimeSlotId;

@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class TakesDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private ClassroomDao classroomDao;
	
	@Autowired
	private SectionDao sectionDao;
	
	@Autowired
	private TimeSlotDao timeSlotDao;
	
	@Autowired
	private TakesDao takesDao;
	
	@Autowired
	private StudentDao studentDao;
	
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
		Section sec1 = new Section(new SectionId(cou.getCourseId(),"1", "1",(short)2015 ), cou);
		sec1.setClassroom(cla);
		sec1.setTimeSlotId(time.getId().getTimeSlotId());
		Section sec2 = new Section(new SectionId(cou.getCourseId(),"1", "2",(short)2015 ), cou);
		sec2.setClassroom(cla);
		sec2.setTimeSlotId(time2.getId().getTimeSlotId());
		sectionDao.saveOrUpdate(sec1);
		sectionDao.saveOrUpdate(sec2);
		Student st = new Student("C1234","Chris");
		studentDao.saveOrUpdate(st);
		takesDao.insert("C1234", sec1.getId(), "A+");
		takesDao.insert("C1234", sec2.getId(), null);
	}
	
	@Test
	public void getAll(){
		List<Takes> all = takesDao.findAll();
		for(Takes take : all){
			Section sec = take.getSection();
			Student st = take.getStudent();
			System.out.print("Nombre Student ");
			System.out.println(st.getName());
			System.out.print("Curso ");
			System.out.println(sec.getCourse().getTitle());
			System.out.print("Semestre ");
			System.out.println(sec.getId().getSemester());
			System.out.print("Clase ");
			System.out.println(sec.getClassroom().getId().getBuilding());
			System.out.print("Time ");
			System.out.println(sec.getTimeSlotId());
			System.out.print("Grade ");
			System.out.println(take.getGrade());
		}
	}
	
	@Test
	public void getById(){
		Takes take = takesDao.getById(new TakesId("C1234", "CSSSS", "1", "1", (short) 2015));
		Section sec = take.getSection();
		Student st = take.getStudent();
		System.out.print("Nombre Student ");
		System.out.println(st.getName());
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
	public void getByStudent(){
		List<Takes> all = takesDao.getByStudent("C1234");
		for(Takes take : all){
			Section sec = take.getSection();
			Student st = take.getStudent();
			System.out.print("Nombre Student ");
			System.out.println(st.getName());
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
		takesDao.delete(new TakesId("C1234", "CSSSS", "1", "1", (short)2015));
		List<Takes> all = takesDao.findAll();
		for(Takes take : all){
			Section sec = take.getSection();
			Student st = take.getStudent();
			System.out.print("Nombre Student ");
			System.out.println(st.getName());
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
		Takes take2 = takesDao.getById(new TakesId("C1234", "CSSSS", "1", "1", (short)2015));
		take2.setGrade("F");
		takesDao.update(take2);
		List<Takes> all = takesDao.findAll();
		for(Takes take : all){
			Section sec = take.getSection();
			Student st = take.getStudent();
			System.out.print("Nombre Student ");
			System.out.println(st.getName());
			System.out.print("Curso ");
			System.out.println(sec.getCourse().getTitle());
			System.out.print("Semestre ");
			System.out.println(sec.getId().getSemester());
			System.out.print("Clase ");
			System.out.println(sec.getClassroom().getId().getBuilding());
			System.out.print("Time ");
			System.out.println(sec.getTimeSlotId());
			System.out.print("Grade ");
			System.out.println(take.getGrade());
		}
	}
	
	@Test
	public void getBySection(){
		List<Takes> all = takesDao.getBySection(new SectionId("CSSSS", "1", "1", (short)2015));
		for(Takes take : all){
			Section sec = take.getSection();
			Student st = take.getStudent();
			System.out.print("Nombre Student ");
			System.out.println(st.getName());
			System.out.print("Curso ");
			System.out.println(sec.getCourse().getTitle());
			System.out.print("Semestre ");
			System.out.println(sec.getId().getSemester());
			System.out.print("Clase ");
			System.out.println(sec.getClassroom().getId().getBuilding());
			System.out.print("Time ");
			System.out.println(sec.getTimeSlotId());
			System.out.print("Grade ");
			System.out.println(take.getGrade());
		}
	}
}
