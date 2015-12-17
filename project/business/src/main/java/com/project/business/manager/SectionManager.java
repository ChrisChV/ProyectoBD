package com.project.business.manager;

import java.util.List;

import com.project.business.dto.SectionDTO;
import com.project.persistence.entity.ClassroomId;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;

public interface SectionManager {

	public Section mappingDTO(SectionDTO sec);
	
	public SectionDTO mappingDTO(Section sec);
	
	public List<SectionDTO> mappingList(List<Section> lsec);
	
	public List<SectionDTO> getAll();
	
	public SectionDTO getById(SectionId secId);
	
	public List<SectionDTO> getByCourse(String courseId);
	
	public List<SectionDTO> getByClassroom(ClassroomId classId);
	
	public String insert(String courseId, String secId, String semester, short year
			, ClassroomId classId, String timeSlotId);
	
	public String delete(SectionId sectionId);
	
	public String update(Section section);
	
}
