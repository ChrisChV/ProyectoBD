package com.project.business.manager;

import java.util.List;

import com.project.business.dto.CourseDTO;
import com.project.persistence.entity.Course;

public interface CourseManager {

	public Course mappingDTO(CourseDTO course);
	
	public CourseDTO mappingDTO(Course course);
	
	public List<CourseDTO> mappingList(List<Course> lcour);
	
	public List<CourseDTO> getAll();
	
	public CourseDTO getById(String courseId);
	
	public String insert(String courseId, String departmentId, String title, byte credits);
	
	public String delete(String courseId);
	
	public String update(Course course);
	
}
