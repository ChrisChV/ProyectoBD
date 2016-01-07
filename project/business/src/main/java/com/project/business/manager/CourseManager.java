package com.project.business.manager;

import java.util.List;

import com.project.business.dto.ClassroomDTO;
import com.project.business.dto.CourseDTO;
import com.project.persistence.entity.Course;

public interface CourseManager {

	public Course mappingDTO(CourseDTO course);
	
	public CourseDTO mappingDTO(Course course);
	
	public List<CourseDTO> mappingList(List<Course> lcour);
	
	public List<CourseDTO> getAll();
	
	public CourseDTO getByIndex(int index);
	
	public int verificarIndex(int index);
	
	public int getLastIndex();
	
	public CourseDTO getById(String courseId);
	
	public String insert(String courseId, String departmentId, String title, byte credits);
	
	public String delete(String courseId);
	
	public String update(Course course);
	
	public List<CourseDTO> getTable(int start, int length, String s);
	
	public List<Course> getAprobados(String studentId);
	
	public List<Course> getSinRequisito(String dptName);
	
	public List<CourseDTO> puedeLlevar(String studentId);
	
	public boolean llevar(Course course,List<Course> aprobados);
	
}
