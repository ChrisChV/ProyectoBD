package com.project.business.manager;

import java.math.BigDecimal;
import java.util.List;

import com.project.business.dto.DepartmentDTO;
import com.project.persistence.entity.Department;

public interface DepartmentManager {

	public Department mappingDTO(DepartmentDTO dept);
	
	public DepartmentDTO mappingDTO(Department dept);
	
	public List<DepartmentDTO> mappingList(List<Department> ldept);
	
	public DepartmentDTO getById(String deptName);
	
	public List<DepartmentDTO> getAll();
	
	public DepartmentDTO getByIndex(int index);
	
	public int verificarIndex(int index);
	
	public int getLastIndex();
	
	public String insert(String depName, String building, BigDecimal budget);
	
	public String delete(String depName);
	
	public String update(Department dept);
	
	public List<DepartmentDTO> getTable(int start, int length, String s);
	
}
