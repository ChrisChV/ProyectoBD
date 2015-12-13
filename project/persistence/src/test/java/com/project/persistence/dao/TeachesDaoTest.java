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
import com.project.persistence.entity.Instructor;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Teaches;
import com.project.persistence.entity.TeachesId;
import com.project.persistence.entity.TimeSlot;
import com.project.persistence.entity.TimeSlotId;

@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class TeachesDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private ClassroomDao classroomDao;
	
	@Autowired
	private SectionDao sectionDao;
	
	@Autowired
	private TimeSlotDao timeSlotDao;
	
	@Autowired
	private TeachesDao teachesDao;
	
	@Autowired
	private InstructorDao instructorDao;
	
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
		Instructor ins = new Instructor("C4444","Chris");
		instructorDao.saveOrUpdate(ins);
		Teaches tea = new Teaches(new TeachesId(ins.getId(), sec1.getId().getCourseId(), sec1.getId().getSecId()
				, sec1.getId().getSemester(), sec1.getId().getYear()));
		Teaches tea2 = new Teaches(new TeachesId(ins.getId(), sec2.getId().getCourseId(), sec2.getId().getSecId()
				, sec2.getId().getSemester(), sec2.getId().getYear()));
		teachesDao.saveOrUpdate(tea);
		teachesDao.saveOrUpdate(tea2);
	}
	
	@Test
	public void getAll(){
		List<Teaches> all = teachesDao.findAll();
		for(Teaches tea : all){
			Section sec = sectionDao.getById(new SectionId(tea.getId().getCourseId(), tea.getId().getSecId(), tea.getId().getSemester(), tea.getId().getYear()));
			Instructor ins = instructorDao.getById(tea.getId().getId());
			System.out.print("Nombre Instructor ");
			System.out.println(ins.getName());
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
		Teaches tea = teachesDao.getById(new TeachesId("C4444", "CSSSS", "1", "1", (short)2015));
		Section sec = sectionDao.getById(new SectionId(tea.getId().getCourseId(), tea.getId().getSecId(), tea.getId().getSemester(), tea.getId().getYear()));
		Instructor ins = instructorDao.getById(tea.getId().getId());
		System.out.print("Nombre Instructor ");
		System.out.println(ins.getName());
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
	public void getByInstructor(){
		List<Teaches> all = teachesDao.getByInstructor("C4444");
		for(Teaches tea : all){
			Section sec = sectionDao.getById(new SectionId(tea.getId().getCourseId(), tea.getId().getSecId(), tea.getId().getSemester(), tea.getId().getYear()));
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
	public void getBySection(){
		List<Teaches> all = teachesDao.getBySection(new SectionId("CSSSS", "1", "1", (short)2015));
		for(Teaches tea : all){
			Instructor ins = instructorDao.getById(tea.getId().getId());
			System.out.print("Nombre Instructor ");
			System.out.println(ins.getName());
		}
	}
	
}
