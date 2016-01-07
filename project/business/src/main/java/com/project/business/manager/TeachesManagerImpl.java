package com.project.business.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.InstructorDTO;
import com.project.business.dto.SectionDTO;
import com.project.business.dto.TeachesDTO;
import com.project.persistence.dao.ClassroomDao;
import com.project.persistence.dao.CourseDao;
import com.project.persistence.dao.DepartamentDao;
import com.project.persistence.dao.InstructorDao;
import com.project.persistence.dao.SectionDao;
import com.project.persistence.dao.TeachesDao;
import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;
import com.project.persistence.entity.Course;
import com.project.persistence.entity.Department;
import com.project.persistence.entity.Instructor;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.Teaches;
import com.project.persistence.entity.TeachesId;

@Service("teaches")
public class TeachesManagerImpl implements TeachesManager{

	@Autowired
	private TeachesDao teachesDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private ClassroomDao classroomDao;
	
	@Autowired
	private SectionDao sectonDao;
	
	@Autowired
	private DepartamentDao departmentDao;
	
	@Autowired
	private InstructorDao instructorDao;
	
	@Override
	public Section mappingDTO(SectionDTO sec) {
		Course cour = courseDao.getById(sec.getCourseId());
		Classroom cla = classroomDao.getById(new ClassroomId(sec.getBuilding(), sec.getRoomNumber()));
		return new Section(new SectionId(sec.getCourseId(), sec.getSecId(), sec.getSemester()
				, sec.getYear()), cour, cla, sec.getTimeSlotId());
	}

	@Override
	public SectionDTO mappingDTOSec(Teaches tea) {
		Section sec = sectonDao.getById(new SectionId(tea.getId().getCourseId(), tea.getId().getSecId()
				, tea.getId().getSemester(), tea.getId().getYear()));
		Course cour = courseDao.getById(sec.getId().getCourseId());
		Classroom cla = classroomDao.getById(sec.getClassroom().getId());
		return new SectionDTO(sec.getId().getCourseId(), sec.getId().getSecId(), sec.getId().getSemester()
				, sec.getId().getYear(), cour.getDepartment().getDeptName()
				, cour.getTitle(), cour.getCredits()
				, cla.getId().getBuilding(), cla.getId().getRoomNumber()
				, sec.getTimeSlotId());
	}

	@Override
	public List<SectionDTO> mappingListSec(List<Teaches> ltea) {
		List<SectionDTO> res = new ArrayList<SectionDTO>();
		for(Teaches tea : ltea){
			res.add(mappingDTOSec(tea));
		}
		return res;
	}

	@Override
	public Instructor mappingDTO(InstructorDTO ins) {
		Department dpt = departmentDao.findById(ins.getDptName());
		return new Instructor(ins.getId(), dpt, ins.getName(), new BigDecimal(ins.getSalary()));
	}

	@Override
	public InstructorDTO mappingDTOI(Teaches tea) {
		Instructor ins = instructorDao.getById(tea.getId().getId());
		return new InstructorDTO(ins.getId(), ins.getDepartment().getDeptName()
				, ins.getName(), ins.getSalary().longValue());
	}

	@Override
	public List<InstructorDTO> mappingListI(List<Teaches> ltea) {
		List<InstructorDTO> res = new ArrayList<InstructorDTO>();
		for(Teaches tea : ltea){
			res.add(mappingDTOI(tea));
		}
		return res;
	}

	@Override
	public List<Teaches> getAll() {
		return teachesDao.findAll();
	}

	@Override
	public Teaches getById(TeachesId id) {
		return teachesDao.getById(id);
	}

	@Override
	public String insert(String instructorId, SectionId sectionId) {
		return teachesDao.insert(instructorId, sectionId);
	}

	@Override
	public String delete(TeachesId teachesId) {
		return teachesDao.delete(teachesId);
	}

	@Override
	public List<SectionDTO> getByInstructor(String instructorId) {	
		return mappingListSec(teachesDao.getByInstructor(instructorId));
	}

	@Override
	public List<InstructorDTO> getBySection(SectionId sectionId) {
		return mappingListI(teachesDao.getBySection(sectionId));
	}

	@Override
	public Teaches mappingDTO(TeachesDTO tea) {
		return new Teaches(new TeachesId(tea.getInstructorId()
				, tea.getCourseId(), tea.getSecId(), tea.getSemester(), (short) tea.getYear()));
	}
}
