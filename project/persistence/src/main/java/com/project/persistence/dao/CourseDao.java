package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.Course;
import com.project.persistence.entity.Department;

public interface CourseDao extends GenericDao<Course> {

	public Course getById(String id);
	
	public List<Course> getByDept(String deptName);

	public String insert(String courseId, String departmentId, String title, byte credits);
	
	public String delete(String courseId);
	
	public String update(Course course);
	
	public List<Course> getTable(int iDisplayStart, int iDisplayLength, String s);
	
	public List<Course> getByDpt(String dptName);
	
}
