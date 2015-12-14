package com.project.persistence.dao;

import java.math.BigDecimal;

import com.project.persistence.entity.Instructor;

public interface InstructorDao extends GenericDao<Instructor> {
	
	public Instructor getById(String id);
	
	public String insert(String insId, String deptId, String name, BigDecimal salary);
	
	public String delete(String insId);
	
	public String update(Instructor ins);
	
}
