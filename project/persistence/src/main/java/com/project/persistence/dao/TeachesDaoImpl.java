package com.project.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Instructor;
import com.project.persistence.entity.Section;
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

	@Transactional
	public String insert(String instructorId, SectionId sectionId) {
		if(instructorId == null){
			String m = "El id des instructor no debe ser null";
			System.out.println(m + " en INSERT FROM TEACHES");
			return m;
		}
		if(sectionId.getCourseId() == null){
			String m = "El id del curso no debe ser null";
			System.out.println(m + " en INSERT FROM TEACHES");
			return m;
		}
		if(sectionId.getSecId() == null){
			String m = "El id de la section no debe ser null";
			System.out.println(m + " en INSERT FROM TEACHES");
			return m;
		}
		if(sectionId.getSemester() == null){
			String m = "El semestre no debe ser null";
			System.out.println(m + " en INSERT FROM TEACHES");
			return m;
		}
		Teaches tea = getById(new TeachesId(instructorId, sectionId.getCourseId()
				, sectionId.getSecId(), sectionId.getSemester(), sectionId.getYear()));
		if(tea != null){
			String m = "EL teaches que intenta ingresar ya existe";
			System.out.println(m + " en INSERT FROM TEACHES");
			return m;
		}
		Instructor ins = (Instructor) getCurrentSession().get(Instructor.class, instructorId);
		if(ins == null){
			String m = "EL instructor no existe";
			System.out.println(m + " en INSERT FROM TEACHES");
			return m;
		}
		Section sec = (Section) getCurrentSession().get(Section.class, sectionId);
		if(sec == null){
			String m = "La section no existe";
			System.out.println(m + " en INSERT FROM TEACHES");
			return m;
		}
		tea = new Teaches(new TeachesId(instructorId, sectionId.getCourseId()
				, sectionId.getSecId(), sectionId.getSemester(), sectionId.getYear()));
		saveOrUpdate(tea);
		String m = "La Teaches a sido insertada correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String delete(TeachesId teachesId) {
		if(teachesId.getId() == null){
			String m = "El id des instructor no debe ser null";
			System.out.println(m + " en DELETE FROM TEACHES");
			return m;
		}
		if(teachesId.getCourseId() == null){
			String m = "El id del curso no debe ser null";
			System.out.println(m + " en DELETE FROM TEACHES");
			return m;
		}
		if(teachesId.getSecId() == null){
			String m = "El id de la section no debe ser null";
			System.out.println(m + " en DELETE FROM TEACHES");
			return m;
		}
		if(teachesId.getSemester() == null){
			String m = "El semestre no debe ser null";
			System.out.println(m + " en DELETE FROM TEACHES");
			return m;
		}
		Teaches tea = getById(teachesId);
		if(tea == null){
			String m = "El teaches que esta intentando ingresar no existe";
			System.out.println(m + " en DELETE FROM TEACHES");
			return m;
		}
		getCurrentSession().delete(tea);
		String m = "El teaches se a eliminado correctamente";
		System.out.println(m);
		return m;
	}
}
