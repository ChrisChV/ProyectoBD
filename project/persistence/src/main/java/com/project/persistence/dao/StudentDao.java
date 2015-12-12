package com.project.persistence.dao;

import com.project.persistence.entity.Student;

public interface StudentDao extends GenericDao<Student> {
	
	public Student getById(String id);
	
}
