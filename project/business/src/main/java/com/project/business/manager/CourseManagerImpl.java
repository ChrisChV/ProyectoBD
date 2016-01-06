package com.project.business.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.CourseDTO;
import com.project.persistence.dao.CourseDao;
import com.project.persistence.dao.DepartamentDao;
import com.project.persistence.dao.PrereqDao;
import com.project.persistence.dao.SectionDao;
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
}
