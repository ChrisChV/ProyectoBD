package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Takes;
import com.project.persistence.entity.TakesId;

public interface TakesDao extends GenericDao<Takes> {

	public Takes getById(TakesId id);
	
	public String insert(String studentId, SectionId sectionId, String grade);
	
	public String delete(TakesId takeId);
	
	public String update(Takes take);
	
	public List<Takes> getByStudent(String studentId);
	
	public List<Takes> getBySection(SectionId secId);
	
}
