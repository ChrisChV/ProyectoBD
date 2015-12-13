package com.project.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Teaches;
import com.project.persistence.entity.TeachesId;

@Repository("teachesDao")
public class TeachesDaoImpl extends GenericDaoImpl<Teaches> implements TeachesDao {

	private TeachesDaoImpl(){
		super(Teaches.class);
	}

	@Transactional
	public Teaches getById(TeachesId id) {
		return (Teaches) getCurrentSession().get(Teaches.class, id);
	}

	@Transactional
	public List<Teaches> getByInstructor(String instructorId) {
		Criterion criterion = Restrictions.eq("id.id", instructorId);
		return findByCriteria(criterion);
	}

	@Transactional
	public List<Teaches> getBySection(SectionId sectionId) {
		List<Teaches> result = new ArrayList<Teaches>();
		Criterion criterion = Restrictions.eq("id.courseId", sectionId.getCourseId());
		List<Teaches> l1 = findByCriteria(criterion);
		for(Teaches ta : l1){
			if(ta.getId().getSecId() == sectionId.getSecId() && ta.getId().getSemester() == sectionId.getSemester()
					&& ta.getId().getYear() == sectionId.getYear()){
				result.add(ta);
			}
		}
		return result;
	}
}
