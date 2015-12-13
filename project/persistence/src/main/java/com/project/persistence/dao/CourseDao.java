package com.project.persistence.dao;

import com.project.persistence.entity.Course;

public interface CourseDao extends GenericDao<Course> {

	public Course getById(String id);

	public String insert(String courseId, String departmentId, String title, byte credits);
	
	public String delete(String courseId);
	
	public String update(Course course);
	
}
