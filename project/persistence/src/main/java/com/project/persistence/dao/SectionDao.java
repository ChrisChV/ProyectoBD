package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.ClassroomId;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;

public interface SectionDao extends GenericDao<Section> {

	public Section getById(SectionId id);
	
	public String insert(String courseId, String secId, String semester, short year
			, ClassroomId classId, String timeSlotId);
	
	public String delete(SectionId sectionId);
	
	public String update(Section section);
	
	public List<Section> getByCourse(String courseId);
	
	public List<Section> getByClassroom(ClassroomId id);
	
	
}
