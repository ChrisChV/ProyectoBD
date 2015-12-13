package com.project.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Prereq;
import com.project.persistence.entity.PrereqId;

@Repository("prereqDao")
public class PrereqDaoImpl extends GenericDaoImpl<Prereq> implements PrereqDao {

	private PrereqDaoImpl(){
		super(Prereq.class);
	}
	
	@Transactional
	public Prereq getById(PrereqId id){
		return (Prereq) getCurrentSession().get(Prereq.class, id);
	}
	
	@Transactional
	public void delete(Prereq pre){
		getCurrentSession().delete(pre);
	}

	@Transactional
	public List<Prereq> getPrereq(String courseId) {
		Criterion criterion = Restrictions.eq("id.courseId", courseId);
		return findByCriteria(criterion);
	}
	
	
	
}
