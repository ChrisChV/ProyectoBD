package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;

public interface ClassroomDao extends GenericDao<Classroom> {
	
	public Classroom getById(ClassroomId id);

	public String insert(String building, String roomN, short capacity);
	
	public String delete(ClassroomId classId);
	
	public String update(Classroom classA);

	public List<Classroom> getTable(int iDisplayStart, int iDisplayLength, String s);
	
}
