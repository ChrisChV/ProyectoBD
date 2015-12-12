package com.project.persistence.dao;

import com.project.persistence.entity.Course;

public interface CourseDao extends GenericDao<Course> {

	public Course getById(String id);
	
}
