package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.Takes;
import com.project.persistence.entity.TakesId;

public interface TakesDao extends GenericDao<Takes> {

	public Takes getById(TakesId id);
	
	public void delete(Takes take);
	
	public List<Takes> getByStudent(String studentId);
	
}
