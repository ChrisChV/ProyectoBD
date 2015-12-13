package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.Prereq;
import com.project.persistence.entity.PrereqId;

public interface PrereqDao extends GenericDao<Prereq> {

	public Prereq getById(PrereqId id);
	
	public void delete(Prereq pre);
	
	public List<Prereq> getPrereq(String courseId);
	
}
