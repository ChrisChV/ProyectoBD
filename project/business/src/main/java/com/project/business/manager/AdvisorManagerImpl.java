package com.project.business.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.InstructorDTO;
import com.project.business.dto.StudentDTO;
import com.project.persistence.dao.AdvisorDao;
import com.project.persistence.dao.DepartamentDao;
import com.project.persistence.dao.InstructorDao;
import com.project.persistence.dao.StudentDao;
import com.project.persistence.entity.Advisor;
import com.project.persistence.entity.Department;
import com.project.persistence.entity.Instructor;
import com.project.persistence.entity.Student;

@Service("advisor")
public class AdvisorManagerImpl implements AdvisorManager {

	@Autowired
	private AdvisorDao advisorDao;
	
	@Autowired
	private DepartamentDao departmentDao;
	
	@Override
	public Student mappingDTO(StudentDTO st) {
		Department dep = departmentDao.findById(st.getDptName());
		return new Student(st.getId(), dep, st.getName(), (short) st.getTotCred());
	}

	@Override
	public StudentDTO mappingDTOS(Advisor ad) {
		return new StudentDTO(ad.getStudent().getId(), ad.getStudent().getDepartment().getDeptName()
				, ad.getStudent().getName(), ad.getStudent().getTotCred());
	}

	@Override
	public List<StudentDTO> mappingListS(List<Advisor> lad) {
		List<StudentDTO> res = new ArrayList<StudentDTO>();
		for(Advisor ad : lad){
			res.add(mappingDTOS(ad));
		}
		return res;
	}

	@Override
	public Instructor mappingDTO(InstructorDTO ins) {
		Department dpt = departmentDao.findById(ins.getDptName());
		return new Instructor(ins.getId(), dpt, ins.getName(), new BigDecimal(ins.getSalary()));
	}

	@Override
	public InstructorDTO mappingDTOI(Advisor ins) {
		return new InstructorDTO(ins.getInstructor().getId(), ins.getStudent().getDepartment().getDeptName()
				, ins.getInstructor().getName(), ins.getInstructor().getSalary().longValue());
	}

	@Override
	public List<InstructorDTO> mappingListI(List<Advisor> lad) {
		List<InstructorDTO> res = new ArrayList<InstructorDTO>();
		for(Advisor ad : lad){
			res.add(mappingDTOI(ad));
		}
		return res;
	}

	@Override
	public Advisor getById(String id) {
		return advisorDao.getById(id);
	}

	@Override
	public List<Advisor> getAll() {
		return advisorDao.findAll();
	}

	@Override
	public List<InstructorDTO> getByStudent(String studentId) {
		return mappingListI(advisorDao.getByStudent(studentId));
		
	}

	@Override
	public List<StudentDTO> getByInstructor(String instructorId) {
		return mappingListS(advisorDao.getByInstructor(instructorId));
	}

	@Override
	public String insert(String instructorId, String studentId) {
		return advisorDao.insert(instructorId, studentId);
	}

	@Override
	public String update(Advisor ad) {
		return advisorDao.update(ad);
	}

	@Override
	public String delete(String id) {
		return advisorDao.delete(id);
	}

}
