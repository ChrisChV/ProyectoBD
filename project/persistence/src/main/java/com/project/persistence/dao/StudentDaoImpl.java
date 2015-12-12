package com.project.persistence.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Student;

@Repository("studentDao")
public class StudentDaoImpl extends GenericDaoImpl<Student> implements StudentDao {
	
	protected StudentDaoImpl(){
		super(Student.class);
	}

	@Transactional
	public Student getById(String id) {
		return (Student) getCurrentSession().get(Student.class, id);
	}
	
}
