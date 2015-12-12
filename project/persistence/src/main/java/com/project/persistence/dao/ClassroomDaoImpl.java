package com.project.persistence.dao;

import org.springframework.stereotype.Repository;

import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;

@Repository("classroomDao")
public class ClassroomDaoImpl extends GenericDaoImpl<Classroom> implements ClassroomDao {
	
	private ClassroomDaoImpl(){
		super(Classroom.class);
	}

	@Override
	public Classroom getById(ClassroomId id) {
		return (Classroom) getCurrentSession().get(Classroom.class, id);
	}
	
	
	
}
