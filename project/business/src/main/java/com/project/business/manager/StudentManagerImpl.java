package com.project.business.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.StudentDTO;
import com.project.persistence.dao.AdvisorDao;
import com.project.persistence.dao.DepartamentDao;
import com.project.persistence.dao.StudentDao;
import com.project.persistence.dao.TakesDao;
import com.project.persistence.entity.Advisor;
import com.project.persistence.entity.Department;
import com.project.persistence.entity.Student;
import com.project.persistence.entity.Takes;

@Service("student")
public class StudentManagerImpl implements StudentManager {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private DepartamentDao departmentDao;
	
	@Autowired
	private AdvisorDao advisorDao;
	
	@Autowired
	private TakesDao takesDao;
	
	@Override
	public Student mappingDTO(StudentDTO st) {
		Department dep = departmentDao.findById(st.getDptName());
		return new Student(st.getId(), dep, st.getName(), (short) st.getTotCred());
	}

	@Override
	public StudentDTO mappingDTO(Student st) {
		return new StudentDTO(st.getId(), st.getDepartment().getDeptName(), st.getName(), st.getTotCred());
	}

	@Override
	public List<StudentDTO> mappingList(List<Student> lst) {
		List<StudentDTO> res = new ArrayList<StudentDTO>();
		for(Student st : lst){
			res.add(mappingDTO(st));
		}
		return res;
	}

	@Override
	public List<StudentDTO> getAll(){
		return mappingList(studentDao.findAll());
	}
	
	@Override
	public StudentDTO getById(String studentId) {
		return mappingDTO(studentDao.getById(studentId));
	}

	@Override
	public List<StudentDTO> getByDept(String deptName) {
		return mappingList(studentDao.getByDept(deptName));
	}

	@Override
	public String insert(String id, String deptId, String name, Short totCred) {
		return studentDao.insert(id, deptId, name, totCred);
	}

	@Override
	public String delete(String id) {
		List<Advisor> ladv = advisorDao.getByStudent(id);
		for(Advisor adv : ladv){
			advisorDao.delete(adv.getSId());
		}
		List<Takes> ltake = takesDao.getByStudent(id);
		for(Takes take : ltake){
			takesDao.delete(take.getId());
		}
		return studentDao.delete(id);
	}

	@Override
	public String update(Student st) {
		return studentDao.update(st);
	}

	
}
