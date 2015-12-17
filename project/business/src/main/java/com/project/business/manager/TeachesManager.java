package com.project.business.manager;

import java.util.List;

import com.project.business.dto.InstructorDTO;
import com.project.business.dto.SectionDTO;
import com.project.persistence.entity.Instructor;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Teaches;
import com.project.persistence.entity.TeachesId;

public interface TeachesManager {

	public Section mappingDTO(SectionDTO sec);
	
	public SectionDTO mappingDTOSec(Teaches tea);
	
	public List<SectionDTO> mappingListSec(List<Teaches> ltea);
	
	public Instructor mappingDTO(InstructorDTO ins);
	
	public InstructorDTO mappingDTOI(Teaches ins);
	
	public List<InstructorDTO> mappingListI(List<Teaches> ltea);
	
	public List<Teaches> getAll();
	
	public Teaches getById(TeachesId id);
	
	public String insert(String instructorId, SectionId sectionId);
	
	public String delete(TeachesId teachesId);
	
	public List<SectionDTO> getByInstructor(String instructorId);
	
	public List<InstructorDTO> getBySection(SectionId sectionId);

}
