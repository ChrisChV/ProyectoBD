package com.project.persistence.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Instructor;


@Repository("instructorDao")
public class InstructorDaoImpl extends GenericDaoImpl<Instructor> implements InstructorDao {

	private InstructorDaoImpl(){
		super(Instructor.class);
	}

	@Transactional
	public Instructor getById(String id) {
		return (Instructor) getCurrentSession().get(Instructor.class,id);
	}
}
