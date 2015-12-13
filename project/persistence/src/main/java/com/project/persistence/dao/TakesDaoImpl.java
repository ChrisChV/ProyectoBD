package com.project.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Takes;
import com.project.persistence.entity.TakesId;

@Repository("takesDao")
public class TakesDaoImpl extends GenericDaoImpl<Takes> implements TakesDao {

	private TakesDaoImpl(){
		super(Takes.class);
	}

	@Transactional
	public Takes getById(TakesId id) {
		return (Takes) getCurrentSession().get(Takes.class, id);
	}
	
	@Transactional
	public void delete(Takes take){
		getCurrentSession().delete(take);
	}

	@Transactional
	public List<Takes> getByStudent(String studentId) {
		Criterion criterion = Restrictions.eq("id.id", studentId);
		return findByCriteria(criterion);
	}
		
}
