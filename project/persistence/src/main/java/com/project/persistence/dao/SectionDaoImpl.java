package com.project.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;
import com.project.persistence.entity.Course;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Student;

@Repository("sectionDao")
public class SectionDaoImpl extends GenericDaoImpl<Section> implements SectionDao {

	private SectionDaoImpl(){
		super(Section.class);
	}

	@Transactional
	public Section getById(SectionId id) {
		return (Section) getCurrentSession().get(Section.class, id);
	}

	@Transactional
	public List<Section> getByCourse(String courseId) {
		Criterion criterion = Restrictions.eq("id.courseId", courseId);
		return findByCriteria(criterion);
	}

	@Transactional
	public String insert(String courseId, String secId, String semester, short year, ClassroomId classId,
			String timeSlotId) {
		if(courseId == null){
			String m = "EL id de curso no puede ser null";
			System.out.println(m + " en INSERT FROM SECTION");
			return m;
		}
		if(secId == null){
			String m = "El id de la section no puede ser null";
			System.out.println(m + " en INSERT FROM SECTION");
			return m;
		}
		if(semester == null){
			String m = "El semestre no puede ser null";
			System.out.println(m + " en INSERT FROM SECTION");
			return m;
		}
		Classroom cla = null;
		if(classId != null && classId.getBuilding() != null && classId.getRoomNumber() != null){
			cla = (Classroom) getCurrentSession().get(Classroom.class,classId);
		}
		Course cour = (Course) getCurrentSession().get(Course.class, courseId);
		if(cour == null){
			String m = "El curso no existe";
			System.out.println(m + " en INSERT FROM SECTION");
			return m;
		}
		Section sec = new Section(new SectionId(courseId, secId, semester, year), cour, cla, timeSlotId);
		saveOrUpdate(sec);
		String m = "La seccion se ingreso correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String delete(SectionId sectionId) {
		if(sectionId.getCourseId() == null){
			String m = "EL id de curso no puede ser null";
			System.out.println(m + " en DELETE FROM SECTION");
			return m;
		}
		if(sectionId.getSecId() == null){
			String m = "El id de la section no puede ser null";
			System.out.println(m + " en DELETE FROM SECTION");
			return m;
		}
		if(sectionId.getSemester() == null){
			String m = "El semestre no puede ser null";
			System.out.println(m + " en DELETE FROM SECTION");
			return m;
		}
		Section sec = getById(sectionId);
		if(sec == null){
			String m = "La section que intenta eliminar no existe";
			System.out.println(m + " en DELETE FROM SECTION");
			return m;
		}
		getCurrentSession().delete(sec);
		String m = "La section se elimino correctamente";
		System.out.println(m);
		return m;
	}

	@Transactional
	public String update(Section section) {
		if(section == null){
			String m = "La section no puede ser null";
			System.out.println(m + " en UPDATE FROM SECTION");
			return m;
		}
		Section temp = getById(section.getId());
		if(temp == null){
			String m = "La section que intenta actualizar no existe";
			System.out.println(m + " en UPDATE FROM SECTION");
			return m;
		}
		saveOrUpdate(section);
		String m = "La section se actualizo correctamente";
		System.out.println(m + " en UPDATE FROM SECTION");
		return m;
	}

	@Transactional
	public List<Section> getByClassroom(ClassroomId id) {
		Criterion criterion = Restrictions.ge("classroom.id", id);
		return findByCriteria(criterion);
	}

	
	
	
}
