package com.project.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

@SuppressWarnings("rawtypes")
public interface GenericDao <Entity extends Serializable>{
	/**
	 * Retrieve an object that was previously persisted to the database using
	 * 	the indicated id as primary key
	 */
	
	/**
	 * Retrieve the list
	 * @return
	 */
	List findAll(); 

	/**
	 * Retrieve the list whith max Results
	 * 
	 * @param maxResults
	 * @return
	 */
	List findAll(int maxResults);
	
	/**
	 * Save changes make to a persistent object
	 */
	void saveOrUpdate(Entity entity);
	
	
	Entity getByIndex(int index);

	
	/**
	 * Retrieve the list of entities by a criteria
	 * 
	 * @param criterion
	 * @return
	 */
	List findByCriteria(Criterion criterion);
	
	/**
	 * Retrieve the list whith max Results
	 * 
	 * @param criterion
	 * @param maxResults
	 * @return
	 */
	List findByCriteria(Criterion criterion, int maxResults);
	
	/**
	 * Retrieve more part of the List
	 * 
	 * @return
	 */
	List getMore();
	
	int verificarIndex(int index);
	
	int getLastIndex();
	
	
}
