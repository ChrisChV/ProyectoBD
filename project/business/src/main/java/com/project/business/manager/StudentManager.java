package com.project.business.manager;

import java.util.List;

import com.project.business.dto.StudentDTO;
import com.project.persistence.entity.Student;

public interface StudentManager {

	public Student mappingDTO(StudentDTO st);
	
	public StudentDTO mappingDTO(Student st);
	
	public List<StudentDTO> mappingList(List<Student> lst);
	
	public List<StudentDTO> getAll();
	
	public StudentDTO getById(String studentId);
	
	public List<StudentDTO> getByDept(String deptName);
	
	public String insert(String id, String deptId, String name, Short totCred);
	
	public String delete(String id);
	
	public String update(Student st);
	
}
