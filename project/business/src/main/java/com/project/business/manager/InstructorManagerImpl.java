package com.project.business.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.InstructorDTO;
import com.project.persistence.dao.AdvisorDao;
import com.project.persistence.dao.DepartamentDao;
import com.project.persistence.dao.InstructorDao;
import com.project.persistence.dao.TeachesDao;
import com.project.persistence.entity.Advisor;
import com.project.persistence.entity.Department;
import com.project.persistence.entity.Instructor;
import com.project.persistence.entity.Teaches;

@Service("instructor")
public class InstructorManagerImpl implements InstructorManager {

	@Autowired
	private InstructorDao instructorDao;
	
	@Autowired
	private DepartamentDao departmentDao;
	
	@Autowired
	private TeachesDao teachesDao;

	@Autowired
	private AdvisorDao advisorDao;
	
	@Override
	public Instructor mappingDTO(InstructorDTO ins) {
		Department dpt = departmentDao.findById(ins.getDptName());
		return new Instructor(ins.getId(), dpt, ins.getName(), new BigDecimal(ins.getSalary()));
	}

	@Override
	public InstructorDTO mappingDTO(Instructor ins) {
		return new InstructorDTO(ins.getId(), ins.getDepartment().getDeptName(), ins.getName(), ins.getSalary().longValue());
	}

	@Override
	public List<InstructorDTO> mappingList(List<Instructor> lins) {
		List<InstructorDTO> res = new ArrayList<InstructorDTO>();
		for(Instructor ins : lins){
			res.add(mappingDTO(ins));
		}
		return res;
	}
	
	@Override
	public List<InstructorDTO> getAll() {
		return mappingList(instructorDao.findAll());
	}

	@Override
	public InstructorDTO getById(String instructorId) {
		return mappingDTO(instructorDao.getById(instructorId));
	}

	@Override
	public List<InstructorDTO> getByDept(String deptName) {
		return mappingList(instructorDao.getByDept(deptName));
	}

	@Override
	public String insert(String insId, String deptId, String name, BigDecimal salary) {
		return instructorDao.insert(insId, deptId, name, salary);
	}

	@Override
	public String delete(String insId) {
		List<Advisor> ladv = advisorDao.getByInstructor(insId);
		for(Advisor adv : ladv){
			advisorDao.delete(adv.getSId());
		}
		List<Teaches> ltea = teachesDao.getByInstructor(insId);
		for(Teaches tea : ltea){
			teachesDao.delete(tea.getId());
		}
		return instructorDao.delete(insId);
	}

	@Override
	public String update(Instructor ins) {
		return instructorDao.update(ins);
	}

	@Override
	public InstructorDTO getByIndex(int index) {
		return mappingDTO(instructorDao.getByIndex(index));
	}

	@Override
	public int verificarIndex(int index) {
		return instructorDao.verificarIndex(index);
	}

	@Override
	public int getLastIndex() {
		return instructorDao.getLastIndex();
	}
	
}
