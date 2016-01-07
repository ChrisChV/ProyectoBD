package com.project.business.manager;

import java.util.List;

import com.project.business.dto.CourseDTO;
import com.project.business.dto.InstructorDTO;
import com.project.persistence.entity.Course;
import com.project.persistence.entity.Prereq;
import com.project.persistence.entity.PrereqId;

public interface PrereqManager {

	public Course mappingDTO(CourseDTO course);
	
	public CourseDTO mappingDTOC(Prereq course);
	
	public CourseDTO mappingDTOP(Prereq course);
	
	public List<CourseDTO> mappingListC(List<Prereq> lcour);
	
	public List<CourseDTO> mappingListP(List<Prereq> lcour);
	
	public List<Prereq> getAll();
	
	public Prereq getById(PrereqId id);
	
	public String insert(String courseId, String prereqId);
	
	public String delete(PrereqId id);
	
	public List<CourseDTO> getPrereq(String courseId);
	
}
