package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.Prereq;
import com.project.persistence.entity.PrereqId;

public interface PrereqDao extends GenericDao<Prereq> {

	public Prereq getById(PrereqId id);
	
	public String insert(String courseId, String prereqId);
	
	public String delete(PrereqId id);
	
	public List<Prereq> getPrereq(String courseId);
	
	public List<Prereq> getCourses(String prereqId);
	
}
