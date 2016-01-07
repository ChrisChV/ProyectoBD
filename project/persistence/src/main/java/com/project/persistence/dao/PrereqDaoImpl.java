package com.project.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Course;
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
	public List<Prereq> getPrereq(String courseId) {
		Criterion criterion = Restrictions.eq("id.courseId", courseId);
		return findByCriteria(criterion);
	}

	@Transactional
	public String insert(String courseId, String prereqId) {
		if(courseId == null){
			String m = "La id del curso no puede ser nula";
			System.out.println(m + " en INSERT FROM PREREQ");
			return m;
		}
		if(prereqId == null){
			String m = "La id del prerequisito no puede ser nula";
			System.out.println(m + " en INSERT FROM PREREQ");
			return m;
		}
		Prereq pre = getById(new PrereqId(courseId, prereqId));
		if(pre != null){
			String m = "EL prerequisto ya existe";
			System.out.println(m + " en INSERT FROM PREREQ");
			return m;
		}
		Course cour = (Course) getCurrentSession().get(Course.class, courseId);
		if(cour == null){
			String m = "EL curso no existe";
			System.out.println(m + " en INSERT FROM PREREQ");
			return m;
		}
		Course prec = (Course) getCurrentSession().get(Course.class, prereqId);
		if(prec == null){
			String m = "El CursoPrerequisito no existe";
			System.out.println(m + " en INSERT FROM PREREQ");
			return m;
		}
		pre = new Prereq(new PrereqId(courseId, prereqId));
		saveOrUpdate(pre);
		String m = "EL prerequisito a sido ingresado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String delete(PrereqId id) {
		if(id.getCourseId() == null){
			String m = "La id del curso no puede ser nula";
			System.out.println(m + " en DELETE FROM PREREQ");
			return m;
		}
		if(id.getPrereqId() == null){
			String m = "La id del prerequisito no puede ser nula";
			System.out.println(m + " en DELETE FROM PREREQ");
			return m;
		}
		Prereq pre = getById(id);
		if(pre == null){
			String m = "El prerequisito que intenta eliminar no existe";
			System.out.println(m + " en DELETE FROM PREREQ");
			return m;
		}
		getCurrentSession().delete(pre);
		String m = "EL prerequisito se elimino correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public List<Prereq> getCourses(String prereqId) {
		Criterion criterion = Restrictions.eq("id.prereqId", prereqId);
		return findByCriteria(criterion);
	}
}
