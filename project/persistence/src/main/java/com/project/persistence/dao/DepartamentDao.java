package com.project.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import com.project.persistence.entity.Department;

public interface DepartamentDao extends GenericDao<Department> {

	public Department findById(String id);
	
	public String insert(String depName, String building, BigDecimal budget);
	
	public String delete(String depName);
	
	public String update(Department dept);
	
	public List<Department> getTable(int iDisplayStart, int iDisplayLength, String s);
	
}
