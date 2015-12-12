package com.project.persistence.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Course;

@Repository("courseDao")
public class CourseDaoImpl extends GenericDaoImpl<Course> implements CourseDao {
	
	private CourseDaoImpl(){
		super(Course.class);
	}

	@Transactional
	public Course getById(String id) {
		return (Course) getCurrentSession().get(Course.class, id);
	}
	
	
	
}
