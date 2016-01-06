package com.project.persistence.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.dao.DepartamentDao;
import com.project.persistence.entity.Department;


@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartamentDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private DepartamentDao dptDao;
	
	@Before
	public void setUp(){
		dptDao.insert("CS", "CS2", new BigDecimal(10000));
		dptDao.insert("CS2", "CS1", new BigDecimal(1031200));
	}
	
	@Test
	public void getAll(){
		List<Department> all = dptDao.findAll();
		for(Department dep : all){
			System.out.print("Nombre ");
			System.out.println(dep.getDeptName());
			System.out.print("Building ");
			System.out.println(dep.getBuilding());
			System.out.print("Budget ");
			System.out.println(dep.getBudget());
		}
	}
	
	@Test
	public void getById(){
		Department dep = dptDao.findById("CS");
		System.out.print("Nombre ");
		System.out.println(dep.getDeptName());
		System.out.print("Building ");
		System.out.println(dep.getBuilding());
		System.out.print("Budget ");
		System.out.println(dep.getBudget());
	}
	
	@Test
	public void delete(){
		dptDao.delete("CS");
		List<Department> all = dptDao.findAll();
		for(Department dep : all){
			System.out.print("Nombre ");
			System.out.println(dep.getDeptName());
			System.out.print("Building ");
			System.out.println(dep.getBuilding());
			System.out.print("Budget ");
			System.out.println(dep.getBudget());
		}
	}
	
	@Test
	public void update(){
		Department dep2 = dptDao.findById("CS");
		dep2.setBudget(new BigDecimal(200000));
		dptDao.update(dep2);
		List<Department> all = dptDao.findAll();
		for(Department dep : all){
			System.out.print("Nombre ");
			System.out.println(dep.getDeptName());
			System.out.print("Building ");
			System.out.println(dep.getBuilding());
			System.out.print("Budget ");
			System.out.println(dep.getBudget());
		}
	}
}
