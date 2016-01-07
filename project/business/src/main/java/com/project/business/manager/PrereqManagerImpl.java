package com.project.business.manager;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.IconUIResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.CourseDTO;
import com.project.persistence.dao.CourseDao;
import com.project.persistence.dao.DepartamentDao;
import com.project.persistence.dao.PrereqDao;
import com.project.persistence.entity.Course;
import com.project.persistence.entity.Department;
import com.project.persistence.entity.Prereq;
import com.project.persistence.entity.PrereqId;

@Service("prereq")
public class PrereqManagerImpl implements PrereqManager{

	@Autowired
	private PrereqDao prereqDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private DepartamentDao departmentDao;
	
	@Override
	public Course mappingDTO(CourseDTO course) {
		Department dept = departmentDao.findById(course.getDptName());
		return new Course(course.getId(),dept, course.getTitle(),(byte) course.getCredits());
	}

	@Override
	public CourseDTO mappingDTOC(Prereq course) {
		Course cour = courseDao.getById(course.getId().getCourseId());
		return new CourseDTO(cour.getCourseId(), cour.getDepartment().getDeptName()
				, cour.getTitle(), cour.getCredits());
	}

	@Override
	public CourseDTO mappingDTOP(Prereq course) {
		Course cour = courseDao.getById(course.getId().getPrereqId());
		return new CourseDTO(cour.getCourseId(), cour.getDepartment().getDeptName()
				, cour.getTitle(), cour.getCredits());
	}

	@Override
	public List<CourseDTO> mappingListC(List<Prereq> lcour) {
		List<CourseDTO> res = new ArrayList<CourseDTO>();
		for(Prereq pre : lcour){
			res.add(mappingDTOC(pre));
		}
		return res;
	}

	@Override
	public List<CourseDTO> mappingListP(List<Prereq> lcour) {
		List<CourseDTO> res = new ArrayList<CourseDTO>();
		for(Prereq pre : lcour){
			res.add(mappingDTOP(pre));
		}
		return res;
	}

	@Override
	public List<Prereq> getAll() {
		return prereqDao.findAll();
	}

	@Override
	public Prereq getById(PrereqId id) {
		return prereqDao.getById(id);
	}

	@Override
	public String insert(String courseId, String prereqId) {
		return prereqDao.insert(courseId, prereqId);
	}

	@Override
	public String delete(PrereqId id) {
		return prereqDao.delete(id);
	}

	@Override
	public List<CourseDTO> getPrereq(String courseId) {
		return mappingListP(prereqDao.getPrereq(courseId));
	}

	@Override
	public List<CourseDTO> getPrereqByDepartment(String courseId) {
		List<CourseDTO> cour = mappingListC(prereqDao.findAll());
		Course cc = courseDao.getById(courseId);
		List<CourseDTO> res = new ArrayList<CourseDTO>();
		for(CourseDTO dto : cour){
			if(cc.getDepartment().getDeptName().equals(dto.getDptName())){
			 res.add(dto);
			}
		}
		return res;
	}

}
