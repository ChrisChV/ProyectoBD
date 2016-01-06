package com.project.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Course;
import com.project.persistence.entity.Department;

@Repository("courseDao")
public class CourseDaoImpl extends GenericDaoImpl<Course> implements CourseDao {
	
	private CourseDaoImpl(){
		super(Course.class);
	}

	@Transactional
	public Course getById(String id) {
		return (Course) getCurrentSession().get(Course.class, id);
	}

	@Transactional
	public String insert(String courseId, String departmentId, String title, byte credits) {
		if(courseId == null){
			String m = "El id de curso no debe ser null";
			System.out.println(m + " en INSERT FROM COURSE");
			return m;
		}
		Course temp = getById(courseId);
		if(temp != null){
			String m = "El curso que esta intentando ingresar ya existe";
			System.out.println(m + " en INSERT FROM COURSE");
			return m;
		}
		if(departmentId != null){
			Department dep = (Department) getCurrentSession().get(Department.class, departmentId);
			temp = new Course(courseId, dep, title, credits);
			saveOrUpdate(temp);			
		}
		else{
			temp = new Course(courseId);
			temp.setTitle(title);
			temp.setCredits(credits);
			saveOrUpdate(temp);
		}
		String m = "El curso a sido insertado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String delete(String courseId) {
		if(courseId == null){
			String m = "El id no puede ser null";
			System.out.println(m + " en DELETE FROM COURSE");
			return m;
		}
		Course cour = getById(courseId);
		if(cour == null){
			String m = "El Curso que esta intentando eliminar no existe";
			System.out.println(m + " en DELETE FROM COURSE");
			return m;
		}
		getCurrentSession().delete(cour);
		String m = "El Curso a sido eliminado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String update(Course course) {
		if(course == null){
			String m = "El curso no puede ser null";
			System.out.println(m + " en UPDATE FROM COURSE");
			return m;
		}
		Course cour = getById(course.getCourseId());
		if(cour == null){
			String m = "El curso que esta intentando actualizar no existe";
			System.out.println(m + " en UPDATE FROM COURSE");
			return m;
		}
		saveOrUpdate(course);
		String m = "EL curso se a actualizado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public List<Course> getByDept(String deptName) {
		Criterion criterion = Restrictions.eq("department.deptName", deptName);
		return findByCriteria(criterion);
	}
	
	@Transactional
	public List<Course> getTable(int iDisplayStart, int iDisplayLength, String s) {
		if(s == ""){
			Query quer = getCurrentSession().createQuery("from " + entity.getSimpleName());
			quer.setFirstResult(iDisplayStart);
			quer.setMaxResults(iDisplayLength);
			return (List<Course>) quer.list();
		}
		String ss = "%" + s + "%";
		Criterion criterion1 = Restrictions.like("courseId", ss);
		Criterion criterion2 = Restrictions.like("department.deptName", ss);
		Criterion criterion3 = Restrictions.like("title", ss);
		//int t = Integer.parseInt(s);
		//Criterion criterion3 = Restrictions.like("budget", new BigDecimal(t));
		Criteria criteria = getCurrentSession().createCriteria(entity);
		criteria.add(Restrictions.or(criterion1, criterion2,criterion3));
		criteria.setFirstResult(iDisplayStart);
		criteria.setMaxResults(iDisplayLength);
		return (List<Course>) criteria.list();
	}
}
