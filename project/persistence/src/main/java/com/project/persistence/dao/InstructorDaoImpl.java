package com.project.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Department;
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

	@Transactional
	public String insert(String insId, String deptId, String name, BigDecimal salary) {
		if(insId == null){
			String m = "La id del instructor no puede ser nula";
			System.out.println(m + " en INSERT FROM INSTRUCTOR");
			return m;
		}
		if(name == null){
			String m = "EL nombre del instructor no puede ser null";
			System.out.println(m + " en INSERT FROM INSTRUCTOR");
			return m;
		}
		Department dep = null;
		if(deptId != null){
			dep = (Department) getCurrentSession().get(Department.class, deptId);
		}
		Instructor ins = new Instructor(insId, dep, name, salary);
		saveOrUpdate(ins);
		String m = "El Instructor a sido ingresado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String delete(String insId) {
		if(insId == null){
			String m = "El id del istructor no puede se nula";
			System.out.println(m + " en DELETE FROM INSTRUCTOR");
			return m;
		}
		Instructor ins = getById(insId);
		if(ins == null){
			String m = "El instructor que intenta eliminar no existe";
			System.out.println(m + " en DELETE FROM INSTRUCTOR");
			return m;
		}
		getCurrentSession().delete(ins);
		String m = "El instructor a sido eliminado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String update(Instructor ins) {
		if(ins == null){
			String m = "EL instructor no puede ser null";
			System.out.println(m + " en UPDATE FROM INSTRUCTOR");
			return m;
		}
		Instructor temp = getById(ins.getId());
		if(temp == null){
			String m = "EL instructor que esta intentando eliminar no existe";
			System.out.println(m + " en UPDATE FROM INSTRUCTOR");
			return m;
		}
		saveOrUpdate(ins);
		String m = "El instructor a sido actualizado con exito";
		System.out.println(m + " en UPDATE FROM INSTRUCTOR");
		return m;
	}

	@Transactional
	public List<Instructor> getByDept(String deptName) {
		Criterion criterion = Restrictions.eq("department.deptName", deptName);
		return findByCriteria(criterion);
	}
}
