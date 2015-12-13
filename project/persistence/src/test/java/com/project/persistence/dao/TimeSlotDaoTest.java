package com.project.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.dao.TimeSlotDao;
import com.project.persistence.entity.TimeSlot;
import com.project.persistence.entity.TimeSlotId;

@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class TimeSlotDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private TimeSlotDao timeSlotDao;
	
	@Before
	public void setUp(){
		TimeSlot time = new TimeSlot(new TimeSlotId("M", "M", (byte) 0, (byte) 13), (byte) 4, (byte) 5);
		TimeSlot time2 = new TimeSlot(new TimeSlotId("M", "F", (byte) 0, (byte) 13), (byte) 4, (byte) 5);
		timeSlotDao.saveOrUpdate(time);
		timeSlotDao.saveOrUpdate(time2);
	}
	
	@Test
	public void findAll(){
		List<TimeSlot> all = timeSlotDao.findAll();
		for(TimeSlot ts : all){
			System.out.print("ID ");
			System.out.println(ts.getId().getTimeSlotId());
			System.out.print("Day ");
			System.out.println(ts.getId().getDay());
			System.out.print("End ");
			System.out.println(ts.getEndMin());
		}
	}
	
	@Test
	public void findByIs(){
		TimeSlot ts = timeSlotDao.getById(new TimeSlotId("M", "M", (byte) 0, (byte) 13));
		System.out.print("ID ");
		System.out.println(ts.getId().getTimeSlotId());
		System.out.print("Day ");
		System.out.println(ts.getId().getDay());
		System.out.print("End ");
		System.out.println(ts.getEndMin());
	}
	
	@Test
	public void findAllById(){
		Criterion criterion = Restrictions.eq("id.timeSlotId", "M");
		List<TimeSlot> all = timeSlotDao.findByCriteria(criterion);
		for(TimeSlot ts : all){
			System.out.print("ID ");
			System.out.println(ts.getId().getTimeSlotId());
			System.out.print("Day ");
			System.out.println(ts.getId().getDay());
			System.out.print("End ");
			System.out.println(ts.getEndMin());
		}
	}
	
	
	
	
}
