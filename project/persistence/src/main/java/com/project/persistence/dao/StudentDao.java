package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.Student;

public interface StudentDao extends GenericDao<Student> {
	
	public Student getById(String id);
	
	public List<Student> getByDept(String deptName);
	
	public String insert(String id, String deptId, String name, Short totCred);
	
	public String delete(String id);
	
	public String update(Student st);
	
}
