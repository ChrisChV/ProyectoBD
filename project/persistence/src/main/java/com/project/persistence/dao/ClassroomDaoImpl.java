package com.project.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;
import com.project.persistence.entity.Department;

@Repository("classroomDao")
public class ClassroomDaoImpl extends GenericDaoImpl<Classroom> implements ClassroomDao {
	
	private ClassroomDaoImpl(){
		super(Classroom.class);
	}

	@Transactional
	public Classroom getById(ClassroomId id) {
		return (Classroom) getCurrentSession().get(Classroom.class, id);
	}

	@Transactional
	public String insert(String building, String roomN, short capacity) {
		if(building == null){
			String m = "El edificion no puede ser null";
			System.out.println(m + " en INSERT FROM CLASSROOM");
			return m;
		}
		if(roomN == null){
			String m = "El numero de cuarto no puede ser null";
			System.out.println(m + " en INSERT FROM CLASSROOM");
			return m;
		}
		Classroom temp = getById(new ClassroomId(building, roomN));
		if(temp != null){
			String m = "La clase que esta intentando ingresar ya existe";
			System.out.println(m + " en INSERT FROM CLASSROOM");
			return m;
		}
		Classroom cla = new Classroom(new ClassroomId(building, roomN), capacity);
		saveOrUpdate(cla);
		String m = "La clase a sido ingresada correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String delete(ClassroomId classId) {
		if(classId.getBuilding() == null){
			String m = "El building no puede ser nulo";
			System.out.println(m + " en DELETE FROM CLASSROOM");
			return m;
		}
		if(classId.getRoomNumber() == null){
			String m = "EL room number no puede ser null";
			System.out.println(m + " en DELETE FROM CLASSROOM");
			return m;
		}
		Classroom temp = getById(classId);
		if(temp == null){
			String m = "La clase que intenta eliminar no existe";
			System.out.println(m + " en DELETE FROM CLASSROOM");
			return m;
		}
		getCurrentSession().delete(temp);
		String m = "La clase ha sido eliminada correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String update(Classroom classA) {
		if(classA == null){
			String m = "La clase no debe ser nula";
			System.out.println(m + " en UPDATE FROM CLASSROOM");
			return m;
		}
		Classroom temp = getById(classA.getId());
		if(temp == null){
			String m = "La clase que esta intentando actualizar no existe";
			System.out.println(m + " en UPDATE FROM CLASSROOM");
			return m;
		}
		saveOrUpdate(classA);
		String m = "La clase ha sido actualizada correctamente";
		System.out.println(m);
		return m;
	}
	
	@Transactional
	public List<Classroom> getTable(int iDisplayStart, int iDisplayLength, String s) {
		if(s == ""){
			Query quer = getCurrentSession().createQuery("from " + entity.getSimpleName());
			quer.setFirstResult(iDisplayStart);
			quer.setMaxResults(iDisplayLength);
		}
		String ss = "%" + s + "%";
		Criterion criterion1 = Restrictions.like("id.building", ss);
		Criterion criterion2 = Restrictions.like("id.roomNumber", ss);
		//int t = Integer.parseInt(s);
		//Criterion criterion3 = Restrictions.like("capacity", (short) t);
		Criteria criteria = getCurrentSession().createCriteria(entity);
		criteria.add(Restrictions.or(criterion1, criterion2));
		criteria.setFirstResult(iDisplayStart);
		criteria.setMaxResults(iDisplayLength);
		return (List<Classroom>) criteria.list();
	}
	
	
}
