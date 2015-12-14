package com.project.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.entity.TimeSlot;
import com.project.persistence.entity.TimeSlotId;

@Repository("timeSlotDao")
public class TimeSlotDaoImpl extends GenericDaoImpl<TimeSlot> implements TimeSlotDao{
	
	private TimeSlotDaoImpl(){
		super(TimeSlot.class);
	}

	@Transactional
	public TimeSlot getById(TimeSlotId id) {
		return (TimeSlot) getCurrentSession().get(TimeSlot.class, id);
	}

	@Transactional
	public List<TimeSlot> getByTimeId(String timeSlotId) {
		Criterion criterion = Restrictions.ge("id.timeSlotId", timeSlotId);
		return findByCriteria(criterion);
	}

	@Transactional
	public String insert(String timeSlotId, String day, byte startHr, byte startMin, Byte endHr, Byte endMin) {
		if(timeSlotId == null){
			String m = "El timeSid no debe ser null";
			System.out.print(m + " en INSERT FROM TIMESLOT");
			return m;
		}
		if(day == null){
			String m = "El day no debe ser null";
			System.out.print(m + " en INSERT FROM TIMESLOT");
			return m;
		}
		TimeSlot time = getById(new TimeSlotId(timeSlotId, day, startHr, startMin));
		if(time != null){
			String m = "El time que quiere ingresar ya existe";
			System.out.print(m + " en INSERT FROM TIMESLOT");
			return m;
		}
		time = new TimeSlot(new TimeSlotId(timeSlotId, day, startHr, startMin), endHr, endMin);
		saveOrUpdate(time);
		String m = "EL time a sido ingresado correctamente";
		System.out.print(m);
		return m;
		
	}

	@Transactional
	public String delete(TimeSlotId timeId) {
		if(timeId.getTimeSlotId() == null){
			String m = "El timeSid no debe ser null";
			System.out.print(m + " en DELETE FROM TIMESLOT");
			return m;
		}
		if(timeId.getDay() == null){
			String m = "El day no debe ser null";
			System.out.print(m + " en DELETE FROM TIMESLOT");
			return m;
		}
		TimeSlot time = getById(timeId);
		if(time == null){
			String m = "El time que quiere eliminar no existe";
			System.out.print(m + " en DELETE FROM TIMESLOT");
			return m;
		}
		getCurrentSession().delete(time);
		String m = "El time a sido eliminado correctamente";
		System.out.print(m);
		return m;
	}

	@Transactional
	public String update(TimeSlot time) {
		if(time == null){
			String m = "El time no puede ser null";
			System.out.print(m + " en UPDATE FROM TIMESLOT");
			return m;
		}
		TimeSlot temp = getById(time.getId());
		if(temp == null){
			String m = "EL time que intenta actualizar no existe";
			System.out.print(m + " en UPDATE FROM TIMESLOT");
			return m;
		}
		saveOrUpdate(time);
		String m = "El time a sido actualizado correctamente";
		System.out.print(m);
		return m;
	}
	
	
	
}
