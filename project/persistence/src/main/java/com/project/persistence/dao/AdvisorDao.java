package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.Advisor;

public interface AdvisorDao extends GenericDao<Advisor> {

	public Advisor getById(String id);
	
	public String delete(String id);
	
	public List<Advisor> getByStudent(String studentId);
	
	public List<Advisor> getByInstructor(String instructorId);
	
	public String insert(String instructorId, String studentId);
	
	public String update(Advisor ad);
	
}
