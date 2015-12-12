package com.project.persistence.dao;

import com.project.persistence.entity.TimeSlot;
import com.project.persistence.entity.TimeSlotId;

public interface TimeSlotDao extends GenericDao<TimeSlot> {

	public TimeSlot getById(TimeSlotId id);
	
}
