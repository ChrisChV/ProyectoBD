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

	@Transactional
	public String insert(String id, String deptId, String name, Short totCred) {
		if(id == null){
			String m = "La id del estudiante no puede ser null";
			System.out.println(m + " en INSERT FROM STUDENT");
			return m;
		}
		if(name == null){
			String m = "El nombre del estudiante no puede ser null";
			System.out.println(m + " en INSERT FROM STUDENT");
			return m;
		}
		Student st = getById(id);
		if(st != null){
			String m = "El estudiante que esta intentando ingresar ya existe";
			System.out.println(m + " en INSERT FROM STUDENT");
			return m;
		}
		Department dpt = null;
		if(deptId != null){
			dpt = (Department) getCurrentSession().get(Department.class, deptId);
		}
		st = new Student(id, dpt, name, totCred);
		saveOrUpdate(st);
		String m = "El estudiante ha sido ingresado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String delete(String id) {
		if(id == null){
			String m = "La id del estudiante no debe ser null";
			System.out.println(m + " en DELETE FROM STUDENT");
			return m;
		}
		Student st = getById(id);
		if(st == null){
			String m = "EL estudiante que intenta eliminar no existe";
			System.out.println(m + " en DELETE FROM STUDENT");
			return m;
		}
		getCurrentSession().delete(st);
		String m = "EL estudiante se elimino correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String update(Student st) {
		if(st == null){
			String m = "EL estudiante no debe ser null";
			System.out.println(m + " en UPDATE FROM STUDENT");
			return m;
		}
		Student temp = getById(st.getId());
		if(temp == null){
			String m = "EL estudiante que intenta actualizar no existe";
			System.out.println(m + " en UPDATE FROM STUDENT");
			return m;
		}
		updateSession(st);
		String m = "EL estudiante se actualizo correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public List<Student> getByDept(String deptName) {
		Criterion criterion = Restrictions.eq("department.deptName", deptName);
		return findByCriteria(criterion);
	}
	
	@Transactional
	public List<Student> getTable(int iDisplayStart, int iDisplayLength, String s) {
		if(s == ""){
			Query quer = getCurrentSession().createQuery("from " + entity.getSimpleName());
			quer.setFirstResult(iDisplayStart);
			quer.setMaxResults(iDisplayLength);
			return (List<Student>) quer.list();
		}
		String ss = "%" + s + "%";
		Criterion criterion1 = Restrictions.like("id", ss);
		Criterion criterion2 = Restrictions.like("department.deptName", ss);
		Criterion criterion3 = Restrictions.like("name", ss);
		//int t = Integer.parseInt(s);
		//Criterion criterion3 = Restrictions.like("budget", new BigDecimal(t));
		Criteria criteria = getCurrentSession().createCriteria(entity);
		criteria.add(Restrictions.or(criterion1, criterion2,criterion3));
		criteria.setFirstResult(iDisplayStart);
		criteria.setMaxResults(iDisplayLength);
		return (List<Student>) criteria.list();
	}

	
}
