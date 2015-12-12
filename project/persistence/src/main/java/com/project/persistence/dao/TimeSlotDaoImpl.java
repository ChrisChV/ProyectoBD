package com.project.persistence.dao;

import org.springframework.stereotype.Repository;

import com.project.persistence.entity.TimeSlot;
import com.project.persistence.entity.TimeSlotId;

@Repository("timeSlotDao")
public class TimeSlotDaoImpl extends GenericDaoImpl<TimeSlot> implements TimeSlotDao{
	
	private TimeSlotDaoImpl(){
		super(TimeSlot.class);
	}

	@Override
	public TimeSlot getById(TimeSlotId id) {
		return (TimeSlot) getCurrentSession().get(TimeSlot.class, id);
	}
	
	
	
}
