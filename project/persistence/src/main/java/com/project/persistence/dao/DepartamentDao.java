package com.project.persistence.dao;

import com.project.persistence.entity.Department;

public interface DepartamentDao extends GenericDao<Department> {

	public Department findById(String id);
	
}
