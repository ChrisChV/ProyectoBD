package com.project.business.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.CourseDTO;
import com.project.business.dto.SectionDTO;
import com.project.persistence.dao.CourseDao;
import com.project.persistence.dao.DepartamentDao;
import com.project.persistence.dao.PrereqDao;
import com.project.persistence.dao.SectionDao;
import com.project.persistence.dao.StudentDao;
import com.project.persistence.dao.TakesDao;
import com.project.persistence.dao.TeachesDao;
import com.project.persistence.entity.Course;
import com.project.persistence.entity.Department;
import com.project.persistence.entity.Prereq;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.Takes;
import com.project.persistence.entity.Teaches;

@Service("course")
public class CourseManagerImpl implements CourseManager {

	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private DepartamentDao departmentDao;
	
	@Autowired
	private PrereqDao prereqDao;
	
	@Autowired
	private SectionDao sectionDao;
	
	@Autowired
	private TeachesDao teachesDao;
	
	@Autowired
	private TakesDao takesDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public Course mappingDTO(CourseDTO course) {
		Department dept = departmentDao.findById(course.getDptName());
		return new Course(course.getId(),dept, course.getTitle(),(byte) course.getCredits());
	}

	@Override
	public CourseDTO mappingDTO(Course course) {
		return new CourseDTO(course.getCourseId(), course.getDepartment().getDeptName()
				, course.getTitle(), course.getCredits());
	}

	@Override
	public List<CourseDTO> mappingList(List<Course> lcour) {
		List<CourseDTO> res = new ArrayList<CourseDTO>();
		for(Course cour : lcour){
			res.add(mappingDTO(cour));
		}
		return res;
	}

	@Override
	public List<CourseDTO> getAll() {
		return mappingList(courseDao.findAll());
	}

	@Override
	public CourseDTO getById(String courseId) {
		return mappingDTO(courseDao.getById(courseId));
	}

	@Override
	public String insert(String courseId, String departmentId, String title, byte credits) {
		return courseDao.insert(courseId, departmentId, title, credits);
	}

	@Override
	public String delete(String courseId) {
		List<Prereq> lprereq = prereqDao.getPrereq(courseId);
		for(Prereq pre : lprereq){
			prereqDao.delete(pre.getId());
		}
		List<Section> lsec = sectionDao.getByCourse(courseId);
		for(Section sec : lsec){
			List<Teaches> ltea = teachesDao.getBySection(sec.getId());
			for(Teaches tea : ltea){
				teachesDao.delete(tea.getId());
			}
			List<Takes> ltake = takesDao.getBySection(sec.getId());
			for(Takes take : ltake){
				takesDao.delete(take.getId());
			}
			sectionDao.delete(sec.getId());
		}
		return courseDao.delete(courseId);
	}

	@Override
	public String update(Course course) {
		return courseDao.update(course);
	}

	@Override
	public CourseDTO getByIndex(int index) {
		return mappingDTO(courseDao.getByIndex(index));
	}

	@Override
	public int verificarIndex(int index) {
		return courseDao.verificarIndex(index);
	}

	@Override
	public int getLastIndex() {
		return courseDao.getLastIndex();
	}

	@Override
	public List<CourseDTO> getTable(int start, int length, String s) {
		return mappingList(courseDao.getTable(start, length, s));
	}

	@Override
	public List<Course> getAprobados(String studentId) {
		List<Takes> sec = takesDao.getByStudent(studentId);
		List<Takes> app = new ArrayList<Takes>();
		for(Takes t : sec){
			if(!t.getGrade().equals("F")) app.add(t);
		}
		List<Course> res = new ArrayList<Course>();
		for(Takes t : app){
			res.add(courseDao.getById(t.getId().getCourseId()));
		}
		return res;
	}

	
	@Override
	public List<Course> getSinRequisito(String dptName) {
		List<Prereq> pre = prereqDao.findAll();
		List<Course> cour = courseDao.findAll();
		List<Course> sin = new ArrayList<Course>();
		List<Course> res = new ArrayList<Course>();
		for(Course c : cour){
			boolean flag = true;
			for(Prereq p : pre){
				if(p.getId().getCourseId().equals(c.getCourseId())){
					flag = false;
					break;
				}
			}
			if(flag) sin.add(c);
		}
		for(Course c : sin){
			if(c.getDepartment().getDeptName().equals(dptName)) res.add(c);
		}
		return res;
	}

	@Override
	public List<CourseDTO> puedeLlevar(String studentId) {
		List<Course> aprobados = getAprobados(studentId);
		String dpt = studentDao.getById(studentId).getDepartment().getDeptName();
		List<Course> sinPre = getSinRequisito(dpt);
		List<Course> allDpt = courseDao.getByDept(dpt);
		List<Course> res = new ArrayList<Course>();
		for(Course c : sinPre){
			boolean flag = true;
			for(Course cc : aprobados){
				if(cc.getCourseId().equals(c.getCourseId())){
					flag = false;
					break;
				}
			}
			if(flag) res.add(c);
		}
		List<Course> posibles = new ArrayList<Course>();
		for(Course c : allDpt){
			boolean flag = true;
			for(Course cc : sinPre){
				if(cc.getCourseId().equals(c.getCourseId())){
					flag = false;
					break;
				}
			}
			if(flag) posibles.add(c);
			flag = true;
			for(Course cc : aprobados){
				if(cc.getCourseId().equals(c.getCourseId())){
					flag = false;
					break;
				}
			}
			if(flag) posibles.add(c);
		}
		for(Course c : posibles){
			if(llevar(c, aprobados)){
				res.add(c);
			}
		}
		return mappingList(res);
	}

	@Override
	public boolean llevar(Course course, List<Course> aprobados) {
		List<Prereq> pre = prereqDao.getPrereq(course.getCourseId());
		int cont = 0;
		for(Prereq p : pre){
			for(Course c : aprobados){
				if(c.getCourseId().equals(p.getId().getPrereqId())){
					cont++;
					break;
				}
			}
		}
		if(cont == pre.size()) return true;
		return false;
	}
}
