package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Teaches;
import com.project.persistence.entity.TeachesId;

public interface TeachesDao extends GenericDao<Teaches> {

	public Teaches getById(TeachesId id);
	
	public List<Teaches> getByInstructor(String instructorId);
	
	public List<Teaches> getBySection(SectionId sectionId);
	
}
