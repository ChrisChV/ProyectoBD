package com.project.business.manager;

import java.math.BigDecimal;
import java.util.List;

import com.project.business.dto.InstructorDTO;
import com.project.persistence.entity.Instructor;

public interface InstructorManager {
	
	public Instructor mappingDTO(InstructorDTO ins);
	
	public InstructorDTO mappingDTO(Instructor ins);
	
	public List<InstructorDTO> mappingList(List<Instructor> lins);
	
	public List<InstructorDTO> getAll();
	
	public InstructorDTO getByIndex(int index);
	
	public int verificarIndex(int index);
	
	public int getLastIndex();
	
	public InstructorDTO getById(String instructorId);
	
	public List<InstructorDTO> getByDept(String deptName);
	
	public String insert(String insId, String deptId, String name, BigDecimal salary);
	
	public String delete(String insId);
	
	public String update(Instructor ins);
	
}
