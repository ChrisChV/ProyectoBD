package com.project.persistence.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Department;

@Repository("departametDao")
public class DepartamentDaoImpl extends GenericDaoImpl<Department> implements DepartamentDao {

	protected DepartamentDaoImpl(){
		super(Department.class);
	}

	@Transactional
	public Department findById(String id) {
		return (Department) getCurrentSession().get(Department.class, id);
	}
	
}
