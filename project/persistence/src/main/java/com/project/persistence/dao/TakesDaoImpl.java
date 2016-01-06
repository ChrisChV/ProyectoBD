package com.project.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Student;
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
	public List<Takes> getByStudent(String studentId) {
		Criterion criterion = Restrictions.eq("id.id", studentId);
		return findByCriteria(criterion);
	}

	@Transactional
	public String insert(String studentId, SectionId sectionId, String grade) {
		if(studentId == null){
			String m = "El id del estudiante no debe ser null";
			System.out.println(m + " en INSERT FROM TAKES");
			return m;
		}
		if(sectionId.getCourseId() == null){
			String m = "El id del curso no debe ser null";
			System.out.println(m + " en INSERT FROM TAKES");
			return m;
		}
		if(sectionId.getSecId() == null){
			String m = "El id de la section no debe ser null";
			System.out.println(m + " en INSERT FROM TAKES");
			return m;
		}
		if(sectionId.getSemester() == null){
			String m = "El semestre no debe ser null";
			System.out.println(m + " en INSERT FROM TAKES");
			return m;
		}
		Takes take = getById(new TakesId(studentId, sectionId.getCourseId()
				, sectionId.getSecId(), sectionId.getSemester(), sectionId.getYear()));
		if(take != null){
			String m = "El take que esta intentando ingresar ya existe";
			System.out.println(m + " en INSERT FROM TAKES");
			return m;
		}
		Section sec = (Section) getCurrentSession().get(Section.class, sectionId);
		if(sec == null){
			String m = "La section no existe";
			System.out.println(m + " en INSERT FROM TAKES");
			return m;
		}
		Student st = (Student) getCurrentSession().get(Student.class, studentId);
		if(st == null){
			String m = "El estudiante no existe";
			System.out.println(m + " en INSERT FROM TAKES");
			return m;
		}
		take = new Takes(new TakesId(studentId, sectionId.getCourseId()
				, sectionId.getSecId(), sectionId.getSemester(), sectionId.getYear())
				, sec, st, grade);
		saveOrUpdate(take);
		take = getById(new TakesId(studentId, sectionId.getCourseId()
				, sectionId.getSecId(), sectionId.getSemester(), sectionId.getYear()));
		String m = "El take a sido ingresado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String delete(TakesId takeId) {
		if(takeId.getId() == null){
			String m = "El id del estudiante no debe ser null";
			System.out.println(m + " en DELETE FROM TAKES");
			return m;
		}
		if(takeId.getCourseId() == null){
			String m = "El id del curso no debe ser null";
			System.out.println(m + " en DELETE FROM TAKES");
			return m;
		}
		if(takeId.getSecId() == null){
			String m = "El id de la section no debe ser null";
			System.out.println(m + " en DELETE FROM TAKES");
			return m;
		}
		if(takeId.getSemester() == null){
			String m = "El semestre no debe ser null";
			System.out.println(m + " en DELETE FROM TAKES");
			return m;
		}
		Takes take = getById(takeId);
		if(take == null){
			String m = "El take que intenta eliminar no existe";
			System.out.println(m + " en DELETE FROM TAKES");
			return m;
		}
		getCurrentSession().delete(take);
		String m = "El take a sido eliminado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String update(Takes take) {
		if(take == null){
			String m = "El take no debe ser null";
			System.out.println(m + " en UPDATE FROM TAKES");
			return m;
		}
		Takes temp = getById(take.getId());
		if(temp == null){
			String m = "El take que intenta actualizar no existe";
			System.out.println(m + " en UPDATE FROM TAKES");
			return m;
		}
		saveOrUpdate(take);
		String m = "El take ha sido actualizado correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public List<Takes> getBySection(SectionId secId) {
		Criterion criterion = Restrictions.eq("section.id", secId);
		return findByCriteria(criterion);
	}
		
}
