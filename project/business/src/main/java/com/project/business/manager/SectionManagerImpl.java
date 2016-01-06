package com.project.business.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.SectionDTO;
import com.project.persistence.dao.ClassroomDao;
import com.project.persistence.dao.CourseDao;
import com.project.persistence.dao.SectionDao;
import com.project.persistence.dao.TakesDao;
import com.project.persistence.dao.TeachesDao;
import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;
import com.project.persistence.entity.Course;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Takes;
import com.project.persistence.entity.Teaches;

@Service("section")
public class SectionManagerImpl implements SectionManager {

	@Autowired
	private SectionDao sectionDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private ClassroomDao classroomDao;
	
	@Autowired
	private TeachesDao teachesDao;
	
	@Autowired
	private TakesDao takesDao;
	
	@Override
	public Section mappingDTO(SectionDTO sec) {
		Course cour = courseDao.getById(sec.getCourseId());
		Classroom cla = classroomDao.getById(new ClassroomId(sec.getBuilding(), sec.getRoomNumber()));
		return new Section(new SectionId(sec.getCourseId(), sec.getSecId(), sec.getSemester()
				, sec.getYear()), cour, cla, sec.getTimeSlotId());
	}

	@Override
	public SectionDTO mappingDTO(Section sec) {
		return new SectionDTO(sec.getId().getCourseId(), sec.getId().getSecId(), sec.getId().getSemester()
				, sec.getId().getYear(),sec.getCourse().getDepartment().getDeptName()
				, sec.getCourse().getTitle(), sec.getCourse().getCredits()
				, sec.getClassroom().getId().getBuilding(), sec.getClassroom().getId().getRoomNumber()
				, sec.getTimeSlotId());
	}

	@Override
	public List<SectionDTO> mappingList(List<Section> lsec) {
		List<SectionDTO> res = new ArrayList<SectionDTO>();
		for(Section sec : lsec){
			res.add(mappingDTO(sec));
		}
		return res;
	}

	@Override
	public List<SectionDTO> getAll() {
		return mappingList(sectionDao.findAll());
	}

	@Override
	public SectionDTO getById(SectionId secId) {
		return mappingDTO(sectionDao.getById(secId));
	}

	@Override
	public List<SectionDTO> getByCourse(String courseId) {
		return mappingList(sectionDao.getByCourse(courseId));
	}

	@Override
	public List<SectionDTO> getByClassroom(ClassroomId classId) {
		return mappingList(sectionDao.getByClassroom(classId));
	}

	@Override
	public String insert(String courseId, String secId, String semester, short year, ClassroomId classId,
			String timeSlotId) {
		return sectionDao.insert(courseId, secId, semester, year, classId, timeSlotId);
	}

	@Override
	public String delete(SectionId sectionId) {
		List<Teaches> ltea = teachesDao.getBySection(sectionId);
		for(Teaches tea : ltea){
			teachesDao.delete(tea.getId());
		}
		List<Takes> ltake = takesDao.getBySection(sectionId);
		for(Takes take : ltake){
			takesDao.delete(take.getId());
		}
		return sectionDao.delete(sectionId);
	}

	@Override
	public String update(Section section) {
		return sectionDao.update(section);
	}

	
	
}
