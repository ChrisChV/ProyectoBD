package com.project.business.manager;

import java.util.List;

import com.project.business.dto.ClassroomDTO;
import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;

public interface ClassroomManager {

	public ClassroomDTO mappingDTO(Classroom cla);
	
	public Classroom mappingDTO(ClassroomDTO claDTO);
	
	public List<ClassroomDTO> mappingList(List<Classroom> lcla);
	
	public List<ClassroomDTO> getAll();
	
	public ClassroomDTO getById(ClassroomId id);
	
	public ClassroomDTO getByIndex(int index);
	
	public String insert(String building, String roomN, short capacity);
	
	public String delete(ClassroomId classId);
	
	public String update(Classroom cla);
	
	public int verificarIndex(int index);
	
	public int getLastIndex();
	
}
