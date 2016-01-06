package com.project.business.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.ClassroomDTO;
import com.project.persistence.dao.ClassroomDao;
import com.project.persistence.dao.SectionDao;
import com.project.persistence.dao.TakesDao;
import com.project.persistence.dao.TeachesDao;
import com.project.persistence.entity.Classroom;
import com.project.persistence.entity.ClassroomId;
import com.project.persistence.entity.Section;
import com.project.persistence.entity.Takes;
import com.project.persistence.entity.Teaches;

@Service("classroom")
public class ClassroomManagerImpl implements ClassroomManager {

	@Autowired
	private ClassroomDao classroomDao;
	
	@Autowired
	private SectionDao sectionDao;
	
	@Autowired
	private TakesDao takesDao;
	
	@Autowired
	private TeachesDao teachesDao;
		
	@Override
	public ClassroomDTO mappingDTO(Classroom cla) {
		return new ClassroomDTO(cla.getId().getBuilding(), cla.getId().getRoomNumber(), cla.getCapacity());
	}

	@Override
	public Classroom mappingDTO(ClassroomDTO claDTO) {
		return new Classroom(new ClassroomId(claDTO.getBuilding(), claDTO.getRoomNumber()), (short) claDTO.getCapacity());
	}
	
	@Override
	public List<ClassroomDTO> mappingList(List<Classroom> lcla) {
		List<ClassroomDTO> res = new ArrayList<ClassroomDTO>();
		for(Classroom cla : lcla){
			res.add(mappingDTO(cla));
		}
		return res;
	}

	@Override
	public List<ClassroomDTO> getAll() {
		return mappingList(classroomDao.findAll());
	}

	@Override
	public ClassroomDTO getById(ClassroomId id) {
		return mappingDTO(classroomDao.getById(id));
	}

	@Override
	public String insert(String building, String roomN, short capacity) {
		return classroomDao.insert(building, roomN, capacity);
	}

	@Override
	public String delete(ClassroomId classId) {
		List<Section> secs = sectionDao.getByClassroom(classId);
		for(Section sec : secs){
			List<Takes> takes = takesDao.getBySection(sec.getId());
			for(Takes take : takes){
				takesDao.delete(take.getId());
			}
			List<Teaches> teaches = teachesDao.getBySection(sec.getId());
			for(Teaches tea : teaches){
				teachesDao.delete(tea.getId());
			}
			sectionDao.delete(sec.getId());
		}
		return classroomDao.delete(classId);
	}

	@Override
	public String update(Classroom cla) {
		return classroomDao.update(cla);
	}

	@Override
	public ClassroomDTO getByIndex(int index) {
		return mappingDTO(classroomDao.getByIndex(index));
	}

	@Override
	public int verificarIndex(int index) {
		return classroomDao.verificarIndex(index);
	}

	@Override
	public int getLastIndex() {
		return classroomDao.getLastIndex();
	}

	@Override
	public List<ClassroomDTO> getTable(int start, int length, String s) {
		return mappingList(classroomDao.getTable(start, length, s));
	}
}
