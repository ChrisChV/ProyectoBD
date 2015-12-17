package com.project.business.manager;

import java.util.List;

import com.project.business.dto.TimeSlotDTO;
import com.project.persistence.entity.TimeSlot;
import com.project.persistence.entity.TimeSlotId;

public interface TimeSlotManager {

	public TimeSlot mappingDTO(TimeSlotDTO time);
	
	public TimeSlotDTO mappingDTO(TimeSlot time);
	
	public List<TimeSlotDTO> mappingList(List<TimeSlot> ltime);
	
	public List<TimeSlotDTO> getAll();
	
	public TimeSlotDTO getById(TimeSlotId id);
	
	public List<TimeSlotDTO> getByTime(String timeSlotId);
	
	public String insert(String timeSlotId, String day, byte startHr, byte startMin
			,Byte endHr, Byte endMin);
	
	public String delete(TimeSlotId timeId);
	
	public String update(TimeSlot time);
	
}
