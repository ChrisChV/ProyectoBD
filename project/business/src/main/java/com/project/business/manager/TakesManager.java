package com.project.business.manager;

import java.util.List;

import com.project.business.dto.SectionDTO;
import com.project.business.dto.StudentDTO;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Student;
import com.project.persistence.entity.Takes;
import com.project.persistence.entity.TakesId;

public interface TakesManager {

	public Section mappingDTO(SectionDTO sec);
	
	public SectionDTO mappingDTOSec(Takes take);
	
	public List<SectionDTO> mappingListSec(List<Takes> lsec);
	
	public Student mappingDTO(StudentDTO st);
	
	public StudentDTO mappingDTOSt(Takes take);
	
	public List<StudentDTO> mappingListSt(List<Takes> lst);
	
	public Takes getById(TakesId id);
	
	public String insert(String studentId, SectionId sectionId, String grade);
	
	public String delete(TakesId takeId);
	
	public String update(Takes take);

	public List<SectionDTO> getByStudent(String studentId);
	
	public List<StudentDTO> getBySection(SectionId secId);
	
}
