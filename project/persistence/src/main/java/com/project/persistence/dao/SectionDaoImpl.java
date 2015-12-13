package com.project.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Student;

@Repository("sectionDao")
public class SectionDaoImpl extends GenericDaoImpl<Section> implements SectionDao {

	private SectionDaoImpl(){
		super(Section.class);
	}

	@Transactional
	public Section getById(SectionId id) {
		return (Section) getCurrentSession().get(Section.class, id);
	}

	@Transactional
	public List<Section> getByCourse(String courseId) {
		Criterion criterion = Restrictions.eq("id.courseId", courseId);
		return findByCriteria(criterion);
	}

	
	
	
}
