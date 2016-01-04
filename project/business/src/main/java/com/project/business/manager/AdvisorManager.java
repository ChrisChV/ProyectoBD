package com.project.business.manager;

import java.util.List;

import com.project.business.dto.AdvisorDTO;
import com.project.business.dto.InstructorDTO;
import com.project.business.dto.StudentDTO;
import com.project.persistence.entity.Advisor;
import com.project.persistence.entity.Instructor;
import com.project.persistence.entity.Student;

public interface AdvisorManager {
	
	public Student mappingDTO(StudentDTO st);
	
	public StudentDTO mappingDTOS(Advisor ad);
	
	public List<StudentDTO> mappingListS(List<Advisor> lst);
	
	public Instructor mappingDTO(InstructorDTO ins);
	
	public InstructorDTO mappingDTOI(Advisor ad);
	
	public Advisor mappingDTO(AdvisorDTO ad);
	
	public List<InstructorDTO> mappingListI(List<Advisor> lins);
	
	public Advisor getById(String id);
	
	public List<Advisor> getAll();
	
	public List<InstructorDTO> getByStudent(String studentId);
	
	public List<StudentDTO> getByInstructor(String instructorId);
	
	public String insert(String instructorId, String studentId);
	
	public String update(Advisor ad);
	
	public String delete(String id);

}
