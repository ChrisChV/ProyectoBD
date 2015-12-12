package com.project.persistence.dao;

import com.project.persistence.entity.Instructor;

public interface InstructorDao extends GenericDao<Instructor> {
	
	public Instructor getById(String id);
	
}
