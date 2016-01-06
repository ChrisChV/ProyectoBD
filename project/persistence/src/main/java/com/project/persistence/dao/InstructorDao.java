package com.project.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import com.project.persistence.entity.Course;
import com.project.persistence.entity.Instructor;

public interface InstructorDao extends GenericDao<Instructor> {
	
	public Instructor getById(String id);
	
	public List<Instructor> getByDept(String deptName);
	
	public String insert(String insId, String deptId, String name, BigDecimal salary);
	
	public String delete(String insId);
	
	public String update(Instructor ins);
	
	public List<Instructor> getTable(int iDisplayStart, int iDisplayLength, String s);
	
	
}
