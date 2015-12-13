package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;

public interface SectionDao extends GenericDao<Section> {

	public Section getById(SectionId id);
	
	public List<Section> getByCourse(String courseId);
	
}
