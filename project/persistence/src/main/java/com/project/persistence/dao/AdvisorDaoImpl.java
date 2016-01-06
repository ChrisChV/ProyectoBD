package com.project.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Advisor;
import com.project.persistence.entity.Instructor;
import com.project.persistence.entity.Student;

@Repository("advisorDao")
public class AdvisorDaoImpl extends GenericDaoImpl<Advisor> implements AdvisorDao {
	
	private AdvisorDaoImpl(){
		super(Advisor.class);
	}

	@Transactional
	public Advisor getById(String id) {
		return (Advisor) getCurrentSession().get(Advisor.class, id);
	}
	
	@Transactional
	public String delete(String id){
		if(id == null){
			String m = "La id es null";
			System.out.println(m + " en DELETE FROM ADVISOR");
			return m;
		}
		Advisor ad = getById(id);
		if(ad == null){
			String m = "EL Advisor " + id  + "no existe";
			System.out.println(m + " en DELETE FROM ADVISOR");
			return m;
		}
		getCurrentSession().delete(ad);
		String m = "Se elimino el Advisor correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public List<Advisor> getByStudent(String studentId) {
		Criterion criterion = Restrictions.eq("student.id",studentId);
		return findByCriteria(criterion);
	}

	@Transactional
	public List<Advisor> getByInstructor(String instructorId) {
		Criterion criterion = Restrictions.eq("instructor.id", instructorId);
		return findByCriteria(criterion);
	}

	@Transactional
	public String insert(String instructorId, String studentId) {
		if(instructorId == null || studentId == null){
			String m = "No se permiten nulos";
			System.out.println(m + " en INSERT FROM ADVISOR");
			return m;
		}
		Advisor ad = getById(studentId);
		if(ad != null){
			String m = "EL Advisor " + studentId + " ya existe";
			System.out.println(m + " en INSERT FROM ADVISOR");
			return m;
		}
		Student st = (Student) getCurrentSession().get(Student.class, studentId);
		Instructor ins = (Instructor) getCurrentSession().get(Instructor.class, instructorId);
		if(st == null){
			String m = "El estudiante " + studentId + "no existe";
			System.out.println(m + " en INSERT FROM ADVISOR");
			return m;
		}
		if(ins == null){
			String m = "El instructor " + instructorId + "no existe";
			System.out.println(m + " en INSERT FROM ADVISOR");
			return m;
		}
		ad = new Advisor(ins, st);
		saveOrUpdate(ad);
		String m = "El Advisor se ingreso correctamente";
		System.out.println(m);
		
		return m;
	}

	@Transactional
	public String update(Advisor ad) {
		if(ad == null){
			String m = "El Advisor no puede ser nulo";
			System.out.println(m + "EN UPDATE FROM ADVISOR");
			return m;
		}
		Advisor temp = getById(ad.getSId());
		if(temp == null){
			String m = "El Advisor que usted quiere editar no existe";
			System.out.println(m + " en UPDATE FROM ADVISOR");
			return m;
		}
		saveOrUpdate(ad);
		String m = "El advisor se actualizo correctamente";
		System.out.println(m);
		return m;
	}
	
}
