package com.project.business.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.DepartmentDTO;
import com.project.persistence.dao.AdvisorDao;
import com.project.persistence.dao.CourseDao;
import com.project.persistence.dao.DepartamentDao;
import com.project.persistence.dao.InstructorDao;
import com.project.persistence.dao.PrereqDao;
import com.project.persistence.dao.SectionDao;
import com.project.persistence.dao.StudentDao;
import com.project.persistence.dao.TakesDao;
import com.project.persistence.dao.TeachesDao;
import com.project.persistence.entity.Advisor;
import com.project.persistence.entity.Course;
import com.project.persistence.entity.Department;
import com.project.persistence.entity.Instructor;
import com.project.persistence.entity.Prereq;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.Student;
import com.project.persistence.entity.Takes;
import com.project.persistence.entity.Teaches;

@Service("department")
public class DepartmentManagerImpl implements DepartmentManager {

	@Autowired
	private DepartamentDao departmentDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private PrereqDao prereqDao;
	
	@Autowired
	private SectionDao sectionDao;
	
	@Autowired
	private TeachesDao teachesDao;
	
	@Autowired
	private TakesDao takesDao;
	
	@Autowired
	private InstructorDao instructorDao;
	
	@Autowired
	private AdvisorDao advisorDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public Department mappingDTO(DepartmentDTO dept) {
		return new Department(dept.getDptName(), dept.getBuilding(), new BigDecimal(dept.getBudget()));
	}

	@Override
	public DepartmentDTO mappingDTO(Department dept) {
		return new DepartmentDTO(dept.getDeptName(), dept.getBuilding(), dept.getBudget().longValue());
	}

	@Override
	public List<DepartmentDTO> mappingList(List<Department> ldept) {
		List<DepartmentDTO> res = new ArrayList<DepartmentDTO>();
		for(Department dept : ldept){
			res.add(mappingDTO(dept));
		}
		return res;
	}

	@Override
	public DepartmentDTO getById(String deptName) {
		return mappingDTO(departmentDao.findById(deptName));
	}

	@Override
	public List<DepartmentDTO> getAll() {
		return mappingList(departmentDao.findAll());
	}

	@Override
	public String insert(String depName, String building, BigDecimal budget) {
		return departmentDao.insert(depName, building, budget);
	}

	@Override
	public String delete(String deptName) {
		List<Course> lcour = courseDao.getByDept(deptName);
		for(Course cour : lcour){
			List<Prereq> lprereq = prereqDao.getPrereq(cour.getCourseId());
			for(Prereq pre : lprereq){
				prereqDao.delete(pre.getId());
			}
			List<Section> lsec = sectionDao.getByCourse(cour.getCourseId());
			for(Section sec : lsec){
				List<Teaches> ltea = teachesDao.getBySection(sec.getId());
				for(Teaches tea : ltea){
					teachesDao.delete(tea.getId());
				}
				List<Takes> ltake = takesDao.getBySection(sec.getId());
				for(Takes take : ltake){
					takesDao.delete(take.getId());
				}
				sectionDao.delete(sec.getId());
			}
			courseDao.delete(cour.getCourseId());
		}
		List<Instructor> lins = instructorDao.getByDept(deptName);
		for(Instructor ins : lins){
			List<Advisor> ladv = advisorDao.getByInstructor(ins.getId());
			for(Advisor adv : ladv){
				advisorDao.delete(adv.getSId());
			}
			List<Teaches> ltea = teachesDao.getByInstructor(ins.getId());
			for(Teaches tea : ltea){
				teachesDao.delete(tea.getId());
			}
			instructorDao.delete(ins.getId());
		}
		List<Student> lst = studentDao.getByDept(deptName);
		for(Student st : lst){
			List<Advisor> ladv = advisorDao.getByStudent(st.getId());
			for(Advisor adv : ladv){
				advisorDao.delete(adv.getSId());
			}
			List<Takes> ltake = takesDao.getByStudent(st.getId());
			for(Takes take : ltake){
				takesDao.delete(take.getId());
			}
			studentDao.delete(st.getId());
		}
		return departmentDao.delete(deptName);
	}

	@Override
	public String update(Department dept) {
		return departmentDao.update(dept);
	}

	@Override
	public DepartmentDTO getByIndex(int index) {
		return mappingDTO(departmentDao.getByIndex(index));
	}

	@Override
	public int verificarIndex(int index) {
		return departmentDao.verificarIndex(index);
	}

	@Override
	public int getLastIndex() {
		return departmentDao.getLastIndex();
	}

	@Override
	public List<DepartmentDTO> getTable(int start, int length, String s) {
		return mappingList(departmentDao.getTable(start, length, s));
	}


	
	
}
