package com.project.persistence.dao;

import java.util.List;

import com.project.persistence.entity.TimeSlot;
import com.project.persistence.entity.TimeSlotId;

public interface TimeSlotDao extends GenericDao<TimeSlot> {

	public TimeSlot getById(TimeSlotId id);
	
	public String insert(String timeSlotId, String day, byte startHr, byte startMin
			,Byte endHr, Byte endMin);
	
	public String delete(TimeSlotId timeId);
	
	public String update(TimeSlot time);
	
	public List<TimeSlot> getByTimeId(String timeSlotId);
	
}
