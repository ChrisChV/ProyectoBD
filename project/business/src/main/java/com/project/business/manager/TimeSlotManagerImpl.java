package com.project.business.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.business.dto.TimeSlotDTO;
import com.project.persistence.dao.TimeSlotDao;
import com.project.persistence.entity.TimeSlot;
import com.project.persistence.entity.TimeSlotId;

@Service("timeSlot")
public class TimeSlotManagerImpl implements TimeSlotManager {

	@Autowired
	private TimeSlotDao timeSlotDao;
	
	@Override
	public TimeSlot mappingDTO(TimeSlotDTO time) {
		return new TimeSlot(new TimeSlotId(time.getId(),time.getDay(), (byte) time.getStartHr(),(byte)time.getStartMin())
				,(byte)time.getEndHr(),(byte)time.getEndMin());
	}

	@Override
	public TimeSlotDTO mappingDTO(TimeSlot time) {
		return new TimeSlotDTO(time.getId().getTimeSlotId(), time.getId().getDay()
				,time.getId().getStartHr(), time.getId().getStartMin(), time.getEndHr(), time.getEndMin());
	}

	@Override
	public List<TimeSlotDTO> mappingList(List<TimeSlot> ltime) {
		List<TimeSlotDTO> res = new ArrayList<TimeSlotDTO>();
		for(TimeSlot time : ltime){
			res.add(mappingDTO(time));
		}
		return res;
	}

	@Override
	public List<TimeSlotDTO> getAll() {
		return mappingList(timeSlotDao.findAll());
	}

	@Override
	public TimeSlotDTO getById(TimeSlotId id) {
		return mappingDTO(timeSlotDao.getById(id));
	}

	@Override
	public List<TimeSlotDTO> getByTime(String timeSlotId) {
		return mappingList(timeSlotDao.getByTimeId(timeSlotId));
	}

	@Override
	public String insert(String timeSlotId, String day, byte startHr, byte startMin, Byte endHr, Byte endMin) {
		return timeSlotDao.insert(timeSlotId, day, startHr, startMin, endHr, endMin);
	}

	@Override
	public String delete(TimeSlotId timeId) {
		return timeSlotDao.delete(timeId);
	}

	@Override
	public String update(TimeSlot time) {
		return timeSlotDao.update(time);
	}

	@Override
	public TimeSlotDTO getByIndex(int index) {
		return mappingDTO(timeSlotDao.getByIndex(index));
	}

	@Override
	public int verificarIndex(int index) {
		return timeSlotDao.verificarIndex(index);
	}

	@Override
	public int getLastIndex() {
		return timeSlotDao.getLastIndex();
	}
}
