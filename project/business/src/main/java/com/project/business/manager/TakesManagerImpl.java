package com.project.business.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.SectionDTO;
import com.project.business.dto.StudentDTO;
import com.project.business.dto.TakesDTO;
import com.project.persistence.dao.ClassroomDao;
import com.project.persistence.dao.CourseDao;
import com.project.persistence.dao.DepartamentDao;
import com.project.persistence.dao.SectionDao;
import com.project.persistence.dao.StudentDao;
import com.project.persistence.dao.TakesDao;
import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;
import com.project.persistence.entity.Course;
import com.project.persistence.entity.Department;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Student;
import com.project.persistence.entity.Takes;
import com.project.persistence.entity.TakesId;

@Service("takes")
public class TakesManagerImpl implements TakesManager {

	@Autowired
	private TakesDao takesDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private ClassroomDao classroomDao;
	
	@Autowired
	private DepartamentDao departmentDao;
	
	@Autowired
	private SectionDao sectionDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public Section mappingDTO(SectionDTO sec) {
		Course cour = courseDao.getById(sec.getCourseId());
		Classroom cla = classroomDao.getById(new ClassroomId(sec.getBuilding(), sec.getRoomNumber()));
		return new Section(new SectionId(sec.getCourseId(), sec.getSecId(), sec.getSemester()
				, sec.getYear()), cour, cla, sec.getTimeSlotId());
	}

	@Override
	public SectionDTO mappingDTOSec(Takes take) {
		return new SectionDTO(take.getSection().getCourse().getCourseId(), take.getId().getSecId()
				, take.getId().getSemester(), take.getId().getYear(), take.getSection().getCourse().getDepartment().getDeptName()
				, take.getSection().getCourse().getTitle(), take.getSection().getCourse().getCredits()
				, take.getSection().getClassroom().getId().getBuilding(), take.getSection().getClassroom().getId().getRoomNumber()
				, take.getSection().getTimeSlotId(), take.getGrade());
	}

	@Override
	public List<SectionDTO> mappingListSec(List<Takes> ltake) {
		List<SectionDTO> res = new ArrayList<SectionDTO>();
		for(Takes take : ltake){
			res.add(mappingDTOSec(take));
		}
		return res;
	}

	@Override
	public Student mappingDTO(StudentDTO st) {
		Department dep = departmentDao.findById(st.getDptName());
		return new Student(st.getId(), dep, st.getName(), (short) st.getTotCred());
	}

	@Override
	public StudentDTO mappingDTOSt(Takes take) {
		return new StudentDTO(take.getStudent().getId(), take.getStudent().getDepartment().getDeptName()
				, take.getStudent().getName(),take.getStudent().getTotCred());
	}

	@Override
	public List<StudentDTO> mappingListSt(List<Takes> ltake) {
		List<StudentDTO> res = new ArrayList<StudentDTO>();
		for(Takes take : ltake){
			res.add(mappingDTOSt(take));
		}
		return res;
	}

	@Override
	public Takes getById(TakesId id) {
		return takesDao.getById(id);
	}

	@Override
	public String insert(String studentId, SectionId sectionId, String grade) {
		return takesDao.insert(studentId, sectionId, grade);
	}

	@Override
	public String delete(TakesId takeId) {
		return takesDao.delete(takeId);
	}

	@Override
	public String update(Takes take) {
		return takesDao.update(take);
	}

	@Override
	public List<SectionDTO> getByStudent(String studentId) {
		return mappingListSec(takesDao.getByStudent(studentId));
	}

	@Override
	public List<StudentDTO> getBySection(SectionId secId) {
		return mappingListSt(takesDao.getBySection(secId));
	}

	@Override
	public Takes mappingDTO(TakesDTO take) {
		Student st = studentDao.getById(take.getStudentId());
		Section sec = sectionDao.getById(new SectionId(take.getCourseId()
				, take.getSecId(), take.getSemester(), (short) take.getYear()));
		return new Takes(new TakesId(take.getStudentId(),take.getCourseId(),take.getSecId()
				, take.getSemester(), (short) take.getYear())
				, sec, st, take.getGrade());
	}
	
	

}
