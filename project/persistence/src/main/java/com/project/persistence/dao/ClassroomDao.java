package com.project.persistence.dao;

import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;

public interface ClassroomDao extends GenericDao<Classroom> {
	
	public Classroom getById(ClassroomId id);

	public String insert(String building, String roomN, short capacity);
	
	public String delete(ClassroomId classId);
	
	public String update(Classroom classA);

	
	
}
