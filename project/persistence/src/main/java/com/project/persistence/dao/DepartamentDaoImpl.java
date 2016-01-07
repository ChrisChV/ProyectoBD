package com.project.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Department;

@Repository("departametDao")
public class DepartamentDaoImpl extends GenericDaoImpl<Department> implements DepartamentDao {

	protected DepartamentDaoImpl(){
		super(Department.class);
	}

	@Transactional
	public Department findById(String id) {
		return (Department) getCurrentSession().get(Department.class, id);
	}

	@Transactional
	public String insert(String depName, String building, BigDecimal budget) {
		if(depName == null){
			String m = "El Nombre del Departamento no puede ser null";
			System.out.println(m + " en INSERT FROM DEPARTMENT");
			return m;
		}
		Department dep = findById(depName);
		if(dep != null){
			String m = "El departamento que esta intentando crear ya existe";
			System.out.println(m + " en INSERT FROM DEPARTMENT");
			return m;
		}
		dep = new Department(depName, building, budget);
		saveOrUpdate(dep);
		String m = "El departamento a sido ingresado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String delete(String depName) {
		if(depName == null){
			String m = "El Nombre del Departamento no puede ser null";
			System.out.println(m + " en DELETE FROM DEPARTMENT");
			return m;
		}
		Department dep = findById(depName);
		if(dep == null){
			String m = "El Departamento que esta intentando eliminar no existe";
			System.out.println(m + " en DELETE FROM DEPARTMENT");
			return m;
		}
		getCurrentSession().delete(dep);
		String m = "EL Departamento ha sido elimindado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String update(Department dept) {
		if(dept == null){
			String m = "El departamento no puede ser nulo";
			System.out.println(m + " en UPDATE FROM DEPARTMENT");
			return m;
		}
		System.out.println(dept.getDeptName());
		System.out.println(dept.getBuilding());
		System.out.println(dept.getBudget());
		Department temp = findById(dept.getDeptName());
		if(temp == null){
			String m = "El departamento que quiere actualizar no existe";
			System.out.println(m + " en UPDATE FROM DEPARTMENT");
			return m;
		}
		updateSession(dept);
		String m = "El departamento se a actualizado correctamente";
		System.out.println(m);
		return m;
	}
	
	@Transactional
	public List<Department> getTable(int iDisplayStart, int iDisplayLength, String s) {
		if(s == ""){
			Query quer = getCurrentSession().createQuery("from " + entity.getSimpleName());
			quer.setFirstResult(iDisplayStart);
			quer.setMaxResults(iDisplayLength);
			return (List<Department>) quer.list();
		}
		String ss = "%" + s + "%";
		Criterion criterion1 = Restrictions.like("deptName", ss);
		Criterion criterion2 = Restrictions.like("building", ss);
		//int t = Integer.parseInt(s);
		//Criterion criterion3 = Restrictions.like("budget", new BigDecimal(t));
		Criteria criteria = getCurrentSession().createCriteria(entity);
		criteria.add(Restrictions.or(criterion1, criterion2));
		criteria.setFirstResult(iDisplayStart);
		criteria.setMaxResults(iDisplayLength);
		return (List<Department>) criteria.list();
	}
	
}
